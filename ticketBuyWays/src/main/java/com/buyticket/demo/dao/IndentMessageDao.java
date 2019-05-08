package com.buyticket.demo.dao;

import com.buyticket.demo.entity.IndentMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 订单的dao
 */
@Mapper
public interface IndentMessageDao {
        /**
         * 查询已有的订单
         * @param trainId  火车id
         * @param from 出发地
         * @param arrive 目的地
         * @param seatType 座位等级
         * @param date 处罚的时间
         * @return
         */
        public Integer getCount(@Param("trainId") Long trainId, @Param("from") String from, @Param("arrive") String arrive, @Param("seatType") Short seatType , @Param("date") String date);

        /**
         *买票插入
         * @param indentMessage
         * @return
         */
        public Boolean addIndentMessage(@Param("indent")IndentMessage indentMessage);



}
