package com.mashibing.consumer.fallback;

import com.mashibing.api.base.dto.PersonDTO;
import com.mashibing.consumer.service.ConsumerApi;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class ConsumerFallBack implements ConsumerApi {
    @Override
    public Map<Integer, String> getMap(Integer id) {
        return Collections.singletonMap(1, "getMap降级");
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public String alive() {
        return "降级了";
    }

    @Override
    public String hello() {
        return null;
    }

    @Override
    public String getUserNameById(Integer id) {
        return null;
    }

    @Override
    public PersonDTO postPerson(PersonDTO person) {
        return null;
    }
}