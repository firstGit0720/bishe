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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
//        List<IndentMessage> lists = ticketOtherFegin.allIndents();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = sdf.format(date);
        String nowDate = dateStr.substring(0,dateStr.length()-3);
        System.out.println("现在的时间为：" + nowDate);
        System.out.println(ticketOtherFegin.updateSuccess(1,nowDate));
        //声明线程池
//        ExecutorService exc = Executors.newFixedThreadPool(lists.size());

      /*  System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
        Thread.sleep(1000 * 10);*/
    }


}
