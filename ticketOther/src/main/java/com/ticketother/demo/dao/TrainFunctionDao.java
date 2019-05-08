package com.ticketother.demo.dao;

import com.ticketother.demo.entity.Train;
import com.ticketother.demo.entity.TrainArrive;
import com.ticketother.demo.entity.TrainSeat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
