package com.ticketother.demo.dao;

import com.ticketother.demo.entity.IndentMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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


}
