package com.other.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 统计模块的方法
 */
@Mapper
public interface StatisticsDao {

    /**
     * 火车的数量
     * @return
     */
    public int  trainsNum();

    /**
     * 会员的总数量
     * @return
     */
    public int  userNum();

    /**
     * 不同等级的会员数量
     * @param type
     * @return
     */
    public int userTypeNum(@Param("type") int type);

    /**
     * 总订单数
     * @return
     */
    public int indentNum();

    /**
     * 不同订单的数量
     * @param type
     * @return
     */
    public int indentTypeNum(@Param("type") int type);

}
