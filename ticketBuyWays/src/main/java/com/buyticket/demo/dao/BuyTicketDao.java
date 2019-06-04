package com.buyticket.demo.dao;

import com.buyticket.demo.entity.Train;
import com.buyticket.demo.entity.TrainArrive;
import com.buyticket.demo.entity.TrainSeat;
import com.buyticket.demo.entity.UserTickerMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 买票的dao层
 */
@Mapper
public interface BuyTicketDao {

    /**
     * 查票
     * @param from 出发地
     * @param arrive 目的地
     * @return 所有相关的车票信息
     */
    public List<Train> allTrain( String from,String arrive,Integer start, Integer end);

    /**
     * 买票
     * @param ticket 车票
     * @return
     */
    public boolean buyTicket(@Param("ticket") UserTickerMessage ticket);

    /**
     * 更具火车id查找车次的座位信息
     * @param trainId
     * @return
     */
    public TrainSeat selectTrainSeatById(@Param("trainId")long trainId);

    /**
     * 获取中间站点的信息
     * @param trainId
     * @return
     */
    public List<TrainArrive> getTrainArrives(@Param("trainId") long trainId, @Param("start") Integer start, @Param("end") Integer end );

    /**
     * 获取中间站点的信息
     * @param trainId
     * @return
     */
    public List<TrainArrive> getTrainArrivesWithOK(@Param("trainId") long trainId, @Param("start") Integer start, @Param("end") Integer end );

    /**
     * 获取中间站点的等级，判断该车辆是否符合要求
     * @param space
     * @param trainId
     * @return
     */
    public Integer getGrade(@Param("space") String space,@Param("trainId") Long trainId);

    /**
     * 更具id查询中间站点信息
     * @param id
     * @return
     */
    public TrainArrive selectArriveById(@Param("id") long id);


}
