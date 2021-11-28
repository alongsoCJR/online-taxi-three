package com.mashibing.api.passenger.service;


import com.mashibing.internal.common.dto.ResponseResult;

public interface ServicePassengerUserService {

    ResponseResult login(String passengerPhone);
}
