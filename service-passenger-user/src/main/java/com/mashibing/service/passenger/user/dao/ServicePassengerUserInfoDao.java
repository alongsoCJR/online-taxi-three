package com.mashibing.service.passenger.user.dao;

import com.mashibing.service.passenger.user.entity.ServicePassengerUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServicePassengerUserInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ServicePassengerUserInfo record);

    int insertSelective(ServicePassengerUserInfo record);

    ServicePassengerUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServicePassengerUserInfo record);

    int updateByPrimaryKey(ServicePassengerUserInfo record);
}