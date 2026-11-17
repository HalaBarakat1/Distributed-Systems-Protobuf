package org.ds.library;

import com.google.protobuf.InvalidProtocolBufferException;
import org.ds.library.proto.Author;
import org.ds.library.proto.Book;
import org.ds.library.proto.Genre;
import org.ds.library.proto.Library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Part 2 of the practical work: encode messages with toByteArray() and decode them with parseFrom().
 */
public class EncodingDemo {

    public static void main(String[] args) throws IOException {
        byte[] msg = sender();
        receiver(msg);
        wireFormatDetails();
        defaultValues();
        corruptedMessage(msg);
    }

    /** Builds the objects, serializes them and writes the bytes to ../shared for the Python program. */
    private static byte[] sender() throws IOException {
        System.out.println("===== [1] Sender: build objects with the Builder Pattern =====");
        Library library = SampleData.library();

        byte[] arrayToSend = library.toByteArray();
        System.out.println("Encoded bytes (hex):");
        System.out.println(Hex.format(arrayToSend, 32));
        System.out.println("Library message size = " + arrayToSend.length + " bytes");

        Files.createDirectories(SampleData.SHARED_DIR);
        Path out = SampleData.SHARED_DIR.resolve("library_java.bin");
        Files.write(out, arrayToSend);
        System.out.println("Written to " + out.normalize());
        System.out.println();
        return arrayToSend;
    }

    /** Decodes the byte array back into objects and reads fields through the generated getters. */
    private static void receiver(byte[] msg) throws InvalidProtocolBufferException {
        System.out.println("===== [2] Receiver: Library.parseFrom(bytes) =====");
        Library received = Library.parseFrom(msg);

        System.out.println("Library name : " + received.getName());
        System.out.println("Books count  : " + received.getBooksCount());
        for (Book book : received.getBooksList()) {
            Author a = book.getAuthor();
            System.out.printf("- %-24s | %s %s (%s) | ISBN %s | %d | %s | %s | $%.2f | available=%b%n",
                    book.getTitle(), a.getFirstName(), a.getLastName(), a.getNationality(),
                    book.getIsbn(), book.getPublicationYear(), book.getGenre(), book.getFormat(),
                    book.getPrice(), book.getAvailable());
            System.out.println("    tags=" + book.getTagsList() + ", reviews=" + book.getReviewsCount());
            for (Book.Review r : book.getReviewsList()) {
                System.out.println("    review: " + r.getReviewer() + " -> " + r.getRating() + "/5 \"" + r.getComment() + "\"");
            }
        }
        System.out.println("Decoded object equals the original object: " + received.equals(SampleData.library()));
        System.out.println();
    }

    /** Shows how field numbers (tags) and wire types are written instead of field names. */
    private static void wireFormatDetails() {
        System.out.println("===== [3] Wire format: key = (field_number << 3) | wire_type =====");
        Author author = Author.newBuilder().setFirstName("Andrew").build();
        System.out.println("Author{first_name=\"Andrew\"}  -> " + Hex.format(author.toByteArray(), 64)
                + "  (" + author.getSerializedSize() + " bytes)");
        System.out.println("  0a = (1 << 3) | 2  -> field 1, wire type 2 (LEN), 06 = length, then UTF-8 \"Andrew\"");

        Book year = Book.newBuilder().setPublicationYear(2017).build();
        System.out.println("Book{publication_year=2017} -> " + Hex.format(year.toByteArray(), 64)
                + "  (" + year.getSerializedSize() + " bytes)");
        System.out.println("  20 = (4 << 3) | 0  -> field 4, wire type 0 (VARINT), e1 0f = 2017 as varint");

        Book genre = Book.newBuilder().setGenre(Genre.TECHNOLOGY).build();
        System.out.println("Book{genre=TECHNOLOGY}      -> " + Hex.format(genre.toByteArray(), 64)
                + "  (" + genre.getSerializedSize() + " bytes)");
        System.out.println("  28 = (5 << 3) | 0  -> field 5, enum sent as its number 04");
        System.out.println();
    }

    /** Fields that hold their default value are not written at all in proto3. */
    private static void defaultValues() throws InvalidProtocolBufferException {
        System.out.println("===== [4] Default values are not sent =====");
        Book empty = Book.newBuilder().build();
        byte[] bytes = empty.toByteArray();
        Book decoded = Book.parseFrom(bytes);
        System.out.println("Empty Book size = " + bytes.length + " bytes");
        System.out.println("title=\"" + decoded.getTitle() + "\", year=" + decoded.getPublicationYear()
                + ", genre=" + decoded.getGenre() + ", price=" + decoded.getPrice()
                + ", hasAuthor=" + decoded.hasAuthor() + ", tags=" + decoded.getTagsList());
        System.out.println();
    }

    /** parseFrom() rejects bytes that are not a valid message. */
    private static void corruptedMessage(byte[] msg) {
        System.out.println("===== [5] Truncated message =====");
        byte[] truncated = java.util.Arrays.copyOf(msg, msg.length - 10);
        try {
            Library.parseFrom(truncated);
            System.out.println("Parsed without error (unexpected)");
        } catch (InvalidProtocolBufferException e) {
            System.out.println("InvalidProtocolBufferException: " + e.getMessage());
        }
    }
}
