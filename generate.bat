@echo off
REM Regenerates the Java and Python code from proto\*.proto
REM Relative paths are used on purpose: protoc on Windows cannot open absolute paths
REM that contain non-ASCII (Arabic) folder names.
cd /d "%~dp0"

REM Java message classes + gRPC stubs
tools\protoc.exe --proto_path=proto ^
  --java_out=book-protobuf\src\main\java ^
  --plugin=protoc-gen-grpc-java=tools\protoc-gen-grpc-java.exe ^
  --grpc-java_out=book-protobuf\src\main\java ^
  library.proto student.proto

REM Python message classes (+ type hints)
tools\protoc.exe --proto_path=proto ^
  --python_out=python-protobuf --pyi_out=python-protobuf ^
  library.proto student.proto

REM Python gRPC stubs (needs: python-protobuf\.venv with grpcio-tools)
if exist python-protobuf\.venv\Scripts\python.exe (
  python-protobuf\.venv\Scripts\python.exe -m grpc_tools.protoc --proto_path=proto ^
    --grpc_python_out=python-protobuf library.proto
)

echo Done.
