package com.other.demo.util;

import com.other.demo.dto.UserDto;
import com.other.demo.feign.RedisServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义一个判断登录的方法，是否登录
 */
@Component
public class PathUtil {

    @Autowired
    private RedisServiceFeign redisServiceFeign;
    
    public boolean isLogin(String username){
        UserDto userDto = redisServiceFeign.getUserFromCache(username);
        return userDto != null ? true :false;
    }

}
