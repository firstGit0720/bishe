package com.ticketother.demo.service;

import com.ticketother.demo.dto.IndentMessageDto;
import com.ticketother.demo.dto.ShowTicketHestoryDto;
import com.ticketother.demo.entity.IndentMessage;
import com.ticketother.demo.entity.UserTickerMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOtherService {
    /**
     * 查看用户历史行程
     * @param userId
     * @return
     */
    public List<IndentMessageDto> showUserHistroy(Long userId);

    /**
     * 获取未完成的行程
     * @param userId
     * @return
     */
    public List<IndentMessageDto> showFuture(Long userId,int start,int end);


    /**
     *
     * @param trainCard
     * @return
     */
    public Long selectTrainId(String trainCard);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    public IndentMessage getMessage(long id);

    /**
     * 改签
     * @param id
     * @return
     */
    public boolean updateTicketStatus(long id);

    /**退票
     * @param id 订单id
     * @return
     */
    public boolean exitTicket( long id);
}
