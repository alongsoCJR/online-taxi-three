package com.mashibing.api.passenger.service.impl;

import com.mashibing.api.passenger.service.AuthService;
import com.mashibing.api.passenger.service.ServicePassengerUserService;
import com.mashibing.api.passenger.service.ServiceVerificationCodeRestTemplateService;
import com.mashibing.internal.common.constant.CommonStatusEnum;
import com.mashibing.internal.common.constant.IdentityConstant;
import com.mashibing.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;

    @Autowired
    private ServicePassengerUserService servicePassengerUserService;

    @Override
    public ResponseResult auth(String passengerPhone, String code) {
        // 验证验证码：
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.verifyCode(IdentityConstant.PASSENGER,passengerPhone,code);
        if (responseResult.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResult.fail("登录失败：验证码校验失败");
        }

        // 用户登录
        responseResult = servicePassengerUserService.login(passengerPhone);
        if (responseResult.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResult.fail("登录失败：登录失败");
        }

        return responseResult;
    }

}
