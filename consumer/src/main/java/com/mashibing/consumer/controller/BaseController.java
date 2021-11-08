package com.mashibing.consumer.controller;

import com.mashibing.consumer.fallback.RestTemplateFallBack;
import com.mashibing.consumer.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RefreshScope
public class BaseController {

    @Autowired
    private ConsumerApi baseApi;

    @Value("${myconfig}")
    private String myConfig;

    @Autowired
    private RestTemplateFallBack restApi;

    @GetMapping("/base")
    public String alive() {
        System.out.println("requestTime:" + new Date());
        return baseApi.alive();
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("requestTime:" + new Date());
        return restApi.hello();
    }

    @GetMapping("/config")
    public String config() {
        return myConfig;
    }
}