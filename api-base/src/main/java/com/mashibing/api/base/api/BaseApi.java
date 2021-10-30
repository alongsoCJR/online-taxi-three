package com.mashibing.api.base.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/base")
public interface BaseApi {

    /**
     * @Author Jianrong.Chen
     * @Description 方法实现说明
     * @Date 2021-10-30 11:56
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/alive")
    String alive();

    /**
     * @Author Jianrong.Chen
     * @Description 方法实现说明
     * @Date 2021-10-30 11:56
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/getUserNameById")
    String getUserNameById(@RequestParam Integer id);

}