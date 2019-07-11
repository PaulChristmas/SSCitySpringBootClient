package com.ss.cities.client.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.bouvet.jsonclient.JsonClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.ss.server.lib.JSONTemplateResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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

    public JSONTemplateResponse performRequest(CityServerRequest cityServerRequest) {
        JSONTemplateResponse result = null;

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

    private JSONTemplateResponse doPost(CityServerRequest cityServerRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(cityServerRequest);
        return getResponseFromHttp(jsonClient.http().post(URL + "/save", node).response());
    }

    private JSONTemplateResponse doGet() {
        return getResponseFromHttp(jsonClient.http().get(URL + "/cities").response());
    }

    private JSONTemplateResponse doUpdate(CityServerRequest cityServerRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(cityServerRequest);
        return getResponseFromHttp(jsonClient.http().post(URL + "/update", node).response());
    }
    private JSONTemplateResponse doDelete(CityServerRequest cityServerRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(cityServerRequest);
        return getResponseFromHttp(jsonClient.http().post(URL + "/delete", node).response());
    }

    private JSONTemplateResponse getResponseFromHttp(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        JSONTemplateResponse result = new JSONTemplateResponse();
        result.setRequestOk(false);

        String responseString = null;
        try {
            responseString = EntityUtils.toString(entity, responseEncoding);
        } catch (IOException e) {
            result.setResponseContent("An error occurs while parsing server response");
        } catch (ParseException e) {
            result.setResponseContent("Header of server response cannot be parsed");
        } catch (IllegalArgumentException e) {
            result.setResponseContent("Response content is not defined or too large");
        }

        if (responseString == null) {
            return result;
        }

        try {
            JSONObject parsedParameters = new JSONObject(responseString);
            JSONArray cities = parsedParameters.getJSONArray("recordList");
            List<Object> cityView = new LinkedList<>();

            for (int i = 0; i < cities.length(); i++) {
                JSONObject city = cities.getJSONObject(i);
                String status = Boolean.parseBoolean(city.get("locked").toString()) ? "locked" : "unlocked";
                String representation = city.get("name") + ": " + status + " id: " + city.get("id");
                cityView.add(representation);
            }

            result.setResponseContent(parsedParameters.get("responseContent").toString());
            result.setRecordList(cityView);
            String requestStatus = parsedParameters.get("requestOk").toString();
            result.setRequestOk(Boolean.parseBoolean(requestStatus));
        } catch (Exception e) {
            result.setResponseContent("Response has wrong format");
        }

        return result;
    }

}
