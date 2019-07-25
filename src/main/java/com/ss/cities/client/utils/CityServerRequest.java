package com.ss.cities.client.utils;

import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CityServerRequest {

    @NonNull
    private String cityName;
    @NonNull
    private String updateName;
    @NonNull
    private String action = "GET";
    @NonNull
    private boolean locked;
    private String response;
    private List<Object> cities;

}