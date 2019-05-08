package com.ticketother.demo.service;

import com.ticketother.demo.dto.TrainSeatMessageDto;
import com.ticketother.demo.entity.Train;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.List;

public interface TrainService {
    /**
     * 添加火车信息
     * @param message
     * @return
     * @throws ParseException
     */
    public boolean addTrainMessage(String message) throws ParseException;

    /**
     * 获取座位信息
     * @param trainId
     * @return
     */
    public List<TrainSeatMessageDto> getTrainSeats(Long trainId,String from,String arrive);

    /**
     * 查询火车信息更具火车编号
     * @param trainCard
     * @return
     */
    public Train selectTrain(String trainCard);

    /**
     * 查询火车信息更具火车编号
     * @param trainId
     * @return
     */
    public Train selectTrainById(Long trainId);

    /**
     * 获取所有的火车信息
     * @return
     */
    public List<Train> getAllTrain(Integer start,Integer end);
    /**
     * 修改火车信息
     * @param train
     * @return
     */
    public boolean updateTrain(@Param("train") Train train);


}
