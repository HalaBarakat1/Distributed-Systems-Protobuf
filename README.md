# Homework 5 - Protocol Buffers & gRPC

The full report (Arabic) is in [Report.pdf](Report.pdf).

```
proto/library.proto        shared schema: enums, nested message Book.Review, LibraryService (gRPC)
proto/student.proto        course example (Students), used as the JSON baseline
tools/                     protoc.exe 28.2 + protoc-gen-grpc-java.exe 1.68.1
generate.bat               regenerates Java + Python code from proto/
book-protobuf/             Java (Maven) project
python-protobuf/           Python version of the same schema
shared/                    .bin / .json files exchanged between Java and Python (created at run time)
```

## Generate code
```bat
generate.bat
```
(relative paths are used because protoc cannot open absolute Windows paths that contain Arabic folder names)

## Java
```powershell
cd book-protobuf
mvn -q compile
mvn -q exec:java                                                     # EncodingDemo: toByteArray / parseFrom
mvn -q exec:java "-Dexec.mainClass=org.ds.library.JsonSizeComparison" # Protobuf vs JSON size
mvn -q exec:java "-Dexec.mainClass=org.ds.library.ReadPythonMessage"  # decode bytes written by Python
```

## Python
```powershell
cd python-protobuf
python -m venv .venv
.venv\Scripts\python -m pip install protobuf grpcio grpcio-tools
.venv\Scripts\python proto_demo.py        # run after EncodingDemo (reads ../shared/library_java.bin)
```

## gRPC (optional part)
```powershell
# terminal 1
cd book-protobuf; mvn -q exec:java "-Dexec.mainClass=org.ds.library.grpc.LibraryServer"
# terminal 2
cd book-protobuf; mvn -q exec:java "-Dexec.mainClass=org.ds.library.grpc.LibraryClient"
cd python-protobuf; .venv\Scripts\python grpc_client.py
```

## Results
| Message | JSON compact | Protobuf | JSON / Protobuf |
|---|---|---|---|
| Students (course example) | 68 B | 24 B | 2.83x |
| One Book | 335 B | 136 B | 2.46x |
| Library (2 books) | 615 B | 262 B | 2.35x |
| 1000 books | 290534 B | 123515 B | 2.35x |

Java and Python produce byte-identical encodings (262 bytes) of the same Library object.
