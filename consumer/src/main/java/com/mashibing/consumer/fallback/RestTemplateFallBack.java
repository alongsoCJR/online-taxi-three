package com.mashibing.consumer.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFallBack {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "back")
    public String hello() {
        // 自动处理URL
        String url = "http://api-passenger/hello";
        String object = restTemplate.getForObject(url, String.class);

        return object;

    }


    public String back() {

        return "wuwuwu~baocuole...";
    }

}