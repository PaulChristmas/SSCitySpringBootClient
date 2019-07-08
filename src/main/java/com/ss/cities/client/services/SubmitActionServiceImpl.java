package com.ss.cities.client.services;

import com.ss.cities.client.utils.CityServerRequest;
import com.ss.cities.client.utils.RequestExecutor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
        JSONObject ans;
        boolean success;
        String result;
        JSONArray cities;
        List<String> cityView = new LinkedList<>();
        try {
            ans = new RequestExecutor(requestURL, encoding).performRequest(cityServerRequest);
            success = Boolean.parseBoolean(ans.get("requestOk").toString());
            result = ans.get("responseContent").toString();

            cities = ans.getJSONArray("recordList");
            for (int i = 0; i < cities.length(); i++) {
                JSONObject city = cities.getJSONObject(i);
                String status = Boolean.parseBoolean(city.get("locked").toString()) ? "locked" : "unlocked";
                String representation = city.get("name") + ": " + status + " id: " + city.get("id");
                cityView.add(representation);
            }

        } catch (Exception e) {
            cityServerRequest.setResponse("Connection refused: server is not running or " +
                    "wrong URL requested: " + requestURL + " \n check connection properties.");
            return "error";
        }
        cityServerRequest.setResponse(result);

        if (success) {
            cityServerRequest.setCities(cityView);
            return "result";
        }

        return "error";
    }
}
