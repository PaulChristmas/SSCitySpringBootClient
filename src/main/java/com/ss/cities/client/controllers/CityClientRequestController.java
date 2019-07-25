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

    @GetMapping(value={"", "/", "/cityServerRequest"})
    public String cityServerRequestForm(Model model) {
        model.addAttribute("cityServerRequest", new CityServerRequest());
        return "cityServerRequest";
    }

    @ApiOperation(value = "Список заданных городов")
    @RequestMapping("/GET")
    public String cityServerRequestGet(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doGet(cityServerRequest);
    }

    @ApiOperation(value = "Поиск города по имени")
    @RequestMapping("/FIND")
    public String cityServerRequestFind(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doFind(cityServerRequest);
    }

    @ApiOperation(value = "Изменение имени города (если он существует и не закрыт для изменений)")
    @RequestMapping("/UPDATE")
    public String cityServerRequestUpdate(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doUpdate(cityServerRequest);
    }

    @ApiOperation(value = "Удаление города по имени (если он существует и не закрыт для изменений)")
    @RequestMapping("/DELETE")
    public String cityServerRequestDelete(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doDelete(cityServerRequest);
    }

    @ApiOperation(value = "Добавление нового города")
    @RequestMapping("/POST")
    public String cityServerRequestPost(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.doPost(cityServerRequest);
    }

}