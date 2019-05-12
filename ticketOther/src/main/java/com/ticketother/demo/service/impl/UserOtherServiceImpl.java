package com.ticketother.demo.service.impl;

import com.ticketother.demo.dao.IndentMessageDao;
import com.ticketother.demo.dao.TrainFunctionDao;
import com.ticketother.demo.dao.UserOtherDao;
import com.ticketother.demo.dto.BackTicketDto;
import com.ticketother.demo.dto.IndentMessageDto;
import com.ticketother.demo.entity.IndentMessage;
import com.ticketother.demo.entity.Train;
import com.ticketother.demo.entity.TrainSeatMessage;
import com.ticketother.demo.entity.User;
import com.ticketother.demo.service.UserOtherService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserOtherServiceImpl implements UserOtherService {
    private static final Logger logger = Logger.getLogger(UserOtherServiceImpl.class);
    @Autowired
    private UserOtherDao userOtherDao;
    @Autowired
    private TrainFunctionDao trainDao;
    @Autowired
    private IndentMessageDao indentMessageDao;
    //座位等级
    private static final Map<Integer,String> SEAT_TYPE = new HashMap<Integer,String>();
    //信息状态
    private static final Map<Short,String> STATUS = new HashMap<Short,String>();
    //支付状态
    private static final Map<Short,String>  PAYMENT = new HashMap<Short,String>();
    //是否完成
    private static final Map<Short,String> SUCCESS = new HashMap<Short,String>();
    static {
        //设置座位等级
        SEAT_TYPE.put(0,"特等座");
        SEAT_TYPE.put(1,"一等座");
        SEAT_TYPE.put(2,"二等座");
        SEAT_TYPE.put(3,"软卧一等卧");
        SEAT_TYPE.put(4,"高级软卧");
        SEAT_TYPE.put(5,"动卧");
        SEAT_TYPE.put(6,"硬卧");
        SEAT_TYPE.put(7,"软座");
        SEAT_TYPE.put(8,"硬座");
        SEAT_TYPE.put(9,"无座");
        SEAT_TYPE.put(10,"其他");

        STATUS.put((short)0,"未出票");
        STATUS.put((short)1,"已出票");
        STATUS.put((short) 2 , "已改签");
        STATUS.put((short) 3 , "已退票");

        PAYMENT.put((short) 0 , "未支付");
        PAYMENT.put((short) 1, "已支付");
        PAYMENT.put((short) 2 , "已退款");

        SUCCESS.put((short) 0 , "未完成");
        SUCCESS.put((short) 1 , "已完成");
    }

    /**
     * 查看历史行程
     * @param userId
     * @return
     */
    @Override
    public List<IndentMessageDto> showUserHistroy(Long userId,int start,int end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<IndentMessage>  indentMessages= userOtherDao.showHistroy(userId,1,start,end);
        List<IndentMessageDto> indentMessageDtos = new ArrayList<IndentMessageDto>();
        for(IndentMessage indentMessage : indentMessages){
            IndentMessageDto indentMessageDto = new IndentMessageDto();
            indentMessageDto.setId(indentMessage.getId());
            Train train = trainDao.selectTrainByTrainId(indentMessage.getTrainId());
            indentMessageDto.setTrainCrad(train.getTrainCard());
            indentMessageDto.setIndentFrom(indentMessage.getIndentFrom());
            indentMessageDto.setIndentArrive(indentMessage.getIndentArrive());
            indentMessageDto.setIndentTime(sdf.format(indentMessage.getIndentTime()));
            indentMessageDto.setTrainStartTime(indentMessage.getTrainStartTime());
            indentMessageDto.setSeatType(SEAT_TYPE.get(indentMessage.getSeatType()));
            indentMessageDto.setSeatMessage(indentMessage.getSeatMessage());
            indentMessageDto.setIsPayment(PAYMENT.get(indentMessage.getIsPayment()));
            indentMessageDto.setIsStatus(STATUS.get(indentMessage.getIsStatus()));
            indentMessageDto.setIsSuccess(SUCCESS.get(indentMessage.getIsSuccess()));
            indentMessageDtos.add(indentMessageDto);
        }
        return indentMessageDtos;
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public List<IndentMessageDto> showFuture(Long userId,int start,int end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<IndentMessage>  indentMessages= userOtherDao.showFuture(userId,0,start,end);
        List<IndentMessageDto> indentMessageDtos = new ArrayList<IndentMessageDto>();
        for(IndentMessage indentMessage : indentMessages){
            IndentMessageDto indentMessageDto = new IndentMessageDto();
            indentMessageDto.setId(indentMessage.getId());
            Train train = trainDao.selectTrainByTrainId(indentMessage.getTrainId());
            indentMessageDto.setTrainCrad(train.getTrainCard());
            indentMessageDto.setIndentFrom(indentMessage.getIndentFrom());
            indentMessageDto.setIndentArrive(indentMessage.getIndentArrive());
            indentMessageDto.setIndentTime(sdf.format(indentMessage.getIndentTime()));
            indentMessageDto.setTrainStartTime(indentMessage.getTrainStartTime());
            indentMessageDto.setSeatType(SEAT_TYPE.get(indentMessage.getSeatType()));
            indentMessageDto.setSeatMessage(indentMessage.getSeatMessage());
            indentMessageDto.setIsPayment(PAYMENT.get(indentMessage.getIsPayment()));
            indentMessageDto.setIsStatus(STATUS.get(indentMessage.getIsStatus()));
            indentMessageDtos.add(indentMessageDto);
        }
        return indentMessageDtos;
    }

    /**
     * 查询火车id
     * @param trainCard
     * @return
     */
    @Override
    public Long selectTrainId(String trainCard) {
        return userOtherDao.selectTrainIdByTrainCard(trainCard);
    }

    /**
     * 获取订单车票信息
     * @param id
     * @return
     */
    @Override
    public IndentMessage getMessage(long id) {
        return indentMessageDao.getMessage(id);
    }

    /**
     * 改签
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean updateTicketStatus(long id) {
        boolean add = false;
        //首先根据id获取订单信息
        IndentMessage message = this.getMessage(id);
        //将相关信息放到退票的表中
        TrainSeatMessage trainSeatMessage = new TrainSeatMessage();
        trainSeatMessage.setTrainId(message.getTrainId());
        trainSeatMessage.setSeatType(message.getSeatType());
        trainSeatMessage.setBackChangeMessage(message.getSeatMessage());
        trainSeatMessage.setTrainArrive(message.getIndentArrive());
        trainSeatMessage.setTrainFrom(message.getIndentFrom());
        trainSeatMessage.setTrainTime(message.getTrainStartTime());
        //将订单中的状态改为2
        boolean check = this.updateStatis(id,2);
        if (check){
            userOtherDao.addTrainSeatMessage(trainSeatMessage);
        }
        return add;
    }

    /**
     * 退票
     * @param id 订单id
     * @return
     */
    @Override
    public boolean exitTicket(long id) {
        //首先根据id获取订单信息
        IndentMessage message = this.getMessage(id);
        //将相关信息放到退票的表中
        TrainSeatMessage trainSeatMessage = new TrainSeatMessage();
        trainSeatMessage.setTrainId(message.getTrainId());
        trainSeatMessage.setSeatType(message.getSeatType());
        trainSeatMessage.setBackChangeMessage(message.getSeatMessage());
        trainSeatMessage.setTrainArrive(message.getIndentArrive());
        trainSeatMessage.setTrainFrom(message.getIndentFrom());
        trainSeatMessage.setTrainTime(message.getTrainStartTime());
        //显示修改订单中车票的状态
        this.updateStatis(id,3);
        //将付款状态设置为已退款
        indentMessageDao.uodatepPymentStatus(id,2);

        return  userOtherDao.addTrainSeatMessage(trainSeatMessage);
    }

    /**
     * 订单的显示
     * @param startTime
     * @param endTime
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<IndentMessageDto> allIndentMessage(String startTime, String endTime, int start, int end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<IndentMessage> all = indentMessageDao.allIndentMessage(startTime,endTime,0,start,end);
        //封装数据对象
        List<IndentMessageDto> lists = new ArrayList<IndentMessageDto>();
        for (IndentMessage indentMessage : all){
            IndentMessageDto indentMessageDto = new IndentMessageDto();
            //获取火车信息
            Train train = trainDao.selectTrainByTrainId(indentMessage.getTrainId());
            //获取用户信息
            User user = userOtherDao.showUser(indentMessage.getUserId());
            indentMessageDto.setId(indentMessage.getId());
            indentMessageDto.setIsSuccess(SUCCESS.get(indentMessage.getIsSuccess()));
            indentMessageDto.setIsStatus(STATUS.get(indentMessage.getIsStatus()));
            indentMessageDto.setIsPayment(PAYMENT.get(indentMessage.getIsPayment()));
            indentMessageDto.setTrainCrad(train.getTrainCard());
            indentMessageDto.setUsername(user.getUserName());
            indentMessageDto.setUserPanem(user.getUserPname());
            indentMessageDto.setSeatType(SEAT_TYPE.get(indentMessage.getSeatType()));
            indentMessageDto.setSeatMessage(indentMessage.getSeatMessage());
            indentMessageDto.setTrainStartTime(indentMessage.getTrainStartTime());
            indentMessageDto.setIndentArrive(indentMessage.getIndentArrive());
            indentMessageDto.setIndentFrom(indentMessage.getIndentFrom());
            indentMessageDto.setIndentTime(sdf.format(indentMessage.getIndentTime()));
            lists.add(indentMessageDto);
        }
        return lists;
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<BackTicketDto> lists(String startTime, String endTime, int start, int end) {
        List<TrainSeatMessage> messageList = userOtherDao.allMessage(startTime, endTime, start, end);
        List<BackTicketDto> list = new ArrayList<BackTicketDto>();
        for(TrainSeatMessage message : messageList){
            BackTicketDto backTicketDto = new BackTicketDto();
            backTicketDto.setId(message.getId());
            Train train = trainDao.selectTrainByTrainId(message.getTrainId());
            backTicketDto.setTrainCard(train.getTrainCard());
            backTicketDto.setBackChangeMessage(message.getBackChangeMessage());
            backTicketDto.setSeatType(SEAT_TYPE.get(message.getSeatType()));
            backTicketDto.setTrainArrive(message.getTrainArrive());
            backTicketDto.setTrainFrom(message.getTrainFrom());
            backTicketDto.setTrainTime(message.getTrainTime());
            list.add(backTicketDto);
        }
        return list;
    }

    public boolean updateStatis(long id,int status){
        return indentMessageDao.uodateTicketStatus(id,status);
    }
}
