package com.other.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.other.demo.dto.RegisterDto;
import com.other.demo.entity.User;
import com.other.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 注册的controller
 */
@Controller
@CrossOrigin
public class RegisterController {
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    private UserService loginAndRegisterService;

    /**
     * 判断用户是否存在，存在为返回
     * @param username
     * @return
     */
    @RequestMapping("/isExist")
    @ResponseBody
    public boolean usernameIsExist(@RequestBody String username){
        User user = loginAndRegisterService.selectByUsername(username);
        return user != null ? true : false;
    }

    /**
     * 用户注册
     * @param data 用户注册传送对象
     * @return
     */
    @RequestMapping(value = "/register" , method = RequestMethod.POST , consumes = {CONTENT_TYPE})
    @ResponseBody
    public boolean register(@RequestBody  String data){
        RegisterDto registerDto = JSONObject.parseObject(data,RegisterDto.class);

        return  loginAndRegisterService.registerUser(registerDto);
    }

}
