package com.other.demo.service;

import org.apache.ibatis.annotations.Param;

public interface StatisticsService {
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
    public int userTypeNum(int type);

    /**
     * 总订单数
     * @return
     */
    public int indentNum();

    /**
     * 订单的数量完成的
     * @param type
     * @return
     */
    public int indentTypeNumIsOk(int type);

}
