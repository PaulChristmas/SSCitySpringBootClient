package com.ss.cities.client.controllers;

import com.ss.cities.client.utils.CityServerRequest;
import com.ss.cities.client.services.SubmitActionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@Api(value = "/api")
public class CityClientRequestController {
    @Autowired
    private SubmitActionServiceImpl submitService;

    @RequestMapping(value={"", "/", "/cityServerRequest"}, method = RequestMethod.GET)
    public String cityServerRequestForm(Model model) {
        model.addAttribute("cityServerRequest", new CityServerRequest());
        return "cityServerRequest";
    }

    @ApiOperation(value = "Список заданных городов")
    @RequestMapping(value = "/GET", method = RequestMethod.POST)
    public String cityServerRequestGet(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doGet(cityServerRequest);
    }

    @ApiOperation(value = "Поиск города по имени")
    @RequestMapping(value = "/FIND", method = RequestMethod.POST)
    public String cityServerRequestFind(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doFind(cityServerRequest);
    }

    @ApiOperation(value = "Изменение имени города (если он существует и не закрыт для изменений)")
    @RequestMapping(value = "/UPDATE", method = RequestMethod.POST)
    public String cityServerRequestUpdate(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doUpdate(cityServerRequest);
    }

    @ApiOperation(value = "Удаление города по имени (если он существует и не закрыт для изменений)")
    @RequestMapping(value = "/DELETE", method = RequestMethod.POST)
    public String cityServerRequestDelete(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doDelete(cityServerRequest);
    }

    @ApiOperation(value = "Добавление нового города")
    @RequestMapping(value = "/POST", method = RequestMethod.POST)
    public String cityServerRequestPost(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doPost(cityServerRequest);
    }

}