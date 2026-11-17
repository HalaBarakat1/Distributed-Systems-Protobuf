"""
Optional part: a Python gRPC client that calls the Java LibraryServer.
The client and the server share only library.proto - nothing else.
Run the Java server first (LibraryServer on port 50051).
"""
import grpc

import library_pb2 as lib
import library_pb2_grpc as lib_grpc


def main() -> None:
    with grpc.insecure_channel("localhost:50051") as channel:
        stub = lib_grpc.LibraryServiceStub(channel)

        print("--- [Python] GetBook(978-0553380163) ---")
        book = stub.GetBook(lib.GetBookRequest(isbn="978-0553380163"))
        print(f"{book.title} by {book.author.last_name} ({book.publication_year}, {lib.Genre.Name(book.genre)})")

        print("--- [Python] CreateBook(Python Crash Course) ---")
        resp = stub.CreateBook(lib.Book(
            title="Python Crash Course",
            author=lib.Author(first_name="Eric", last_name="Matthes", nationality="American"),
            isbn="978-1718502703",
            publication_year=2023,
            genre=lib.TECHNOLOGY,
            format=lib.EBOOK,
            price=29.99,
            available=True,
            tags=["python"],
        ))
        print(f"created={resp.created}, message={resp.message}, total_books={resp.total_books}")

        print("--- [Python] GetBook(missing) ---")
        try:
            stub.GetBook(lib.GetBookRequest(isbn="111-1111111111"))
        except grpc.RpcError as e:
            print(f"gRPC error: {e.code().name} - {e.details()}")

        print("--- [Python] ListBooks(TECHNOLOGY) : server streaming ---")
        for b in stub.ListBooks(lib.ListBooksRequest(genre=lib.TECHNOLOGY)):
            print(f"  <- {b.title} [{b.isbn}]")


if __name__ == "__main__":
    main()
