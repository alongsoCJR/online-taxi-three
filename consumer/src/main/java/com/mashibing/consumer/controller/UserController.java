package com.mashibing.consumer.controller;

import com.mashibing.consumer.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ConsumerApi userApi;

    @GetMapping("/getUserNameById")
    public String getUserNameById(@RequestParam("id") Integer id) {
        return userApi.getUserNameById(id);
    }
}