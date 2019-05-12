package com.other.demo.controller;

import com.other.demo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @GetMapping(value = "/statistics",consumes = {CONTENT_TYPE})
    public Map<String,Integer> statisticsNum(){
        Map<String,Integer> map  = new HashMap<String,Integer>();
        int trainNum = statisticsService.trainsNum();
        int userNum = statisticsService.userNum();
        int usertop = statisticsService.userTypeNum(0);
        int userCenter = statisticsService.userTypeNum(1);
        int userBottom = statisticsService.userTypeNum(2);
        int indents = statisticsService.indentNum();
        int indentsOK = statisticsService.indentTypeNumIsOk(0);
        int indentsNo = statisticsService.indentTypeNumIsOk(1);
        map.put("trainNum",trainNum);
        map.put("userNum",userNum);
        map.put("usertop" , usertop);
        map.put("userCenter" , userCenter);
        map.put("userBottom",userBottom);
        map.put("indents",indents);
        map.put("indentsOK",indentsOK);
        map.put("indentsNo",indentsNo);

        return map;
    }

}
