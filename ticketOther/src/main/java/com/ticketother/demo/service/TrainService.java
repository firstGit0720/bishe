package com.ticketother.demo.service;

import com.ticketother.demo.dto.ShowSeatDto;
import com.ticketother.demo.dto.TrainSeatMessageDto;
import com.ticketother.demo.entity.Train;
import com.ticketother.demo.entity.TrainArrive;
import com.ticketother.demo.entity.TrainSeat;
import com.ticketother.demo.entity.TrainSeatMessage;
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
    public boolean updateTrain(Train train);

    /**
     * 修改中间站点信息
     * @param trainArrive
     * @return
     */
    public boolean updateSpace(TrainArrive trainArrive);

    /**
     * 获取详细的中间站点信息
     * @param id
     * @return
     */
    public TrainArrive getTrainArrive(long id);

    /**
     * 火车的停运或回复
     * @param trainId
     * @return
     */
    public boolean updateTrainSuccess(long trainId,int status);

    /**
     * 查询退票改签的信息
     * @param trainId
     * @param from
     * @param arrive
     * @param startTime
     * @param status
     * @return
     */
    public List<TrainSeatMessage> allTrainSeatMessage( long trainId,  String from, String arrive,String startTime,  int status , int seatType);

    /**
     * 修改卖出的状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateStatus( long id,int status);

    /**
     * 修改火车座位信息
     * @param trainSeat
     * @return
     */
    public boolean updateTrainSeat(String  trainSeat);

    /**
     * 显示火车座位信息
     * @param trainId
     * @return
     */
    public List<ShowSeatDto> showSeata(Long trainId);

    /**
     * 添加中间站点
     * @param data
     * @return
     */
    public boolean addArrive(String data);

    /**
     * 修改状态，相当于删除
     * @param id
     * @return
     */
    public boolean updateArriveStatus(long id);


}
