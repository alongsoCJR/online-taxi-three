package com.mashibing.api.passenger.service;


import com.mashibing.internal.common.dto.ResponseResult;

public interface ServiceVerificationCodeRestTemplateService {

    ResponseResult generatorCode(int identity, String phoneNumber);

    ResponseResult verifyCode(int identity, String phoneNumber, String code);
}
