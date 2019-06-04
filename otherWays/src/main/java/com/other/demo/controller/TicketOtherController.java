package com.other.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.other.demo.dto.ShowSeatDto;
import com.other.demo.dto.TrainSeatMessageDto;
import com.other.demo.entity.IndentMessage;
import com.other.demo.entity.Train;
import com.other.demo.entity.TrainArrive;
import com.other.demo.feign.TicketOtherFegin;
import org.apache.ibatis.annotations.Param;
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
    public boolean updateTicketStatus(@RequestBody long id) throws Exception {
        IndentMessage indentMessage = this.getMessage(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date trainStart = null;
        String trainStartTime = indentMessage.getTrainStartTime();
        try {
            trainStart = sdf.parse(trainStartTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        //如果改签时间为火车行驶前30分钟系统不给予退票服务，需人工退票
        long num = 30 * 60 * 1000;
        if((now.getTime() - num) < trainStart.getTime()){
            System.out.println(id);
            return ticketOtherFegin.updateTicket(id);
        }else{
//            throw new Exception("改签时间离出发不到30分钟，请到人工服务台处理");
            return false;
        }
    }

    /**
     * 退票
     * @param data
     * @return
     */
    @PostMapping(value = "/exitTicket" , consumes = {CONTENT_TYPE})
    public boolean exitTicket(@RequestBody String data) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(data);
        Long id = jsonObject.getLong("id");
        IndentMessage indentMessage = this.getMessage(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date trainStart = null;
        String trainStartTime = indentMessage.getTrainStartTime();
        try {
            trainStart = sdf.parse(trainStartTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        //如果改签时间为火车行驶前30分钟系统不给予退票服务，需人工退票
        long num = 30 * 60 * 1000;
        System.out.println(now.getTime() - num);
        System.out.println(trainStart.getTime());
        if((now.getTime() - num) < trainStart.getTime()){
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
//        System.out.println("传过来的id是" + id);
        return ticketOtherFegin.getMessage(id);
    }

    /**
     * 修改中间站点信息
     * @param trainArriveStr
     * @return
     */
    @PostMapping(value = "/updateSpace" , consumes = {CONTENT_TYPE})
    public boolean updateSpace(@RequestBody String trainArriveStr){
        return ticketOtherFegin.updateSpace(trainArriveStr);
    }

    /**
     * 获取中间站点的详细信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getTrainArrive" , consumes = {CONTENT_TYPE})
    public TrainArrive getTrainArrive(@RequestParam("id") long id){
        return ticketOtherFegin.getTrainArrive(id);
    }

    /**
     * 修改火车状态 0 正常 1 停运
     * @return
     */
    @PostMapping(value = "/updateTrainSuccess",consumes = {CONTENT_TYPE})
    public boolean updateTrainTask(@RequestBody String data){
        JSONObject obj = JSON.parseObject(data);
        long trainId = obj.getLong("trainId");
        Train train = ticketOtherFegin.selectTrainById(trainId);
        int status = train.getTrainStatus();
        int newStatus = status == 0 ? 1 : 0;
        return ticketOtherFegin.updateTrainTask(trainId,newStatus);
    }

    /**
     * 所有的订单

     * @param startTime
     * @param endTime
     * @param aoData
     * @return
     */
    @GetMapping(value = "/allIndent",consumes = {CONTENT_TYPE})
    public String getAllIndent(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("aoData") String aoData){
        return ticketOtherFegin.getAllIndent( startTime, endTime, aoData);
    }

    /**
     * 所有的退票和改签
     * @param
     * @param aoData
     * @return
     */
    @GetMapping(value = "/allBackTicket" , consumes = {CONTENT_TYPE})
    public String getAllBackTicket(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("aoData") String aoData){
        return ticketOtherFegin.getAllBackTicket(startTime, endTime, aoData);
    }

    /**
     * 获取座位信息
     * @param trainId
     * @return
     */
    @GetMapping(value = "/getSeats" , consumes = {CONTENT_TYPE})
    public List<ShowSeatDto> showSeats (@RequestParam("trainId") long trainId){
        return ticketOtherFegin.getSeats(trainId);
    }
    @PostMapping(value = "/updateSeat" , consumes = {CONTENT_TYPE})
    public boolean updateSeats(@RequestBody String data){
        return ticketOtherFegin.updateSeatMessage(data);
    }

    /**
     * 添加座位信息
     * @param date
     * @return
     */
    @PostMapping(value = "/addSeat",consumes = {CONTENT_TYPE})
    public boolean addTrainArrive(@RequestBody String date){
        return ticketOtherFegin.addTrainArrive(date);
    }

    /**
     * 修改站点状态
     * @param id
     * @return
     */
    @PostMapping(value = "/updateArriveStatus",consumes = {CONTENT_TYPE})
    public boolean updateSeatStatus(@RequestBody String id){
        JSONObject jsonObject = JSONObject.parseObject(id);
        long seatId = jsonObject.getLong("id");
        return ticketOtherFegin.updateSeatStatus(seatId);
    }


}
