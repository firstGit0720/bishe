package com.other.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.other.demo.entity.IndentMessage;
import com.other.demo.feign.TicketOtherFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时器,修改火车票的状态
 */
@Component
@EnableScheduling
@EnableAsync
public class TimedTaskUtil {
    @Autowired
    private TicketOtherFegin ticketOtherFegin;

    private static final long TIME = 1000 * 60;//一分钟
    @Async
    @Scheduled(fixedDelay = TIME)  //间隔1秒
    public void first() throws InterruptedException {
        System.out.println("定时任务启动");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = sdf.format(date);
        String nowDate = dateStr.substring(0,dateStr.length()-3);
        System.out.println("现在的时间为：" + nowDate);
        //修改行程完成状态
        System.out.println(ticketOtherFegin.updateSuccess(1,nowDate));
        //修改车票出票状态
        System.out.println(ticketOtherFegin.updateTicketSuccess(1,nowDate));
    }


}
