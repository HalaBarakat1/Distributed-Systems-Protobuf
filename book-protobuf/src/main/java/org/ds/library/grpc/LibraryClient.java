package org.ds.library.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.ds.library.proto.Author;
import org.ds.library.proto.Book;
import org.ds.library.proto.BookFormat;
import org.ds.library.proto.CreateBookResponse;
import org.ds.library.proto.GetBookRequest;
import org.ds.library.proto.Genre;
import org.ds.library.proto.LibraryServiceGrpc;
import org.ds.library.proto.ListBooksRequest;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/** gRPC client: calls the remote methods as if they were local methods (RPC). */
public class LibraryClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", LibraryServer.PORT)
                .usePlaintext()                       // no TLS for the local lab
                .build();
        LibraryServiceGrpc.LibraryServiceBlockingStub stub = LibraryServiceGrpc.newBlockingStub(channel);

        try {
            System.out.println("--- GetBook(978-1543057386) ---");
            Book book = stub.getBook(GetBookRequest.newBuilder().setIsbn("978-1543057386").build());
            System.out.println(book.getTitle() + " by " + book.getAuthor().getLastName()
                    + " (" + book.getPublicationYear() + ", " + book.getGenre() + ")");

            System.out.println("--- GetBook(000-0000000000) ---");
            call(() -> stub.getBook(GetBookRequest.newBuilder().setIsbn("000-0000000000").build()));

            Book newBook = Book.newBuilder()
                    .setTitle("Clean Code")
                    .setAuthor(Author.newBuilder().setFirstName("Robert").setLastName("Martin").setNationality("American"))
                    .setIsbn("978-0132350884")
                    .setPublicationYear(2008)
                    .setGenre(Genre.TECHNOLOGY)
                    .setFormat(BookFormat.EBOOK)
                    .setPrice(37.0)
                    .setAvailable(true)
                    .addTags("software")
                    .build();

            System.out.println("--- CreateBook(Clean Code) ---");
            CreateBookResponse created = stub.createBook(newBook);
            System.out.println("created=" + created.getCreated() + ", message=" + created.getMessage()
                    + ", total_books=" + created.getTotalBooks());

            System.out.println("--- CreateBook(Clean Code) again ---");
            call(() -> stub.createBook(newBook));

            System.out.println("--- ListBooks(TECHNOLOGY) : server streaming ---");
            Iterator<Book> stream = stub.listBooks(ListBooksRequest.newBuilder().setGenre(Genre.TECHNOLOGY).build());
            stream.forEachRemaining(b -> System.out.println("  <- " + b.getTitle() + " [" + b.getIsbn() + "]"));

            System.out.println("--- ListBooks(all) : server streaming ---");
            stub.listBooks(ListBooksRequest.getDefaultInstance())
                    .forEachRemaining(b -> System.out.println("  <- " + b.getTitle() + " (" + b.getGenre() + ")"));
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static void call(Runnable rpc) {
        try {
            rpc.run();
            System.out.println("call succeeded (unexpected)");
        } catch (StatusRuntimeException e) {
            System.out.println("gRPC error: " + e.getStatus().getCode() + " - " + e.getStatus().getDescription());
        }
    }
}
