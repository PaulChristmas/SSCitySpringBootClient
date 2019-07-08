package com.ss.cities.client.controllers;

import com.ss.cities.client.utils.CityServerRequest;
import com.ss.cities.client.services.SubmitActionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class CityClientRequestController {
    @Autowired
    private SubmitActionServiceImpl submitService;

    @GetMapping(value={"", "/", "/cityServerRequest"})
    public String cityServerRequestForm(Model model) {
        model.addAttribute("cityServerRequest", new CityServerRequest());
        return "cityServerRequest";
    }

    @PostMapping("/cityServerRequest")
    public String cityServerRequestSubmit(@ModelAttribute CityServerRequest cityServerRequest) {
        return submitService.submitAction(cityServerRequest);
    }

}