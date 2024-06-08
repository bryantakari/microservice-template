package com.ind.grpc.microservice_server.config;

import com.ind.grpc.microservice_server.service.UserServerService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcConfig {
    @Value("${grpc.server.port}")
    private int port;

    @Autowired
    UserServerService userServerService;

    private Server server;

    @PostConstruct
    public void start() throws IOException {
        System.out.println("test123");
        server = ServerBuilder.forPort(port)
                .addService(userServerService) // Add your gRPC service implementation
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            GrpcConfig.this.stop();
            System.out.println("Server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
