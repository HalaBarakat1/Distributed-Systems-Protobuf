package org.ds.library.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.1)",
    comments = "Source: library.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LibraryServiceGrpc {

  private LibraryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "library.LibraryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ds.library.proto.GetBookRequest,
      org.ds.library.proto.Book> getGetBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBook",
      requestType = org.ds.library.proto.GetBookRequest.class,
      responseType = org.ds.library.proto.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ds.library.proto.GetBookRequest,
      org.ds.library.proto.Book> getGetBookMethod() {
    io.grpc.MethodDescriptor<org.ds.library.proto.GetBookRequest, org.ds.library.proto.Book> getGetBookMethod;
    if ((getGetBookMethod = LibraryServiceGrpc.getGetBookMethod) == null) {
      synchronized (LibraryServiceGrpc.class) {
        if ((getGetBookMethod = LibraryServiceGrpc.getGetBookMethod) == null) {
          LibraryServiceGrpc.getGetBookMethod = getGetBookMethod =
              io.grpc.MethodDescriptor.<org.ds.library.proto.GetBookRequest, org.ds.library.proto.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ds.library.proto.GetBookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ds.library.proto.Book.getDefaultInstance()))
              .setSchemaDescriptor(new LibraryServiceMethodDescriptorSupplier("GetBook"))
              .build();
        }
      }
    }
    return getGetBookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ds.library.proto.Book,
      org.ds.library.proto.CreateBookResponse> getCreateBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBook",
      requestType = org.ds.library.proto.Book.class,
      responseType = org.ds.library.proto.CreateBookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ds.library.proto.Book,
      org.ds.library.proto.CreateBookResponse> getCreateBookMethod() {
    io.grpc.MethodDescriptor<org.ds.library.proto.Book, org.ds.library.proto.CreateBookResponse> getCreateBookMethod;
    if ((getCreateBookMethod = LibraryServiceGrpc.getCreateBookMethod) == null) {
      synchronized (LibraryServiceGrpc.class) {
        if ((getCreateBookMethod = LibraryServiceGrpc.getCreateBookMethod) == null) {
          LibraryServiceGrpc.getCreateBookMethod = getCreateBookMethod =
              io.grpc.MethodDescriptor.<org.ds.library.proto.Book, org.ds.library.proto.CreateBookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ds.library.proto.Book.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ds.library.proto.CreateBookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LibraryServiceMethodDescriptorSupplier("CreateBook"))
              .build();
        }
      }
    }
    return getCreateBookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ds.library.proto.ListBooksRequest,
      org.ds.library.proto.Book> getListBooksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListBooks",
      requestType = org.ds.library.proto.ListBooksRequest.class,
      responseType = org.ds.library.proto.Book.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.ds.library.proto.ListBooksRequest,
      org.ds.library.proto.Book> getListBooksMethod() {
    io.grpc.MethodDescriptor<org.ds.library.proto.ListBooksRequest, org.ds.library.proto.Book> getListBooksMethod;
    if ((getListBooksMethod = LibraryServiceGrpc.getListBooksMethod) == null) {
      synchronized (LibraryServiceGrpc.class) {
        if ((getListBooksMethod = LibraryServiceGrpc.getListBooksMethod) == null) {
          LibraryServiceGrpc.getListBooksMethod = getListBooksMethod =
              io.grpc.MethodDescriptor.<org.ds.library.proto.ListBooksRequest, org.ds.library.proto.Book>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListBooks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ds.library.proto.ListBooksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ds.library.proto.Book.getDefaultInstance()))
              .setSchemaDescriptor(new LibraryServiceMethodDescriptorSupplier("ListBooks"))
              .build();
        }
      }
    }
    return getListBooksMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LibraryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibraryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibraryServiceStub>() {
        @java.lang.Override
        public LibraryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibraryServiceStub(channel, callOptions);
        }
      };
    return LibraryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LibraryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibraryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibraryServiceBlockingStub>() {
        @java.lang.Override
        public LibraryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibraryServiceBlockingStub(channel, callOptions);
        }
      };
    return LibraryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LibraryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibraryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibraryServiceFutureStub>() {
        @java.lang.Override
        public LibraryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibraryServiceFutureStub(channel, callOptions);
        }
      };
    return LibraryServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Unary RPC: one request, one response
     * </pre>
     */
    default void getBook(org.ds.library.proto.GetBookRequest request,
        io.grpc.stub.StreamObserver<org.ds.library.proto.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unary RPC: client sends a Book, server stores it
     * </pre>
     */
    default void createBook(org.ds.library.proto.Book request,
        io.grpc.stub.StreamObserver<org.ds.library.proto.CreateBookResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBookMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server-streaming RPC: the server sends many books over one HTTP/2 stream
     * </pre>
     */
    default void listBooks(org.ds.library.proto.ListBooksRequest request,
        io.grpc.stub.StreamObserver<org.ds.library.proto.Book> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListBooksMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service LibraryService.
   */
  public static abstract class LibraryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LibraryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service LibraryService.
   */
  public static final class LibraryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<LibraryServiceStub> {
    private LibraryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibraryServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: one request, one response
     * </pre>
     */
    public void getBook(org.ds.library.proto.GetBookRequest request,
        io.grpc.stub.StreamObserver<org.ds.library.proto.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unary RPC: client sends a Book, server stores it
     * </pre>
     */
    public void createBook(org.ds.library.proto.Book request,
        io.grpc.stub.StreamObserver<org.ds.library.proto.CreateBookResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server-streaming RPC: the server sends many books over one HTTP/2 stream
     * </pre>
     */
    public void listBooks(org.ds.library.proto.ListBooksRequest request,
        io.grpc.stub.StreamObserver<org.ds.library.proto.Book> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListBooksMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service LibraryService.
   */
  public static final class LibraryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LibraryServiceBlockingStub> {
    private LibraryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibraryServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: one request, one response
     * </pre>
     */
    public org.ds.library.proto.Book getBook(org.ds.library.proto.GetBookRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unary RPC: client sends a Book, server stores it
     * </pre>
     */
    public org.ds.library.proto.CreateBookResponse createBook(org.ds.library.proto.Book request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBookMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server-streaming RPC: the server sends many books over one HTTP/2 stream
     * </pre>
     */
    public java.util.Iterator<org.ds.library.proto.Book> listBooks(
        org.ds.library.proto.ListBooksRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListBooksMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service LibraryService.
   */
  public static final class LibraryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<LibraryServiceFutureStub> {
    private LibraryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibraryServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC: one request, one response
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ds.library.proto.Book> getBook(
        org.ds.library.proto.GetBookRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unary RPC: client sends a Book, server stores it
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ds.library.proto.CreateBookResponse> createBook(
        org.ds.library.proto.Book request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBookMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BOOK = 0;
  private static final int METHODID_CREATE_BOOK = 1;
  private static final int METHODID_LIST_BOOKS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BOOK:
          serviceImpl.getBook((org.ds.library.proto.GetBookRequest) request,
              (io.grpc.stub.StreamObserver<org.ds.library.proto.Book>) responseObserver);
          break;
        case METHODID_CREATE_BOOK:
          serviceImpl.createBook((org.ds.library.proto.Book) request,
              (io.grpc.stub.StreamObserver<org.ds.library.proto.CreateBookResponse>) responseObserver);
          break;
        case METHODID_LIST_BOOKS:
          serviceImpl.listBooks((org.ds.library.proto.ListBooksRequest) request,
              (io.grpc.stub.StreamObserver<org.ds.library.proto.Book>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetBookMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ds.library.proto.GetBookRequest,
              org.ds.library.proto.Book>(
                service, METHODID_GET_BOOK)))
        .addMethod(
          getCreateBookMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ds.library.proto.Book,
              org.ds.library.proto.CreateBookResponse>(
                service, METHODID_CREATE_BOOK)))
        .addMethod(
          getListBooksMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.ds.library.proto.ListBooksRequest,
              org.ds.library.proto.Book>(
                service, METHODID_LIST_BOOKS)))
        .build();
  }

  private static abstract class LibraryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LibraryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ds.library.proto.LibraryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LibraryService");
    }
  }

  private static final class LibraryServiceFileDescriptorSupplier
      extends LibraryServiceBaseDescriptorSupplier {
    LibraryServiceFileDescriptorSupplier() {}
  }

  private static final class LibraryServiceMethodDescriptorSupplier
      extends LibraryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    LibraryServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LibraryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LibraryServiceFileDescriptorSupplier())
              .addMethod(getGetBookMethod())
              .addMethod(getCreateBookMethod())
              .addMethod(getListBooksMethod())
              .build();
        }
      }
    }
    return result;
  }
}
