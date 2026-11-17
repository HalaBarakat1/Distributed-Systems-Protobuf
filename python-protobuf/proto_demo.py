"""
Part 4 of the practical work: the same library.proto used from Python.

1. Build the same Library object that the Java program builds.
2. Encode it with SerializeToString() and compare the bytes with the Java bytes.
3. Decode the bytes written by Java (library_java.bin) with ParseFromString().
4. Write library_python.bin so that Java can decode it (ReadPythonMessage).
"""
import json
import os
from pathlib import Path

from google.protobuf import json_format

import library_pb2 as lib
import student_pb2

SHARED = Path(__file__).resolve().parent.parent / "shared"


def build_library() -> lib.Library:
    ds_book = lib.Book(
        title="Distributed Systems",
        author=lib.Author(first_name="Andrew", last_name="Tanenbaum", nationality="Dutch"),
        isbn="978-1543057386",
        publication_year=2017,
        genre=lib.TECHNOLOGY,
        format=lib.PAPERBACK,
        price=45.5,
        available=True,
        tags=["distributed", "networks"],
        reviews=[lib.Book.Review(reviewer="Hala", rating=5, comment="Clear explanations")],
    )

    # The same kind of object can also be filled field by field
    history = lib.Book()
    history.title = "A Brief History of Time"
    history.author.first_name = "Stephen"
    history.author.last_name = "Hawking"
    history.author.nationality = "British"
    history.isbn = "978-0553380163"
    history.publication_year = 1998
    history.genre = lib.SCIENCE
    history.format = lib.HARDCOVER
    history.price = 18.99
    history.available = False
    history.tags.extend(["physics", "cosmology"])

    return lib.Library(name="HIAST Library", books=[ds_book, history])


def main() -> None:
    print("===== [1] Python encoder: SerializeToString() =====")
    library = build_library()
    data = library.SerializeToString()
    print(f"Library message size = {len(data)} bytes")
    print("First 32 bytes:", data[:32].hex(" "))

    SHARED.mkdir(exist_ok=True)
    (SHARED / "library_python.bin").write_bytes(data)
    print("Written to", os.path.relpath(SHARED / "library_python.bin"))

    print("\n===== [2] Compare with the bytes produced by Java =====")
    java_bytes = (SHARED / "library_java.bin").read_bytes()
    print(f"Java bytes = {len(java_bytes)}, Python bytes = {len(data)}, identical = {java_bytes == data}")

    print("\n===== [3] Python decoder: ParseFromString(Java bytes) =====")
    from_java = lib.Library()
    from_java.ParseFromString(java_bytes)
    print("Library:", from_java.name, f"({len(from_java.books)} books)")
    for b in from_java.books:
        print(f"- {b.title} by {b.author.first_name} {b.author.last_name} | "
              f"{lib.Genre.Name(b.genre)} | {b.publication_year} | ${b.price:.2f} | tags={list(b.tags)}")
        for r in b.reviews:
            print(f"    review: {r.reviewer} -> {r.rating}/5 \"{r.comment}\"")
    print("Decoded Java object == Python object:", from_java == library)

    print("\n===== [4] JSON vs Protobuf in Python =====")
    compact = json.dumps(json_format.MessageToDict(library), separators=(",", ":")).encode("utf-8")
    pretty = json_format.MessageToJson(library).encode("utf-8")
    print(f"JSON (pretty) = {len(pretty)} bytes | JSON (compact) = {len(compact)} bytes | Protobuf = {len(data)} bytes")

    students = student_pb2.Students(students=[
        student_pb2.Student(name="Mohamad", age=40),
        student_pb2.Student(name="Ahmad", age=40),
    ])
    s_bytes = students.SerializeToString()
    print("Course example (Students) protobuf:", s_bytes.hex(" "), f"-> {len(s_bytes)} bytes")


if __name__ == "__main__":
    main()
