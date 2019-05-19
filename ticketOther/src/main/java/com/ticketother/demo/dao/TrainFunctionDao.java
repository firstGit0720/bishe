package com.ticketother.demo.dao;

import com.ticketother.demo.dto.ShowSeatDto;
import com.ticketother.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * 车票信息管理
 */
@Mapper
public interface TrainFunctionDao {
    /**
     * 添加车票信息，只有出发点和终点，用于根据id查看该车次的信息
     * @param train
     * @return
     */
    public boolean addTicket(@Param("train") Train train);

    /**
     * 添加该车次经过的站点
     * @param trainArrive
     * @return
     */
    public boolean addTrainPassSite(@Param("trainArrive") TrainArrive trainArrive);

    /**
     * 添加座位信息
     * @param trainSeat
     * @return
     */
    public boolean addTrainSeat(@Param("trainSeat") TrainSeat trainSeat);

    /**
     * 根据车次获取id
     * @param trainCard
     * @return
     */
    public long selectIdByCard(@Param("trainCard") String trainCard);

    /**
     * 更具火车id查找火车座位信息
     * @param trainId
     * @return
     */
    public TrainSeat selectSeatByTrainId(@Param("trainId") Long trainId);
    /**
     * 根据火车编号查找
     * @param trainCard
     * @return
     */
    public Train selectTrainByTrainCard(@Param("trainCard") String trainCard);


    /**
     * 根据火车编号查找
     * @param trainCard
     * @return
     */
    public List<Train> selectTrainByTrainCard1(@Param("trainCard") String trainCard);

    /**
     * 根据火车编号查找
     * @param trainId
     * @return
     */
    public Train selectTrainByTrainId(@Param("trainId") Long trainId);

    /**
     * 获取去所有的火车信息
     * @return
     */
    public List<Train> getAllTrain(@Param("start") Integer start, @Param("end") Integer end);

    /**
     * 修改火车信息
     * @param train
     * @return
     */
    public boolean updateTrain(@Param("train") Train train);

    /**
     * 修改中间站点信息
     * @param trainArrive
     * @return
     */
    public boolean updateSpace(@Param("trainArrive") TrainArrive trainArrive);

    /**
     * 根据id获取中间站点的详细信息
     * @param id
     * @return
     */
    public TrainArrive getTrainArrive(@Param("id") long id);

    /**
     * 火车的停运或恢复
     * @param trainId
     * @return
     */
    public boolean updateTrainSuccess(@Param("trainId") long trainId,@Param("status") int status);

    /**
     * 查询退票改签的信息
     * @param trainId
     * @param from
     * @param arrive
     * @param startTime
     * @param status
     * @return
     */
    public List<TrainSeatMessage> allTrainSeatMessage(@Param("trainId") long trainId,@Param("from") String from,@Param("arrive") String arrive,
                                                      @Param("startTime") String startTime, @Param("status") int status ,@Param("seatType") int seatType);

    /**
     * 修改卖出的状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateStatus(@Param("id") long id,@Param("status") int status);
    /**
     * 修改座位信息
     * @param trainSeat
     * @return
     */
    public boolean updateTrainSeat(@Param("trainSeat") TrainSeat trainSeat);

    /**
     * 修改火车座位信息
     * @param trainId
     * @param seatNum
     * @return
     */
    public boolean updateSeatNum(@Param("trainId") long trainId,@Param("seatNum") long seatNum);

    /**
     * 删除
     * @param trainId
     * @return
     */
    public boolean deleteSeat (@Param("trainId") long trainId);

    /**
     * 修改站点的运行状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateTrainArriveStatus(@Param("id") long id,@Param("status") int status);

}
