package com.mashibing.api.passenger.controller;

import com.mashibing.api.passenger.domain.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("getHi")
    public String getHi() {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "Hi,My Port is " + port;
    }


    @RequestMapping("getMap")
    public Map<String, String> getMap() {
        return Collections.singletonMap("id", "100");
    }


    @RequestMapping("getObj")
    public Person getObj() {
        return new Person("alongso", 25);
    }

    @RequestMapping("getObj2")
    public Person getObj2(String name) {
        return new Person(name, 25);
    }

    @PostMapping("/postParam")
    public Person postParam(@RequestBody String name) {
        System.out.println("name:" + name);
        Person person = new Person();
        person.setAge(100);
        person.setName("xiaoming" + name);
        return person;
    }


}

