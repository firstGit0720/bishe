package com.other.demo.feign;

import com.other.demo.dto.ShowTicketHestoryDto;
import com.other.demo.dto.TrainSeatMessageDto;
import com.other.demo.entity.IndentMessage;
import com.other.demo.entity.Train;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@FeignClient("TICKETOTHER-CLIENT")
public interface TicketOtherFegin {
    /**
     * 添加车票信息
     * @param data
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/train/add" , method = RequestMethod.POST )
    public boolean addTrainMessage(@RequestBody String data) throws ParseException;

    /**
     * 获取座位信息
     * @param trainId
     * @return
     */
    @GetMapping(value = "/train/getTrainSeats" )
    public List<TrainSeatMessageDto> getTrainSeats(@RequestParam("trainId") Long trainId,@RequestParam("from") String from,@RequestParam("arrive") String arrive);

    /**
     * 根据火车编号搜索
     * @param trainCard
     * @return
     */
    @GetMapping(value = "/train/getTrainMessage")
    public Train selectTrain(@RequestParam("trainCard") String trainCard);

    /**
     * 根据火车id搜索
     * @param trainId
     * @return
     */
    @GetMapping(value = "/train/getSelectById")
    public Train selectTrainById(@RequestParam("trainId") Long trainId);


    /**
     * 获取所有的火车信息
     * @return
     */
    @GetMapping("/train/getAllTicker")
    public String  getAllTrain(@RequestParam("aoData") String aoData);

    /**
     * 获取未完成的形成订单
     * @param userId
     * @param aoData
     * @return
     */
    @GetMapping("/userother/getUserFuture")
    public String showFuture(@RequestParam("userId") Long userId,@RequestParam("aoData") String aoData);
    /**
     * 获取已完成的行程订单
     * @param userId
     * @return
     */
    @GetMapping("/userother/getUserHistroy")
    public String showUserHistroy(@RequestParam("userId") Long userId,@RequestParam("aoData") String aoData);

    /**
     * 根据火车车次，获取火车id
     * @param trainCard
     * @return
     */
    @GetMapping("/userother/selectTrainId")
    public Long selectTrainId(@RequestParam("trainCard") String trainCard);

    /**
     * 修改火车信息
     * @param trainMessage
     * @return
     */
    @PostMapping("/train/updateTrain")
    public boolean updateTrain(@RequestParam("trainMessage") String trainMessage);

    /**
     * 改签
     * @param id
     * @return
     */
    @PostMapping("/userother/updateTicket")
    public boolean updateTicket(@RequestParam("id")long id);
    /**
     * 获取订单信息
     * @param id
     * @return
     */
    @GetMapping("/userother/getMessage")
    public IndentMessage getMessage(@RequestParam("id")long id);
    /**
     * 退票
     * @param id
     * @return
     */
    @PostMapping("/exitTicket")
    public boolean exitTicket(@RequestParam("id")long id);
}
