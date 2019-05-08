package com.other.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.other.demo.entity.User;
import com.other.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public static final String CONTENT_TYPE = "application/json; charset=utf-8";

    @PostMapping(value = "/updateMessage" , consumes = {CONTENT_TYPE})
    public boolean updateUserMessage(@RequestBody String data){
        System.out.print(data);
        User user = JSONObject.parseObject(data,User.class);
        return userService.updateUserMessage(user);
    }
}
