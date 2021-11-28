package com.mashibing.internal.common.dto.serviceverificationcode.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author yueyi2019
 */
@Data
public class VerifyCodeRequest {

    private int identity;

    @Valid
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Max(value = 11, message = "手机号只能为{max}位")
    @Min(value = 11, message = "手机号只能为{min}位")
    private String phoneNumber;

    @NotBlank
    private String code;
}