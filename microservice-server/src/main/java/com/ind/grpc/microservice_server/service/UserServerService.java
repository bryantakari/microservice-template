package com.ind.grpc.microservice_server.service;


import com.ind.grpc.GetUserRequest;
import com.ind.grpc.User;
import com.ind.grpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService

public class UserServerService extends UserServiceGrpc.UserServiceImplBase{

    @Override
    public void getUser(GetUserRequest request, StreamObserver<User> responseObserver) {
        responseObserver.onNext(User.newBuilder().setId(request.getId()).setName("Bryan test grpc").build());
        responseObserver.onCompleted();
    }
}
