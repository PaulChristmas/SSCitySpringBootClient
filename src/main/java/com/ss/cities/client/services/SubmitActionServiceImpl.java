package com.ss.cities.client.services;

import com.ss.cities.client.utils.CityServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ss.server.lib.JSONTemplateResponse;

@Service
public class SubmitActionServiceImpl {

    @Autowired
    CityRequestClient client;

    public String doPost(CityServerRequest cityServerRequest) {
        JSONTemplateResponse answer = client.cityServerRequestPost(cityServerRequest);
        return setAnswerType(cityServerRequest, answer);
    }

    public String doGet(CityServerRequest cityServerRequest) {
        JSONTemplateResponse answer = client.cityServerRequestGet();
        return setAnswerType(cityServerRequest, answer);
    }

    public String doFind(CityServerRequest cityServerRequest) {
        JSONTemplateResponse answer = client.cityServerRequestFind(cityServerRequest);
        return setAnswerType(cityServerRequest, answer);
    }

    public String doUpdate(CityServerRequest cityServerRequest) {
        JSONTemplateResponse answer = client.cityServerRequestUpdate(cityServerRequest);
        return setAnswerType(cityServerRequest, answer);
    }

    public String doDelete(CityServerRequest cityServerRequest) {
        JSONTemplateResponse answer = client.cityServerRequestDelete(cityServerRequest);
        return setAnswerType(cityServerRequest, answer);
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
