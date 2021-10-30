package com.mashibing.consumer.service;

import com.mashibing.api.base.api.BaseApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "api-passager")
public interface ConsumerApi extends BaseApi {

}