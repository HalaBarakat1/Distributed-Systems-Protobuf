package org.ds.library;

import org.ds.library.proto.Author;
import org.ds.library.proto.Book;
import org.ds.library.proto.BookFormat;
import org.ds.library.proto.Genre;
import org.ds.library.proto.Library;

import java.nio.file.Path;

/** Builds the sample objects used by every demo (Builder Pattern). */
public final class SampleData {

    /** Folder shared by the Java and Python programs to exchange .bin / .json files. */
    public static final Path SHARED_DIR = Path.of("..", "shared");

    private SampleData() {
    }

    public static Book distributedSystemsBook() {
        Author author = Author.newBuilder()
                .setFirstName("Andrew")
                .setLastName("Tanenbaum")
                .setNationality("Dutch")
                .build();

        return Book.newBuilder()
                .setTitle("Distributed Systems")
                .setAuthor(author)                       // nested message object
                .setIsbn("978-1543057386")
                .setPublicationYear(2017)
                .setGenre(Genre.TECHNOLOGY)              // enum value
                .setFormat(BookFormat.PAPERBACK)
                .setPrice(45.5)
                .setAvailable(true)
                .addTags("distributed")                  // repeated field
                .addTags("networks")
                .addReviews(Book.Review.newBuilder()     // nested message type Book.Review
                        .setReviewer("Hala")
                        .setRating(5)
                        .setComment("Clear explanations")
                        .build())
                .build();
    }

    public static Book briefHistoryBook() {
        return Book.newBuilder()
                .setTitle("A Brief History of Time")
                .setAuthor(Author.newBuilder()
                        .setFirstName("Stephen")
                        .setLastName("Hawking")
                        .setNationality("British"))
                .setIsbn("978-0553380163")
                .setPublicationYear(1998)
                .setGenre(Genre.SCIENCE)
                .setFormat(BookFormat.HARDCOVER)
                .setPrice(18.99)
                .setAvailable(false)
                .addTags("physics")
                .addTags("cosmology")
                .build();
    }

    public static Library library() {
        return Library.newBuilder()
                .setName("HIAST Library")
                .addBooks(distributedSystemsBook())
                .addBooks(briefHistoryBook())
                .build();
    }

    /** A library with n books (used to see how the size difference grows). */
    public static Library libraryOfSize(int n) {
        Library.Builder builder = Library.newBuilder().setName("HIAST Library");
        for (int i = 0; i < n; i++) {
            Book base = (i % 2 == 0) ? distributedSystemsBook() : briefHistoryBook();
            builder.addBooks(base.toBuilder()
                    .setIsbn(String.format("978-%010d", i))
                    .setPublicationYear(1990 + (i % 35)));
        }
        return builder.build();
    }
}
