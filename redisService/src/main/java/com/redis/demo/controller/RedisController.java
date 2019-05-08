package com.redis.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.redis.demo.dto.UserDto;
import com.redis.demo.redisUtil.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private Redis redis;

    /**
     * 将用户添加到缓存
     * @param usermessage
     * @return
     */
    @PostMapping("/addUserToCache")
    public boolean addUserToCache(@RequestParam("userMessage") String usermessage){
        UserDto user = JSONObject.parseObject(usermessage,UserDto.class);
        boolean status = redis.addUserToCache(user);
        redis.close();
        return status;
    }

    /**
     * 从缓存中获取用户信息
     * @return
     */
    @GetMapping("/getUserMessage")
    public UserDto getUserFromCache(@RequestParam("username") String username){
        UserDto userDto = redis.getUserFromCache(username);
        redis.close();
        return userDto;
    }

    /**
     * 从缓冲中清空数据，退出登录时
     * @param username
     * @return
     */
    @PostMapping("/clearUser")
    public boolean clearUserFromCache(@RequestParam("username") String username){
        boolean status =  redis.cleanUserFromCache(username);
        redis.close();
        return status;
    }

}
