package com.mashibing.internal.common.util;

import com.mashibing.internal.common.constant.IdentityConstant;
import com.mashibing.internal.common.constant.RedisKeyPrefixConstant;

public abstract class RedisKeyUtil {


    /**
     * 根据身份类型生成对应的缓存key
     * @param identity
     * @return
     */
    public static String generateKeyPreByIdentity(int identity){
        String keyPre = "";
        if (identity == IdentityConstant.PASSENGER){
            keyPre = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PRE;
        }else if (identity == IdentityConstant.DRIVER){
            keyPre = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return keyPre;
    }
}