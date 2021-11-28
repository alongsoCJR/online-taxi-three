package com.mashibing.api.passenger.service;


import com.mashibing.internal.common.dto.ResponseResult;

public interface ServiceSmsRestTemplateService {

    ResponseResult sendSms(String phoneNumber, String code);
}
