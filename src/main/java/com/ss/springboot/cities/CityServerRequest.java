package com.ss.springboot.cities;

import java.util.LinkedList;
import java.util.List;

public class CityServerRequest {

    private String cityName;
    private String updateName;
    private String action;
    private boolean locked;
    private String response;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    private List<String> cities;

    public CityServerRequest() {
        cityName = "";
        updateName = "";
        locked = false;
        action = "GET";
        cities = new LinkedList();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCityName() {
        return cityName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

}