package com.ss.cities.client.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Configuration
public class BadRequestConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return (method, response) -> {
            int status = response.status();
            if (status >= 400) {
                BadResponse badResponse;
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String responseBody = org.apache.commons.io.IOUtils.toString(response.body().asReader());
                    badResponse = mapper.readValue(responseBody, BadResponse.class);
                } catch (IOException e) {
                    log.error("Error while reading response body",e);
                    return new IOException(e);
                }
                return new BadRequestException(badResponse.getMessage());
            } else {
                return new RuntimeException("Response Code " + status);
            }
        };
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class BadResponse {
        private String status;
        private String error;
        // @JsonProperty("error_message")
        private String message;

    }
}