package com.mashibing.consumer.controller;

import com.mashibing.api.base.dto.PersonDTO;
import com.mashibing.consumer.service.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private ConsumerApi consumerApi;

    @GetMapping("/getUserNameById")
    public String getUserNameById(@RequestParam("id") Integer id) {
        return consumerApi.getUserNameById(id);
    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return consumerApi.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id, String name) {
        System.out.println(id);
        return consumerApi.getMap2(id, name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(Integer id, String name) {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        map.put("name", name);
        System.out.println(map);
        return consumerApi.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return consumerApi.postMap(map);
    }

    @PostMapping("/postPerson")
    public PersonDTO postPerson(@RequestBody PersonDTO personDTO) {
//        PersonDTO personDTO = new PersonDTO(name, age);
        return consumerApi.postPerson(personDTO);
    }
}