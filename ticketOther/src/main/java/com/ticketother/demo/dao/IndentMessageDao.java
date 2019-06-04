package com.ticketother.demo.dao;

import com.ticketother.demo.dto.IndentMessageDto;
import com.ticketother.demo.entity.IndentMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单的dao
 */
@Mapper
public interface IndentMessageDao {

    /**
     * 更具id获取订单信息
     * @param id
     * @return
     */
       public IndentMessage getMessage(@Param("id") long id);

        /**
         * 根据id修改车票的状态
         * 将状态修改为改签状态
         * @param id
         * @param status
         * @return
         */
        public boolean uodateTicketStatus(@Param("id") long id,@Param("status") int status);
    /**
     * 根据id修改车票付款的状态
     * 将状态修改为改签状态
     * @param id
     * @param status
     * @return
     */
    public boolean uodatepPymentStatus(@Param("id") long id,@Param("status") int status);

    /***
     * 订单的显示以及查询
     * @param startTime
     * @param endTime

     * @param start
     * @param end
     * @return
     */
    public List<IndentMessage> allIndentMessage(@Param("startTime") String startTime,@Param("endtime") String endTime,@Param("status") int status,@Param("start") int start,@Param("end") int end);

    /**
     * 获取所有的订单
     * @return
     */
    public List<IndentMessage> allIndents();

    /**
     * 修改车票状态
     * @param status
     * @param trainStartTime
     * @return
     */
    public boolean updateSuccess(@Param("status") int status, @Param("trainStartTime") String trainStartTime );

    /**
     * 修改车票出票状态
     * @param status
     * @param trainStartTime
     * @return
     */
    public boolean updateTicketSuccess(@Param("status") int status, @Param("trainStartTime") String trainStartTime );


}
