package com.other.demo.controller;

import com.other.demo.dto.BuyTicketDto;
import com.other.demo.feign.TicketBuyFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketBuy")
@CrossOrigin
public class TicketBuyController {
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";

    @Autowired
    private TicketBuyFegin ticketBuyFegin;

    /**
     * 火车票查询
     * @param from
     * @param arrive
     * @param aoData
     * @return
     */
    @GetMapping(value = "/selectTrainTickets" , consumes = {CONTENT_TYPE})
    public String selectTrainTicket(@RequestParam("from") String from , @RequestParam("arrive") String arrive,@RequestParam("date") String date, @RequestParam("aoData") String aoData){
        return ticketBuyFegin.selectTrainTicket(from, arrive,date, aoData);
    }

    /**
     * 根据火车编号获取中间站点
     * @param trainCard
     * @param aoData
     * @return
     */
    @GetMapping(value = "/getTrainMessage",consumes = {CONTENT_TYPE})
    public String allSpace(@RequestParam("trainCard") String trainCard,@RequestParam("aoData") String aoData){
        return ticketBuyFegin.allSpace(trainCard, aoData);
    }

    /**
     * 买票
     * @param buyTicketDao
     * @return
     */
    @PostMapping(value = "/buyTicket", consumes = {CONTENT_TYPE})
    public String buyTicket(@RequestBody String buyTicketDao){
        String message = ticketBuyFegin.buyTicket(buyTicketDao);
        System.out.println(message);
        return message;
    }

    /**
     * 更具火车id获取中间站点
     * @param trainId
     * @param aoData
     * @return
     */
    @GetMapping(value = "/getArriveByTrainId" , consumes = {CONTENT_TYPE})
    public String getArriveMessage(@RequestParam("trainId") Long trainId,@RequestParam("aoData") String aoData){
        return ticketBuyFegin.getArriveMessage(trainId,aoData);
    }

}
