package com.ss.cities.client.services;

import com.ss.cities.client.utils.CityServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ss.server.lib.JSONTemplateResponse;

@Service
public class SubmitActionServiceImpl {

    @Autowired
    CityRequestClient client;

    public String doPost(CityServerRequest cityServerRequest) {
        ResponseEntity answer = client.cityServerRequestPost(cityServerRequest);
        cityServerRequest.setResponse(answer.getBody().toString());
        return "result";
    }

    public String doGet(CityServerRequest cityServerRequest) {
        ResponseEntity answer = client.cityServerRequestGet();
        cityServerRequest.setResponse(answer.getBody().toString());
        return "result";
    }

    public String doFind(CityServerRequest cityServerRequest) {
        ResponseEntity answer = client.cityServerRequestFind(cityServerRequest);
        cityServerRequest.setResponse(answer.getBody().toString());
        return "result";
    }

    public String doUpdate(CityServerRequest cityServerRequest) {
        ResponseEntity answer = client.cityServerRequestUpdate(cityServerRequest);
        cityServerRequest.setResponse(answer.getBody().toString());
        return "result";
    }

    public String doDelete(CityServerRequest cityServerRequest) {
        ResponseEntity answer = client.cityServerRequestDelete(cityServerRequest);
        cityServerRequest.setResponse(answer.getBody().toString());
        return "result";
    }

    private String setAnswerType(CityServerRequest cityServerRequest, JSONTemplateResponse answer){
        cityServerRequest.setResponse(answer.getResponseContent());
        if (answer.isRequestOk()) {
            cityServerRequest.setCities(answer.getRecordList());
            return "result";
        }
        return "error";
    }
}
