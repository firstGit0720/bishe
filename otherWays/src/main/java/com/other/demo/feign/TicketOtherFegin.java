package com.other.demo.feign;

import com.other.demo.dto.ShowSeatDto;
import com.other.demo.dto.ShowTicketHestoryDto;
import com.other.demo.dto.TrainSeatMessageDto;
import com.other.demo.entity.IndentMessage;
import com.other.demo.entity.Train;
import com.other.demo.entity.TrainArrive;
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

    /**
     * 修改中间站点信息
     * @param trainArriveStr
     * @return
     */
    @PostMapping("/train/updateSpace")
    public boolean updateSpace(@RequestParam("trainMessage")String trainArriveStr);

    /**
     * 获取中间站点的详细信息
     * @param id
     * @return
     */
    @GetMapping("/train/getTrainArrive")
    public TrainArrive getTrainArrive(@RequestParam("id") long id);
    /**
     * 修改火车状态 0 正常 1 停运
     * @param trainId
     * @param status
     * @return
     */
    @PostMapping("/train/updateTrainSuccess")
    public boolean updateTrainTask(@RequestParam("trainId") long trainId , @RequestParam("status") int status);

    /**
     * 所有的订单
     * @param startTime
     * @param endTime
     * @param aoData
     * @return
     */
    @GetMapping("/userother/allIndent")
    public String getAllIndent(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("aoData") String aoData);
    /**
     * 所有的退票和改签
     * @param
     * @param aoData
     * @return
     */
    @GetMapping("/userother/allBackTicket")
    public String getAllBackTicket(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("aoData") String aoData);

    /**
     * 显示座位的信息
     * @param trainId
     * @return
     */
    @GetMapping("/train/getSeats")
    public List<ShowSeatDto> getSeats(@RequestParam("trainId") long trainId);
    /**
     * 修改座位信息
     * @param seatMessage
     * @return
     */
    @PostMapping("/train/updateSeat")
    public boolean updateSeatMessage(@RequestParam("seatMessage") String seatMessage);

    /**
     * 添加座位信息
     * @param date
     * @return
     */
    @PostMapping("/train/addSeat")
    public boolean addTrainArrive(@RequestParam("seatMessage") String date);

    /**
     * 修改站点状态
     * @param id
     * @return
     */
    @PostMapping("/train/updateArriveStatus")
    public boolean updateSeatStatus(@RequestParam("id") long id);
}
