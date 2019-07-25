package com.ss.cities.client;

import com.ss.cities.client.controllers.CityClientRequestController;
import com.ss.cities.client.services.SubmitActionServiceImpl;
import com.ss.cities.client.utils.CityServerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.ss.server.lib.JSONTemplateResponse;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @MockBean
    private SubmitActionServiceImpl serviceCityAction;

    @Autowired
    private CityClientRequestController controller;

    @Test
    public void t1() throws Exception {
        //serviceCityAction = new SubmitActionServiceImpl();
        CityServerRequest request = new CityServerRequest();
        controller.cityServerRequestSubmit(request);
        System.out.println(controller.cityServerRequestSubmit(request));
        //CityServerRequest request = new CityServerRequest();
        String response = serviceCityAction.submitAction(request);
        System.out.println(response);
        assertThat(response.equals("result"));
    }







}
