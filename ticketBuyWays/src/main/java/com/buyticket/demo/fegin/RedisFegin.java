package com.buyticket.demo.fegin;

import com.buyticket.demo.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REDISSERVICE-CLIENT")
@RequestMapping("/redis")
public interface RedisFegin {

    @PostMapping("/addUserToCache")
    public boolean addUserToCache(@RequestParam("userMessage") String usermessage);
    @GetMapping("/getUserMessage")
    public UserDto getUserFromCache(@RequestParam("username") String username);
    @PostMapping("/clearUser")
    public boolean clearUserFromCache(@RequestParam("username") String username);
}
