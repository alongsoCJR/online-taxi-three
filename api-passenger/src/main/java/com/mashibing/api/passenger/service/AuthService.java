package com.mashibing.api.passenger.service;


import com.mashibing.internal.common.dto.ResponseResult;

public interface AuthService {
    ResponseResult auth(String passengerPhone, String code);
}
