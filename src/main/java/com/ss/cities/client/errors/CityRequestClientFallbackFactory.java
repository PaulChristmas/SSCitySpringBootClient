package com.ss.cities.client.errors;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.ss.cities.client.services.CityRequestClient;
import com.ss.cities.client.utils.CityServerRequest;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.ss.server.lib.JSONTemplateResponse;

@Component
public class CityRequestClientFallbackFactory {//implements FallbackFactory<CityRequestClient> {
/*
    @Value("${error.timeout}")
    String timeout;

    @Override
    public CityRequestClient create(Throwable ex) {
        String fallbackMessage = ex.getMessage();
        if (ex instanceof HystrixTimeoutException) {
            fallbackMessage = timeout;
        }
        JSONTemplateResponse response = new JSONTemplateResponse(false, fallbackMessage);
        return new CityRequestClient() {
            @Override
            public JSONTemplateResponse cityServerRequestGet() {
                return response;
            }

            @Override
            public JSONTemplateResponse cityServerRequestFind(CityServerRequest cityServerRequest) {
                return response;
            }

            @Override
            public JSONTemplateResponse cityServerRequestUpdate(CityServerRequest cityServerRequest) {
                return response;
            }

            @Override
            public JSONTemplateResponse cityServerRequestDelete(CityServerRequest cityServerRequest) {
                return response;
            }

            @Override
            public ResponseEntity cityServerRequestPost(CityServerRequest cityServerRequest) {
                return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_GATEWAY);
            }
        };
    }*/
}