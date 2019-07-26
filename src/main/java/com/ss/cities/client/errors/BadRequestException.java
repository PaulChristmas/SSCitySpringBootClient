package com.ss.cities.client.errors;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public class BadRequestException extends HystrixBadRequestException {

    public BadRequestException(String message) {
        super(message);
    }
}