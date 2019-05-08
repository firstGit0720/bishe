package com.other.demo.feign;

import com.other.demo.dto.UserDto;
import com.other.demo.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REDISSERVICE-CLIENT")
public interface RedisServiceFeign {

    @RequestMapping(value = "/redis/addUserToCache" , method = RequestMethod.POST)
    public boolean addUserToCache(@RequestParam("userMessage") String userMessage);

    @RequestMapping(value = "/redis/getUserMessage",method = RequestMethod.GET)
    public UserDto getUserFromCache(@RequestParam("username") String username);

    @PostMapping(value = "/redis/clearUser")
    public boolean clearUserMessage(@RequestParam("username") String username);

}
