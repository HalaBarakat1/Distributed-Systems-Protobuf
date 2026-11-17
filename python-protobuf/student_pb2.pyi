from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Iterable as _Iterable, Mapping as _Mapping, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class Students(_message.Message):
    __slots__ = ("students",)
    STUDENTS_FIELD_NUMBER: _ClassVar[int]
    students: _containers.RepeatedCompositeFieldContainer[Student]
    def __init__(self, students: _Optional[_Iterable[_Union[Student, _Mapping]]] = ...) -> None: ...

class Student(_message.Message):
    __slots__ = ("name", "age")
    NAME_FIELD_NUMBER: _ClassVar[int]
    AGE_FIELD_NUMBER: _ClassVar[int]
    name: str
    age: int
    def __init__(self, name: _Optional[str] = ..., age: _Optional[int] = ...) -> None: ...
