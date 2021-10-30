package com.mashibing.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// 使用eureka只需要定义name的名称，不使用时只需要定义url,格式为 ip:port/
//@FeignClient(name = "api-passager")
//public interface UserApi {
//
//    @GetMapping("/alive")
//    String alive();
//}