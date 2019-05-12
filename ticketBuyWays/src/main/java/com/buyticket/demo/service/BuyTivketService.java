package com.buyticket.demo.service;

import com.buyticket.demo.dto.BuyTicketDto;
import com.buyticket.demo.dto.TicketShowDto;
import com.buyticket.demo.dto.TrainDto;
import com.buyticket.demo.entity.TrainArrive;
import com.buyticket.demo.entity.UserTickerMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 买票的service层
 */

public interface BuyTivketService {

    /**
     * 查询火车票，并封装到dto对象,根据出发地和目的地
     * @param from
     * @param arrive
     * @param start
     * @param end
     * @return
     */
    public List<TrainDto> allTrain(String from, String arrive, String date,Integer start, Integer end) throws UnsupportedEncodingException;

    /**
     * 买票
     * @param ticket
     * @return
     */
    public boolean buyTicket(BuyTicketDto ticket) throws UnsupportedEncodingException;

    /**
     *更具火车车次获取中间站点
     * @param trainCard
     * @param start
     * @param end
     * @return
     */
    public List<TicketShowDto> allTrainSpaces(String trainCard,Integer start,Integer end);

    /**
     *更具火车id查询中间站点
     * @param trainId
     * @param start
     * @param end
     * @return
     */
    public List<TicketShowDto> allTrainSpaces(Long trainId,Integer start,Integer end);

    /**
     * 获取中间站点信息
     * @param trainId
     * @return
     */
    public List<TrainArrive> allTrainArrives(Long trainId);

    /**
     * 更具id查询站点信息
     * @param id
     * @return
     */
    public TrainArrive selectArriveById(long id);
}
