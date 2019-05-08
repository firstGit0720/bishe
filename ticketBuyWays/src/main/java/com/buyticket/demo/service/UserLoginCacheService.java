package com.buyticket.demo.service;

import com.buyticket.demo.dto.UserDto;
import com.buyticket.demo.entity.User;

/**
 * 将用户登录信息添加到缓存
 */
public interface UserLoginCacheService {

    public UserDto getUserFromCache(String username);


}
