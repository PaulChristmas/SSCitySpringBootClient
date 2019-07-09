package com.ss.cities.client.services;

import com.ss.cities.client.utils.CityServerRequest;
import com.ss.cities.client.utils.RequestExecutor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.ss.server.lib.JSONTemplateResponse;

import java.util.LinkedList;
import java.util.List;

@Service
public class SubmitActionServiceImpl implements SubmitActionService{
    @Autowired
    private Environment env;

    @Override
    public String submitAction(CityServerRequest cityServerRequest) {
        String address = env.getProperty("request.address");
        String port = env.getProperty("request.port");

        if (address == null || port == null) {
            cityServerRequest.setResponse("Address of port or requested server is not specified in " +
                    "[resources/application.properties].");
            return "error";
        }

        String encoding = env.getProperty("response.encoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }

        String requestURL = "http://" + address + ":" + port;
        JSONTemplateResponse answer;
        try {
            answer = new RequestExecutor(requestURL, encoding).performRequest(cityServerRequest);
        } catch (Exception e) {
            cityServerRequest.setResponse("Connection refused: server is not running or " +
                    "wrong URL requested: " + requestURL + " \n check connection properties.");
            return "error";
        }
        cityServerRequest.setResponse(answer.getResponseContent());

        if (answer.isRequestOk()) {
            cityServerRequest.setCities(answer.getRecordList());
            return "result";
        }

        return "error";
    }
}
