package com.mashibing.service.passenger.user.service.impl;

import com.mashibing.internal.common.constant.RedisKeyPrefixConstant;
import com.mashibing.internal.common.dto.ResponseResult;
import com.mashibing.internal.common.util.JwtUtil;
import com.mashibing.service.passenger.user.dao.ServicePassengerUserInfoCustomDao;
import com.mashibing.service.passenger.user.entity.ServicePassengerUserInfo;
import com.mashibing.service.passenger.user.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class PassengerUserServiceImpl implements PassengerUserService {

    @Autowired
    private ServicePassengerUserInfoCustomDao servicePassengerUserInfoCustomDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult login(String passengerPhone) {
        // 如果数据库没有此用户，插库。可以用分布锁，也可以用 唯一索引。
        // 为什么此时用手机号？
        // 查出用户id
        ServicePassengerUserInfo passengerUserInfo = new ServicePassengerUserInfo();
        passengerUserInfo.setCreateTime(new Date());
        passengerUserInfo.setPassengerGender((byte) 1);
        passengerUserInfo.setPassengerName("");
        passengerUserInfo.setRegisterDate(new Date());
        passengerUserInfo.setUserState((byte) 1);
        passengerUserInfo.setPassengerPhone(passengerPhone);

        servicePassengerUserInfoCustomDao.insertSelective(passengerUserInfo);

        long passengerId = passengerUserInfo.getId();
        // 生成 token 的时候，如果要服务端控制，要把它 存到 redis中，在设置过期时间。

        String token = JwtUtil.createToken(passengerId + "", new Date());
        // 存入redis，设置过期时间。
        BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + passengerId);
        stringStringBoundValueOperations.set(token, 30, TimeUnit.DAYS);

        return ResponseResult.success(token);
    }

    @Override
    public ResponseResult logout(String passengerId) {
        // 存到过期的redis中。
        setExpireToken(passengerId);

        return ResponseResult.success("");
    }

    public int setExpireToken(String passengerId) {
        String key = RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + passengerId;
        redisTemplate.delete(key);
        return 1;
    }
}
