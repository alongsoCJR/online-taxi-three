package com.mashibing.api.passenger.service.impl;

import com.mashibing.api.passenger.service.ServiceSmsRestTemplateService;
import com.mashibing.api.passenger.service.ServiceVerificationCodeRestTemplateService;
import com.mashibing.api.passenger.service.VerificationCodeService;
import com.mashibing.internal.common.constant.CommonStatusEnum;
import com.mashibing.internal.common.constant.IdentityConstant;
import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.dto.serviceverificationcode.response.VerifyCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;

    @Autowired
    private ServiceSmsRestTemplateService serviceSmsRestTemplateService;

    @Override
    public ResponseResult send(String phoneNumber) {

        // 获取验证码
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.generatorCode(IdentityConstant.PASSENGER,phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if (responseResult.getCode() == CommonStatusEnum.SUCCESS.getCode()){
            JSONObject data = JSONObject.fromObject(responseResult.getData().toString());
            verifyCodeResponse = (VerifyCodeResponse)JSONObject.toBean(data,VerifyCodeResponse.class);

        }else {
            return ResponseResult.fail("获取验证码失败");
        }

        String code = verifyCodeResponse.getCode();

        ResponseResult result = serviceSmsRestTemplateService.sendSms(phoneNumber,code);
        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()){
            return ResponseResult.fail("发送短信 失败");
        }

        return ResponseResult.success("");

    }

    @Override
    public ResponseResult verify(String phoneNumber, String code) {

        return serviceVerificationCodeRestTemplateService.verifyCode(IdentityConstant.PASSENGER,phoneNumber,code);
    }

}
