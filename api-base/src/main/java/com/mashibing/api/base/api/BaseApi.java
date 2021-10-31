package com.mashibing.api.base.api;

import com.mashibing.api.base.dto.PersonDTO;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/base")
public interface BaseApi {

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description 方法实现说明
     * @Date 2021-10-30 11:56
     * @Param []
     **/
    @GetMapping("/alive")
    String alive();

    /**
     * @return java.lang.String
     * @Author Jianrong.Chen
     * @Description 方法实现说明
     * @Date 2021-10-30 11:56
     * @Param [id]
     **/
    @GetMapping("/getUserNameById")
    String getUserNameById(@RequestParam Integer id);


    @PostMapping("/postPerson")
    PersonDTO postPerson(@RequestBody PersonDTO person);


}