package com.mashibing.service.verification.code.controller;

import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.dto.serviceverificationcode.request.VerifyCodeRequest;
import com.mashibing.service.verification.code.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify-code")
@Slf4j
public class VerifyCodeController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable("identity") int identity, @PathVariable("phoneNumber") String phoneNumber) {
        log.info("/generate/{identity}/{phoneNumber} ： 身份类型：" + identity + ",手机号：" + phoneNumber);
        return verifyCodeService.generate(identity, phoneNumber);
    }

    @PostMapping("/verify")
    public ResponseResult verify(@RequestBody VerifyCodeRequest request) {
        log.info("/verify-code/verify  request:" + JSONObject.fromObject(request));
        //获取手机号和验证码
        String phoneNumber = request.getPhoneNumber();
        int identity = request.getIdentity();
        String code = request.getCode();

        return verifyCodeService.verify(identity, phoneNumber, code);

    }
}
