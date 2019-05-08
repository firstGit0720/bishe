package com.buyticket.demo.service.impl;

import com.buyticket.demo.dto.UserDto;
import com.buyticket.demo.fegin.RedisFegin;
import com.buyticket.demo.service.UserLoginCacheService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginCacheServiceImpl implements UserLoginCacheService {

    private  static  final Logger logger = Logger.getLogger(UserLoginCacheServiceImpl.class);

    @Autowired
    private RedisFegin redisFegin;

    /**
     * 从缓存中获取用户信息，用于权限管理
     * @return
     */
    @Override
    public UserDto getUserFromCache(String username) {
        return redisFegin.getUserFromCache(username);
    }
}
