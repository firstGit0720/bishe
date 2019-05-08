package com.other.demo.controller;

import com.other.demo.dto.TrainSeatMessageDto;
import com.other.demo.entity.IndentMessage;
import com.other.demo.entity.Train;
import com.other.demo.feign.TicketOtherFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/ticketother")
@RestController
@CrossOrigin
public class TicketOtherController {
    @Autowired
    private TicketOtherFegin ticketOtherFegin;

    public static final String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 添加车票信息
     * @param data
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST ,consumes = {CONTENT_TYPE})
    public boolean addTrainMessage(@RequestBody String data) throws ParseException {
        System.out.print(data);
        return ticketOtherFegin.addTrainMessage(data);
    }

    /**
     * 获取座位信息
     * @param trainId
     * @return
     */
    @GetMapping(value = "/getTrainSeats" ,consumes = {CONTENT_TYPE})
    public List<TrainSeatMessageDto> getTrainSeats(@RequestParam("trainId") Long trainId,@RequestParam("from") String from,@RequestParam("arrive") String arrive){
        return ticketOtherFegin.getTrainSeats(trainId,from,arrive);
    }

    /**
     * 根据火车编号搜索
     * @param trainCard
     * @return
     */
    @GetMapping(value = "/getTrainMessage",consumes = {CONTENT_TYPE})
    public Train selectTrain(@RequestParam("trainCard") String trainCard){
        return ticketOtherFegin.selectTrain(trainCard);
    }

    /**
     * 根据id获取火车信息
     * @param trainid
     * @return
     */
    @GetMapping(value = "/getSelectById" , consumes = {CONTENT_TYPE})
    public Train selectTrainById(@RequestParam("trainId") Long trainid){
        return ticketOtherFegin.selectTrainById(trainid);
    }

    /**
     * 获取所有的火车信息
     * @return
     */
    @GetMapping(value = "/getAlltrain" , consumes = {CONTENT_TYPE})
    public String  getAllTrain(@RequestParam("aoData") String aoData){
      return ticketOtherFegin.getAllTrain(aoData);
    }

    /**
     * 获取历史订单行程
     * @param userId
     * @param aoData
     * @return
     */
    @GetMapping(value = "/getUserHistroy" , consumes = {CONTENT_TYPE})
    public String showUserHistroy(@RequestParam("userId") Long userId,@RequestParam("aoData") String aoData){
        return ticketOtherFegin.showUserHistroy(userId,aoData);
    }

    /**
     * 获取未完成订单行程
     * @param userId
     * @param aoData
     * @return
     */
    @GetMapping(value = "/getUserFuture" , consumes = {CONTENT_TYPE})
    public String showUserFuture(@RequestParam("userId") Long userId,@RequestParam("aoData") String aoData){
        return ticketOtherFegin.showFuture(userId,aoData);
    }


    /**
     * 获取火车id
     * @param trainCard
     * @return
     */
    @GetMapping(value = "/selectTrainId" , consumes = {CONTENT_TYPE})
    public Long selectTrainId(@RequestParam("trainCard") String trainCard){
        return ticketOtherFegin.selectTrainId(trainCard);
    }

    /**
     * 修改火车信息
     * @param train
     * @return
     */
    @PostMapping(value = "/updateTrain",consumes = {CONTENT_TYPE})
    public boolean updateTrain( @RequestBody String train){
        System.out.print("前段传过来的数据是" + train);
        return ticketOtherFegin.updateTrain(train);
    }

    /**
     * 改签
     * @param id
     * @return
     */
    @PostMapping(value = "/updateTicketStatus" , consumes = {CONTENT_TYPE})
    public boolean updateTicketStatus(@RequestBody long id){
        System.out.println(id);
        return ticketOtherFegin.updateTicket(id);
    }

    /**
     * 退票
     * @param id
     * @return
     */
    @PostMapping(value = "/exitTicket" , consumes = {CONTENT_TYPE})
    public boolean exitTicket(@RequestBody long id){
        IndentMessage indentMessage = this.getMessage(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date trainStart = null;
        try {
            trainStart = sdf.parse(indentMessage.getTrainStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        //如果改签时间为火车行驶前30分钟系统不给予退票服务，需人工退票
        long num = 30 * 60 * 1000;
        if(now.getTime() - num > trainStart.getTime()){
            System.out.println(id);
            return ticketOtherFegin.exitTicket(id);
        }else{
            return false;
        }

    }

    /**
     * 获取订单信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getMessage" , consumes = {CONTENT_TYPE})
    public IndentMessage getMessage(@RequestParam("id") long id){
        System.out.println("传过来的id是" + id);
        return ticketOtherFegin.getMessage(id);
    }

}
