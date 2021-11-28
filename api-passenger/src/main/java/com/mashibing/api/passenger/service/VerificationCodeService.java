package com.mashibing.api.passenger.service;


import com.mashibing.internal.common.dto.ResponseResult;

public interface VerificationCodeService {

    ResponseResult send(String phoneNumber);

    ResponseResult verify(String phoneNumber, String code);
}
