package com.buyticket.demo.fegin;

import com.buyticket.demo.dto.TrainSeatMessageDto;
import com.buyticket.demo.entity.Train;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用于查询火车id
 */
@FeignClient("TICKETOTHER-CLIENT")
@RequestMapping("/train")
public interface TicketOtherFegin {
    @GetMapping("/selectTrainId")
    public Long selectTrainId(@RequestParam("trainCard") String trainCard);
    @GetMapping("/getTrainMessage")
    public Train selectTrain(@RequestParam("trainCard") String trainCard);
    /**
     * 获取座位信息
     * @param trainId
     * @return
     */
    @GetMapping(value = "/getTrainSeats" )
    public List<TrainSeatMessageDto> getTrainSeats(@RequestParam("trainId") Long trainId, @RequestParam("from") String from, @RequestParam("arrive") String arrive);

    /**
     * 更具火车id查找
     * @param trainId
     * @return
     */
    @GetMapping(value = "/getSelectById")
    public Train selectTrainById(@RequestParam("trainId") Long trainId);

}
