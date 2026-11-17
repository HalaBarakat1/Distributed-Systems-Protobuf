from google.protobuf.internal import containers as _containers
from google.protobuf.internal import enum_type_wrapper as _enum_type_wrapper
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class Genre(int, metaclass=_enum_type_wrapper.EnumTypeWrapper):
    __slots__ = ()
    GENRE_UNSPECIFIED: _ClassVar[Genre]
    FICTION: _ClassVar[Genre]
    SCIENCE: _ClassVar[Genre]
    HISTORY: _ClassVar[Genre]
    TECHNOLOGY: _ClassVar[Genre]

class BookFormat(int, metaclass=_enum_type_wrapper.EnumTypeWrapper):
    __slots__ = ()
    FORMAT_UNSPECIFIED: _ClassVar[BookFormat]
    HARDCOVER: _ClassVar[BookFormat]
    PAPERBACK: _ClassVar[BookFormat]
    EBOOK: _ClassVar[BookFormat]
GENRE_UNSPECIFIED: Genre
FICTION: Genre
SCIENCE: Genre
HISTORY: Genre
TECHNOLOGY: Genre
FORMAT_UNSPECIFIED: BookFormat
HARDCOVER: BookFormat
PAPERBACK: BookFormat
EBOOK: BookFormat

class Author(_message.Message):
    __slots__ = ("first_name", "last_name", "nationality")
    FIRST_NAME_FIELD_NUMBER: _ClassVar[int]
    LAST_NAME_FIELD_NUMBER: _ClassVar[int]
    NATIONALITY_FIELD_NUMBER: _ClassVar[int]
    first_name: str
    last_name: str
    nationality: str
    def __init__(self, first_name: _Optional[str] = ..., last_name: _Optional[str] = ..., nationality: _Optional[str] = ...) -> None: ...

class Book(_message.Message):
    __slots__ = ("title", "author", "isbn", "publication_year", "genre", "format", "price", "available", "tags", "reviews")
    class Review(_message.Message):
        __slots__ = ("reviewer", "rating", "comment")
        REVIEWER_FIELD_NUMBER: _ClassVar[int]
        RATING_FIELD_NUMBER: _ClassVar[int]
        COMMENT_FIELD_NUMBER: _ClassVar[int]
        reviewer: str
        rating: int
        comment: str
        def __init__(self, reviewer: _Optional[str] = ..., rating: _Optional[int] = ..., comment: _Optional[str] = ...) -> None: ...
    TITLE_FIELD_NUMBER: _ClassVar[int]
    AUTHOR_FIELD_NUMBER: _ClassVar[int]
    ISBN_FIELD_NUMBER: _ClassVar[int]
    PUBLICATION_YEAR_FIELD_NUMBER: _ClassVar[int]
    GENRE_FIELD_NUMBER: _ClassVar[int]
    FORMAT_FIELD_NUMBER: _ClassVar[int]
    PRICE_FIELD_NUMBER: _ClassVar[int]
    AVAILABLE_FIELD_NUMBER: _ClassVar[int]
    TAGS_FIELD_NUMBER: _ClassVar[int]
    REVIEWS_FIELD_NUMBER: _ClassVar[int]
    title: str
    author: Author
    isbn: str
    publication_year: int
    genre: Genre
    format: BookFormat
    price: float
    available: bool
    tags: _containers.RepeatedScalarFieldContainer[str]
    reviews: _containers.RepeatedCompositeFieldContainer[Book.Review]
    def __init__(self, title: _Optional[str] = ..., author: _Optional[_Union[Author, _Mapping]] = ..., isbn: _Optional[str] = ..., publication_year: _Optional[int] = ..., genre: _Optional[_Union[Genre, str]] = ..., format: _Optional[_Union[BookFormat, str]] = ..., price: _Optional[float] = ..., available: bool = ..., tags: _Optional[_Iterable[str]] = ..., reviews: _Optional[_Iterable[_Union[Book.Review, _Mapping]]] = ...) -> None: ...

class Library(_message.Message):
    __slots__ = ("name", "books")
    NAME_FIELD_NUMBER: _ClassVar[int]
    BOOKS_FIELD_NUMBER: _ClassVar[int]
    name: str
    books: _containers.RepeatedCompositeFieldContainer[Book]
    def __init__(self, name: _Optional[str] = ..., books: _Optional[_Iterable[_Union[Book, _Mapping]]] = ...) -> None: ...

class GetBookRequest(_message.Message):
    __slots__ = ("isbn",)
    ISBN_FIELD_NUMBER: _ClassVar[int]
    isbn: str
    def __init__(self, isbn: _Optional[str] = ...) -> None: ...

class CreateBookResponse(_message.Message):
    __slots__ = ("created", "message", "total_books")
    CREATED_FIELD_NUMBER: _ClassVar[int]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    TOTAL_BOOKS_FIELD_NUMBER: _ClassVar[int]
    created: bool
    message: str
    total_books: int
    def __init__(self, created: bool = ..., message: _Optional[str] = ..., total_books: _Optional[int] = ...) -> None: ...

class ListBooksRequest(_message.Message):
    __slots__ = ("genre",)
    GENRE_FIELD_NUMBER: _ClassVar[int]
    genre: Genre
    def __init__(self, genre: _Optional[_Union[Genre, str]] = ...) -> None: ...
