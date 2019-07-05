package com.ss.springboot.cities.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.springboot.cities.CityServerRequest;
import no.bouvet.jsonclient.JsonClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestExecutor {

    private String URL;
    private JsonClient jsonClient;

    String responseEncoding;
    public RequestExecutor(String URL, String responseEncoding) {
        this.URL = URL;
        this.jsonClient = new JsonClient();
        this.responseEncoding = responseEncoding;
    }

    public String getResponseEncoding() {
        return responseEncoding;
    }

    public void setResponseEncoding(String responseEncoding) {
        this.responseEncoding = responseEncoding;
    }

    public JSONObject performRequest(CityServerRequest cityServerRequest) {
        JSONObject result = null;


        if (cityServerRequest.getAction().equals("GET")) {
            result = doGet();
        } else if (cityServerRequest.getAction().equals("POST")) {
            result = doPost(cityServerRequest);
        } else if (cityServerRequest.getAction().equals("UPDATE")) {
            result = doUpdate(cityServerRequest);
        } else if (cityServerRequest.getAction().equals("DELETE")) {
            result = doDelete(cityServerRequest);
        }
        return result;
    }

    private JSONObject doPost(CityServerRequest cityServerRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(cityServerRequest);
        return getResponseFromHttp(jsonClient.http().post(URL + "/save", node).response());
    }

    private JSONObject doGet() {
        return getResponseFromHttp(jsonClient.http().get(URL + "/cities").response());
    }

    private JSONObject doUpdate(CityServerRequest cityServerRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(cityServerRequest);
        return getResponseFromHttp(jsonClient.http().post(URL + "/update", node).response());
    }
    private JSONObject doDelete(CityServerRequest cityServerRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(cityServerRequest);
        return getResponseFromHttp(jsonClient.http().post(URL + "/delete", node).response());
    }

    private JSONObject getResponseFromHttp(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        JSONObject result = null;

        String responseString = "";
        try {
            responseString = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            result = new JSONObject(responseString);
        } catch (JSONException e) {
            System.err.println("Wrong format: " + e.getMessage());
        }

        return result;
    }

}
