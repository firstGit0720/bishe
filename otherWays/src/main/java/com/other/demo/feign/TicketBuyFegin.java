package com.other.demo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("BUYTICKET-CLIENT")
@RequestMapping("/buyticket")
public interface TicketBuyFegin {

    /**
     * 查询火车票
     * @param from  出发地
     * @param arrive 目的地
     * @param date  时间
     * @param aoData
     * @return
     */
    @GetMapping("/selectTrainTickets")
    public String selectTrainTicket(@RequestParam("from") String from , @RequestParam("arrive") String arrive, @RequestParam("date") String date, @RequestParam("aoData") String aoData);

    /**
     * 火车火车的所有中间站点
     * @param trainCard
     * @param aoData
     * @return
     */
    @GetMapping(value = "/getTrainMessage")
    public String allSpace(@RequestParam("trainCard") String trainCard,@RequestParam("aoData") String aoData);

    /**
     * 购买火车票
     * @param buyTicketDao  购买火车票，需要的信息的json串
     * @return
     */
    @PostMapping("/buyTicket")
    public String buyTicket(@RequestParam("buyTicketMessage") String  buyTicketDao);

    /**
     * 根据火车id查询所有的火车站点信息
     * @param trainId
     * @param aoData
     * @return
     */
    @GetMapping("/getArriveByTrainId")
    public String getArriveMessage(@RequestParam("trainId") Long trainId,@RequestParam("aoData") String aoData);

}
