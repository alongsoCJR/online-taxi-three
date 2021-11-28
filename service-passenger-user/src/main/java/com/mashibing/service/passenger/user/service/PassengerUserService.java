package com.mashibing.service.passenger.user.service;


import com.mashibing.internal.common.dto.ResponseResult;

public interface PassengerUserService {

    ResponseResult login(String passengerPhone);

    ResponseResult logout(String token);
}
