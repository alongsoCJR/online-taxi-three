package com.mashibing.service.verification.code.service;


import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.dto.serviceverificationcode.response.VerifyCodeResponse;

public interface VerifyCodeService {

    ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber);

    ResponseResult verify(int identity, String phoneNumber, String code);
}
