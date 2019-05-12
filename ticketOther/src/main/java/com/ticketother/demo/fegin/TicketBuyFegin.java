package com.ticketother.demo.fegin;

import com.ticketother.demo.entity.TrainArrive;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("BUYTICKET-CLIENT")
@RequestMapping("/buyticket")
public interface TicketBuyFegin {

    @GetMapping("/allTrainArrive")
    public List<TrainArrive> allTrainArrive(@RequestParam("trainId") Long trainId);
    /**
     * 获取站点信息
     * @param id
     * @return
     */
    @GetMapping("/getArrive")
    public TrainArrive selectArriveById(@RequestParam("id") long id);
}
