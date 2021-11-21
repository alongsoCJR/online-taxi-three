package com.mashibing.service.verification.code.service;


import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.dto.serviceverificationcode.VerifyCodeResponse;

public interface VerifyCodeService {

    ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber);
}
