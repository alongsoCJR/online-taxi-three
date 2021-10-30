package com.mashibing.consumer.controller;

import com.mashibing.consumer.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private ConsumerApi baseApi;

    @GetMapping("/base")
    public String alive() {
        return baseApi.alive();
    }
}