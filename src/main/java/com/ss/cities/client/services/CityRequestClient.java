package com.ss.cities.client.services;

import com.ss.cities.client.CityDTO;
import com.ss.cities.client.errors.BadRequestConfiguration;
import com.ss.cities.client.utils.CityServerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ss.server.lib.JSONTemplateResponse;

@FeignClient(name = "${feign.client.name}", url = "${request.url}", configuration = BadRequestConfiguration.class)
public interface CityRequestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cities")
    public ResponseEntity<CityDTO> cityServerRequestGet();

    @RequestMapping(method = RequestMethod.GET, value = "/search", consumes = "application/json")
    public ResponseEntity<CityDTO> cityServerRequestFind(CityServerRequest cityServerRequest);

    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json")
    public ResponseEntity<CityDTO> cityServerRequestUpdate(CityServerRequest cityServerRequest);

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = "application/json")
    public ResponseEntity<CityDTO> cityServerRequestDelete(CityServerRequest cityServerRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/save", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<CityDTO> cityServerRequestPost(CityServerRequest cityServerRequest);

}