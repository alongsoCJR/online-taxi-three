package com.mashibing.api.passenger.controller;


import com.mashibing.api.base.api.BaseApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController implements BaseApi {

    @Override
    public String alive() {
        return "xxoo";
    }

    @Override
    public String getUserNameById(Integer id) {
        return "along" + id;
    }
}