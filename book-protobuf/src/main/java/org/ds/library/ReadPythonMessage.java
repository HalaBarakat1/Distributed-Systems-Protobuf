package org.ds.library;

import org.ds.library.proto.Book;
import org.ds.library.proto.Library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Part 4 (cross-language): reads the bytes that the Python program produced with
 * SerializeToString() and decodes them with the Java generated class.
 */
public class ReadPythonMessage {

    public static void main(String[] args) throws IOException {
        Path in = SampleData.SHARED_DIR.resolve("library_python.bin");
        byte[] bytes = Files.readAllBytes(in);
        System.out.println("Read " + bytes.length + " bytes written by Python from " + in.normalize());

        Library library = Library.parseFrom(bytes);
        System.out.println("Library: " + library.getName() + " (" + library.getBooksCount() + " books)");
        for (Book b : library.getBooksList()) {
            System.out.println("- " + b.getTitle() + " by " + b.getAuthor().getFirstName() + " "
                    + b.getAuthor().getLastName() + ", " + b.getGenre() + ", " + b.getPublicationYear()
                    + ", tags=" + b.getTagsList() + ", reviews=" + b.getReviewsCount());
        }

        byte[] javaBytes = SampleData.library().toByteArray();
        System.out.println("Python bytes identical to Java bytes: " + Arrays.equals(bytes, javaBytes));
        System.out.println("Decoded object equals Java object  : " + library.equals(SampleData.library()));
    }
}
