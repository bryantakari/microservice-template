package com.ind.grpc.microservice_common;

import org.springframework.stereotype.Service;

@Service
public class EvenOddService {

    public String isEvenOrOdd(Integer number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }
}