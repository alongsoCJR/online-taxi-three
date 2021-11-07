package com.mashibing.api.passenger.controller;


import com.mashibing.api.base.api.BaseApi;
import com.mashibing.api.base.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class BaseController implements BaseApi {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Value("${server.port}")
    private String port;

    @Value("${sleep.time}")
    private Long sleepTime;

    @Override
    public String alive() {
//        try {
//            TimeUnit.SECONDS.sleep(sleepTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Date:" + new Date() + "===port:" + port + ",第几次调用：" + atomicInteger.incrementAndGet());
        return "port:" + port;
    }

    @Override
    public String hello() {
        int i = 1 / 0;
        return "hello";
    }

    @Override
    public String getUserNameById(Integer id) {
        return "along" + id;
    }

    @Override
    public PersonDTO postPerson(PersonDTO person) {
        System.out.println(person);
        return person;
    }


    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        // TODO Auto-generated method stub
        System.out.println(id);
        int i = 1 / 0;
        return Collections.singletonMap(id, "mmeme");
    }

    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id, String name) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

}