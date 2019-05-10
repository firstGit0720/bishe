package com.ticketother.demo.dao;

import com.ticketother.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户的其他方法
 */
@Mapper
public interface UserOtherDao {
    /**
     *  查看历史行程
     * @param userId 用户的id
     * @return
     */
    public List<IndentMessage> showHistroy(@Param("userId") Long userId,@Param("status") int status,@Param("start") int start,@Param("end") int end);

    /**
     * 未完成的行程
     * @param userId
     * @return
     */
    public List<IndentMessage> showFuture(@Param("userId") Long userId,@Param("status") int status,@Param("start") int start,@Param("end") int end);

    /**
     * 查看个人信息
     * @param userName
     * @return
     */
    public List<User> showUserMessage(@Param("userName") String userName);
    /**
     * 查看个人信息
     * @param userId
     * @return
     */
    public User showUser(@Param("userId") Long userId);

    /**
     * 根据id查找火车的信息
     * @param trainId
     * @return
     */
    public Train selectTrainById(@Param("trainId") Long trainId);

    /**
     * 更具火车车次查找id
     * @param trainCard
     * @return
     */
    public Long selectTrainIdByTrainCard(@Param("trainCard") String trainCard);

    /**
     * 添加改签/退票信息
     * @param trainSeatMessage
     * @return
     */
    public boolean addTrainSeatMessage(@Param("seatMessage") TrainSeatMessage trainSeatMessage);

    /**
     *
     * @param startTime
     * @param endTime
     * @param start
     * @param end
     * @return
     */
    public List<TrainSeatMessage> allMessage(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("start") int start,@Param("end") int end);

}
