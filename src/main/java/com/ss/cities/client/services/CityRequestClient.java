package com.ss.cities.client.services;

import com.ss.cities.client.errors.CityRequestClientFallbackFactory;
import com.ss.cities.client.utils.CityServerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.ss.server.lib.JSONTemplateResponse;

@FeignClient(name = "${feign.client.name}", url = "${request.url}", fallbackFactory = CityRequestClientFallbackFactory.class)
public interface CityRequestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cities")
    public JSONTemplateResponse cityServerRequestGet();

    @RequestMapping(method = RequestMethod.GET, value = "/search", consumes = "application/json")
    public JSONTemplateResponse cityServerRequestFind(CityServerRequest cityServerRequest);

    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json")
    public JSONTemplateResponse cityServerRequestUpdate(CityServerRequest cityServerRequest);

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = "application/json")
    public JSONTemplateResponse cityServerRequestDelete(CityServerRequest cityServerRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/save", consumes = "application/json")
    public JSONTemplateResponse cityServerRequestPost(CityServerRequest cityServerRequest);

}