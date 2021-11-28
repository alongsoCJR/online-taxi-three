package com.mashibing.api.passenger.controller;

import com.mashibing.api.passenger.request.ShortMsgRequest;
import com.mashibing.api.passenger.service.VerificationCodeService;
import com.mashibing.internal.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify-code")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public ResponseResult send(@RequestBody @Validated ShortMsgRequest request){
        return verificationCodeService.send(request.getPhoneNumber());
    }
}
