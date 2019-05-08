package com.buyticket.demo.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("OTHERWAYS-CLIENT")
public interface OtherWaysFegin {

    @GetMapping("/otherWays/getUserId")
    public Long getUserId(@RequestParam("username") String username);

}
