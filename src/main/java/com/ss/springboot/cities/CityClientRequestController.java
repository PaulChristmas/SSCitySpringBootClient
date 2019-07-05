package com.ss.springboot.cities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.springboot.cities.utils.RequestExecutor;
import no.bouvet.jsonclient.JsonClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class CityClientRequestController {

    @Autowired
    private Environment env;

    @GetMapping("/cityServerRequest")
    public String cityServerRequestForm(Model model) {
        model.addAttribute("cityServerRequest", new CityServerRequest());
        return "cityServerRequest";
    }

    @PostMapping("/cityServerRequest")
    public String cityServerRequestSubmit(@ModelAttribute CityServerRequest cityServerRequest) {
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
        List<String> cityView = new LinkedList<String>();
        try {
            ans = new RequestExecutor(requestURL, encoding).performRequest(cityServerRequest);
            success = Boolean.parseBoolean(ans.get("requestOk").toString());
            result = ans.get("responseContent").toString();
            cities = ans.getJSONArray("cities");
            for (int i = 0; i < cities.length(); i++) {
                JSONObject city = cities.getJSONObject(i);
                String status = Boolean.parseBoolean(city.get("locked").toString())? "locked" : "unlocked";
                String representation = city.get("name") + ": " + status + " id: " + city.get("id");
                cityView.add(representation);
            }

        } catch (Exception e) {
            cityServerRequest.setResponse("Connection refused: server is not running or " +
                    "wrong URL requested: " + requestURL + " \n check connection properties.");
            return "error";
        }

        if (success) {
            cityServerRequest.setResponse(result);
            cityServerRequest.setCities(cityView);
            return "result";
        }

        cityServerRequest.setResponse(result);
        return "error";
    }

}