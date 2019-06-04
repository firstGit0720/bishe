package com.buyticket.demo.service.impl;

import com.buyticket.demo.dao.BuyTicketDao;
import com.buyticket.demo.dao.IndentMessageDao;
import com.buyticket.demo.dto.BuyTicketDto;
import com.buyticket.demo.dto.TicketShowDto;
import com.buyticket.demo.dto.TrainDto;
import com.buyticket.demo.dto.TrainSeatMessageDto;
import com.buyticket.demo.entity.*;
import com.buyticket.demo.fegin.OtherWaysFegin;
import com.buyticket.demo.fegin.TicketOtherFegin;
import com.buyticket.demo.redis.RedisGlobalLock;
import com.buyticket.demo.service.BuyTivketService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BuyTicketServiceImpl implements BuyTivketService {
    private static final Logger logger = Logger.getLogger(BuyTicketServiceImpl.class);
    @Autowired
    private BuyTicketDao buyTicketDao;
    @Autowired
    private OtherWaysFegin otherWaysFegin;
    @Autowired
    private TicketOtherFegin ticketOtherFegin;
    @Autowired
    private IndentMessageDao indentMessageDao;
    /**
     * 座位的总数量
     */
    private static Map<Object,Object> seatNum = new HashMap<Object,Object>();
    /**
     * 座位每节车厢的数量
     */
    private static Map<Object,Object> seatEvery = new HashMap<Object,Object>();

    /**
     * 每种座位的车厢数
     */
    private static Map<Object,Object> seatCarriage = new HashMap<Object,Object>();

    private static final List<String> SEAT_TYPE = new ArrayList<String>();
    static {
        SEAT_TYPE.add("特等座");
        SEAT_TYPE.add("一等座");
        SEAT_TYPE.add("二等座");
        SEAT_TYPE.add("软卧一等卧");
        SEAT_TYPE.add("高级软卧");
        SEAT_TYPE.add("动卧");
        SEAT_TYPE.add("硬卧");
        SEAT_TYPE.add("软座");
        SEAT_TYPE.add("硬座");
        SEAT_TYPE.add("无座");
        SEAT_TYPE.add("其他");

    }


    private static int num = 0;
    /**
     * 查票
     * @param from
     * @param arrive
     * @return
     */
    @Override
    public List<TrainDto> allTrain(String from, String arrive, String date,Integer start, Integer end)  {
        //查询是否有通往该站点的火车
        List<Train> trains = buyTicketDao.allTrain(from,arrive,start,end);
        //火车前端显示的数据传送对象(dto)
        List<TrainDto> trainDtos = new ArrayList<TrainDto>();
        if(trains.size() == 0){
            return  new ArrayList<TrainDto>();
        }
        //先从订单表中查询已有的订单是多少
        //有的情况下，判断出发地是不是该火车的出发地，是，直接添加相应的信息，不是，出发时间需要计算
        for (Train train: trains) {
            //先查看该火车的座位有哪些
            List<TrainSeatMessageDto> trainSeatMessageDtoList = ticketOtherFegin.getTrainSeats(train.getId(),from,arrive);
            //获取座位信息
            TrainSeat trainSeat = buyTicketDao.selectTrainSeatById(train.getId());
            //获取中间站点的信息
            List<TrainArrive> trainArrives = buyTicketDao.getTrainArrivesWithOK(train.getId(),start,end);
            //前端显示的dyo对象
            TrainDto trainDto = new TrainDto();
            //将作为信息添加到数据对象中
            //复制相关属性
            trainDto = fromBeanToBean(trainSeat);
            //设置火车card
            trainDto.setTrainCard(train.getTrainCard());
//            BeanUtils.copyProperties(trainSeat,trainDto);
            //计算时长的两个时间
            String fromTime = null,arriveTime = null;
            //地点信息出发点
            if (from.equals(train.getTrainFrom())){
                trainDto.setTrainFrom(from);
                trainDto.setTrainFromTime(train.getTrainFromTime());
                fromTime = train.getTrainFromTime();
            }else{
                for (TrainArrive trainArrive: trainArrives) {
                    if (from.equals(trainArrive.getTrainArrive())){
                        trainDto.setTrainFrom(from);
                        trainDto.setTrainFromTime(getGoTime(trainArrive.getTrainArriveTime(),trainArrive.getTrainArriveWite()));
                        fromTime = getGoTime(trainArrive.getTrainArriveTime(),trainArrive.getTrainArriveWite());
                    }
                }
            }
            //目的地
            if (arrive.equals(train.getTrainArrive())){
                trainDto.setTrainArrive(arrive);
                trainDto.setTrainArriveTime(train.getTrainArriveTime());
                arriveTime = train.getTrainArriveTime();
                try {
                    trainDto.setTrainWait(String.valueOf(10) + new String("分钟".getBytes(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                for (TrainArrive trainArrive: trainArrives) {
                    if (arrive.equals(trainArrive.getTrainArrive())){
                        trainDto.setTrainArrive(arrive);
                        trainDto.setTrainArriveTime(trainArrive.getTrainArriveTime());
                        arriveTime = trainArrive.getTrainArriveTime();
                        try {
                            trainDto.setTrainWait(String.valueOf(trainArrive.getTrainArriveWite()) + new String("分钟".getBytes(),"UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            //组装火车出发时间
            String dateTime = date + " " + fromTime;
            //修改相应的座位信息，从座位类型中，将相关的座位的剩余数量计算出来
            for (TrainSeatMessageDto trainSeatMessageDto :trainSeatMessageDtoList){
                System.out.println(trainSeatMessageDto.getSeatType());
                int i = getKey(trainSeatMessageDto.getSeatType());
                System.out.println(i);
                switch (i){
                    case 0 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)0,dateTime);
                        trainDto.setSeatBestNum(trainDto.getSeatBestNum() - num);
                    } break;
                    case 1 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)1,dateTime);
                        trainDto.setSeatFirstNum(trainDto.getSeatFirstNum() - num);
                    } break;
                    case 2 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)2,dateTime);
                        trainDto.setSeatSecondNum(trainDto.getSeatSecondNum() - num);
                    } break;
                    case 3 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)3,dateTime);
                        trainDto.setSleeperFirstSoftNum(trainDto.getSleeperFirstSoftNum() - num);
                    } break;
                    case 4 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)4,dateTime);
                        trainDto.setSleeperBestNum(trainDto.getSleeperBestNum() - num);
                    } break;
                    case 5 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)5,dateTime);
                        trainDto.setSleeperSportNum(trainDto.getSleeperSportNum() - num);
                    } break;
                    case 6 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)6,dateTime);
                        trainDto.setSleeperStiffNum(trainDto.getSleeperStiffNum() - num);
                    } break;
                    case 7 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)7,dateTime);
                        trainDto.setSeatSoftNum(trainDto.getSeatSoftNum() - num);
                    } break;
                    case 8 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)8,dateTime);
                        trainDto.setSeatStiffNum(trainDto.getSeatStiffNum() - num);
                    } break;
                    case 9 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)9,dateTime);
                        trainDto.setSeatNoNum(trainDto.getSeatNoNum() - num);
                    } break;
                    case 10 : {
                        int num =  indentMessageDao.getCount(train.getId(),from,arrive,(short)10,dateTime);
                        trainDto.setSeatOtherNum(trainDto.getSeatOtherNum() - num);
                    } break;
                }
            }
            //计算经历时长
            if(StringUtils.isNotEmpty(arriveTime) && StringUtils.isNotEmpty(fromTime)){
                trainDto.setTrainAfter(getExperienceTime(arriveTime,fromTime));
            }else{
                continue;
            }
            //进行火车的过滤
            Integer fromGrade = buyTicketDao.getGrade(from,train.getId());
            fromGrade = fromGrade == null ? 0 : fromGrade;
            Integer arriveGrade = buyTicketDao.getGrade(arrive,train.getId());
            arriveGrade = arriveGrade == null ? trainArrives.size() + 1 : arriveGrade ;
            if (fromGrade < arriveGrade){
                trainDtos.add(trainDto);
            }else{
                continue;
            }


        }
        return trainDtos;
    }

    /**
     * 买票
     * @param ticketDto
     * @return
     */
    @Override
    @Transactional
    public String buyTicket(BuyTicketDto ticketDto){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //更具用户名查找用户id
        String seatMessage = null;
        Long userId = otherWaysFegin.getUserId(ticketDto.getUserName());
        boolean buyCheck = false;
        //首先从退票改签的表中查询该火车是否有人退票会改签
        List<TrainSeatMessage> allList = ticketOtherFegin.allTrainSeatMessage(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),
                ticketDto.getDateStr(),0,getKey(ticketDto.getSeatType()));
        if (allList.size() > 0){
            //获取第一条
            TrainSeatMessage trainSeatMessage = allList.get(0);
            //封装数据对象
            IndentMessage indent = new IndentMessage();
            indent.setTrainId(trainSeatMessage.getTrainId());
            indent.setUserId(userId);
            indent.setIndentTime(new Date());
            indent.setIndentFrom(trainSeatMessage.getTrainFrom());
            indent.setIndentArrive(trainSeatMessage.getTrainArrive());
            indent.setSeatType(trainSeatMessage.getSeatType());
            indent.setSeatMessage(trainSeatMessage.getBackChangeMessage());
            indent.setTrainStartTime(trainSeatMessage.getTrainTime());
            indent.setIsPayment((short) 1);//能添加证明已付款
            indent.setIsStatus((short) 0);
            indent.setIsSuccess((short) 0);
            buyCheck = indentMessageDao.addIndentMessage(indent);
            if (buyCheck){
                //将改票的信息改为已售出
                ticketOtherFegin.updateStatus(trainSeatMessage.getId(),1);
                seatMessage = trainSeatMessage.getBackChangeMessage();
            }
        }else{
            // 座位等级0：特等座，1：一等座，2：二等座，3：软卧一等卧，4：高级软卧，5：动卧，6：硬卧，7：软座，8：硬座，9：无座
            //根据车次id查询火车座位信息
            TrainSeat trainSeat = buyTicketDao.selectTrainSeatById(ticketDto.getTrainId());
            //map数据封装,用于计算座位信息
            setMap(trainSeat,"train" + ticketDto.getTrainId());
            //封装数据对象
            IndentMessage indent = new IndentMessage();
            indent.setTrainId(ticketDto.getTrainId());
            indent.setUserId(userId);
            indent.setIndentTime(new Date());
            indent.setIndentFrom(ticketDto.getTrainFrom());
            indent.setIndentArrive(ticketDto.getTrainArrive());
            int seatKey = getKey(ticketDto.getSeatType());
            System.out.println(seatKey);
            indent.setSeatType(seatKey);
            indent.setSeatMessage(getSeatMessage(seatKey,ticketDto));
            indent.setTrainStartTime(ticketDto.getDateStr());
            indent.setIsPayment((short) 1);//能添加证明已付款
            indent.setIsStatus((short) 0);
            indent.setIsSuccess((short) 0);
            buyCheck = indentMessageDao.addIndentMessage(indent);
            if (buyCheck){
                seatMessage = indent.getSeatMessage();
                System.out.print(seatMessage);
            }
        }

        return seatMessage;
    }

    /**
     * 根据火车车次查找
     * @param trainCard
     * @return
     */
    @Override
    public List<TicketShowDto> allTrainSpaces(String trainCard,Integer start,Integer end) {
        //首先更具车次查找火车信息
        Train train = ticketOtherFegin.selectTrain(trainCard);
        //更具火车id查找火车站点信息
        List<TrainArrive> arriveList = buyTicketDao.getTrainArrives(train.getId(),start,end);
        //封装数据
        List<TicketShowDto> ticketShowDtos = new ArrayList<TicketShowDto>();
        TicketShowDto ticketShowDto = new TicketShowDto();
        //封装出发点
        ticketShowDto.setTrainFrom(train.getTrainFrom());
        ticketShowDto.setTrainFromTime(train.getTrainFromTime());
        ticketShowDtos.add(ticketShowDto);
        for (int i = 0 ; i < arriveList.size() ; i++) {
            TrainArrive arrive = arriveList.get(i);
            ticketShowDto.setTrainFrom(arrive.getTrainArrive());
            ticketShowDto.setTrainArriveTime(arrive.getTrainArriveTime());
            ticketShowDto.setTrainFromTime(getGoTime(arrive.getTrainArriveTime(),arrive.getTrainArriveWite()));
            if (i == 0){
                //出发地
                ticketShowDto.setTrainAfter(getExperienceTime(arrive.getTrainArriveTime(),train.getTrainFrom()));
            }else if(i == arriveList.size()-1){
                //目的地
                ticketShowDto.setTrainAfter(getExperienceTime(train.getTrainArriveTime(),arrive.getTrainArriveTime()));
            }else{
                ticketShowDto.setTrainAfter(getExperienceTime(arriveList.get(i+1).getTrainArriveTime(),arrive.getTrainArriveTime()));
            }
            ticketShowDto.setTrainWait(arrive.getTrainArriveWite());
            ticketShowDtos.add(ticketShowDto);
        }
        return ticketShowDtos;
    }

    /**
     * 火车的中间站点
     * @param trainId
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<TicketShowDto> allTrainSpaces(Long trainId, Integer start, Integer end) {
        //首先更具车次查找火车信息
        //更具火车id查找火车站点信息
        List<TrainArrive> arriveList = buyTicketDao.getTrainArrives(trainId,start,end);
        List<TicketShowDto> ticketShowDtos = new ArrayList<TicketShowDto>();
        for(TrainArrive arrive : arriveList){
            TicketShowDto ticketShowDto = new TicketShowDto();
            ticketShowDto.setId(arrive.getId());
            ticketShowDto.setGrade(arrive.getTrainArriveGrade());
            ticketShowDto.setTrainFrom(arrive.getTrainArrive());
            ticketShowDto.setTrainArriveTime(arrive.getTrainArriveTime());
            ticketShowDto.setTrainFromTime(getGoTime(arrive.getTrainArriveTime(),arrive.getTrainArriveWite()));
            ticketShowDto.setTrainWait(arrive.getTrainArriveWite());
            ticketShowDto.setTrainAfter(arrive.getTrainAfter());
            ticketShowDto.setStatus(arrive.getStatus());
            ticketShowDtos.add(ticketShowDto);
        }
        return ticketShowDtos;
    }

    /**
     * 查询所有的中间站点
     * @param trainId
     * @return
     */
    @Override
    public List<TrainArrive> allTrainArrives(Long trainId) {
        return buyTicketDao.getTrainArrives(trainId,null,null);
    }

    @Override
    public TrainArrive selectArriveById(long id) {
        return buyTicketDao.selectArriveById(id);
    }

    /**
     * 将相关的数据放到Map中
     * @param trainSeat
     * @param trainCard
     */
    public void setMap(TrainSeat trainSeat,String trainCard){
        if (((seatEvery.isEmpty() && seatNum.isEmpty())) || ((trainCard.equals("trainCard") && trainCard.equals(seatEvery.get("trainCard"))))){
            seatEvery.put("trainCard",trainCard);
            seatNum.put("trainCard",trainCard);
            //将座位信息放到map集合内，用于座位信息的计算
            seatNum.put((int)0,trainSeat.getSeatBestNum());
            seatEvery.put((int)0,trainSeat.getSeatBestEnery());
            seatCarriage.put((int) 0 , trainSeat.getSeatBestCarriage());
            seatNum.put((int)1,trainSeat.getSeatFirstNum());
            seatEvery.put((int)1,trainSeat.getSeatFirstEnery());
            seatCarriage.put((int) 1, trainSeat.getSeatFirstCarriage());
            seatNum.put((int)2,trainSeat.getSeatSecondNum());
            seatEvery.put((int)2,trainSeat.getSeatSecondEnery());
            seatCarriage.put((int) 2 , trainSeat.getSeatSecondCarriage());
            seatNum.put((int)3,trainSeat.getSleeperFirstSoftNum());
            seatEvery.put((int)3,trainSeat.getSleeperFirstSoftEnery());
            seatCarriage.put((int) 3 , trainSeat.getSleeperFirstSoftCarriage());
            seatNum.put((int)4,trainSeat.getSleeperBestNum());
            seatEvery.put((int)4,trainSeat.getSleeperBestEnery());
            seatCarriage.put((int) 4 ,trainSeat.getSleeperBestCarriage());
            seatNum.put((int)5,trainSeat.getSleeperSportNum());
            seatEvery.put((int)5,trainSeat.getSleeperSportEnery());
            seatCarriage.put((int) 5,trainSeat.getSleeperSportCarriage());
            seatNum.put((int)6,trainSeat.getSleeperStiffNum());
            seatEvery.put((int)6,trainSeat.getSleeperStiffEnery());
            seatCarriage.put((int) 6 , trainSeat.getSleeperStiffCarriage());
            seatNum.put((int)7,trainSeat.getSeatSoftNum());
            seatEvery.put((int)7,trainSeat.getSeatSoftEnery());
            seatCarriage.put((int) 7,trainSeat.getSeatSoftCarriage());
            seatNum.put((int)8,trainSeat.getSeatStiffNum());
            seatEvery.put((int)8,trainSeat.getSeatStiffEnery());
            seatCarriage.put((int) 8 ,trainSeat.getSeatStiffCarriage());
            seatNum.put((int)9,trainSeat.getSeatNoNum());
            seatEvery.put((int)9,trainSeat.getSeatNoEnery());
            seatCarriage.put((int)9,trainSeat.getSeatNoCarriage());
            seatNum.put((int)10,trainSeat.getSeatOtherNum());
            seatEvery.put((int)10,trainSeat.getSeatOtherEnery());
            seatCarriage.put((int) 10,trainSeat.getSeatOtherCarriage());
        }
    }

    /**
     * 获取座位信息
     * @param seatType  座位等级
     * @return
     */
    public String getSeatMessage(int seatType,BuyTicketDto ticketDto){
        System.out.println(ticketDto.getDateStr());
        //记录车型的位置
        long compartment = getCarriage(seatType, ticketDto);
        if (compartment ==0){
            compartment =1;
        }
        //已经购买的座位数量
        int num = getSeat(seatType, ticketDto);
        //从map中获取相关的座位信息
        int seatNums = (int)seatNum.get(seatType);
        int seatEnerys = (int) seatEvery.get(seatType);
        StringBuffer seatMessage = new StringBuffer();
        compartment = compartment + (int)seatCarriage.get(seatType) - seatNums / seatEnerys;
        seatMessage.append(compartment);
        try {
            seatMessage.append(new String("号车厢".getBytes(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //计算座位号
        int seatNumber = seatNums % seatEnerys + num + 1;
        if (seatNumber < 100 ){
            if (seatNumber < 10){
                seatMessage.append("00" + seatNumber);
            }else{
                seatMessage.append("0" + seatNumber);
            }

        }

        try {
            seatMessage.append(new String("号座位".getBytes(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return seatMessage.toString();
    }

    /**
     * 获取车厢的数量
     * @param seatType  座位类型
     * @param ticketDto
     * @return
     */
    public long getCarriage(int seatType,BuyTicketDto ticketDto){
        long compartment = 0 ;
        for (int i = 0 ; i < seatType-1;i++){
            switch(i){
                case 0 :{
                    if(seatCarriage.get(0) != null) {
                        compartment += (int) seatCarriage.get(0);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 1 : {
                    if (seatCarriage.get(1) != null) {
                        compartment += (int) seatCarriage.get(1);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 2 :{
                    if(seatCarriage.get(2) != null){
                        compartment += (int)seatCarriage.get(2);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 3 : {
                    if (seatCarriage.get(3) != null) {
                        compartment += (int) seatCarriage.get(3);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 4 : {
                    if (seatCarriage.get(4) != null) {
                        compartment += (int) seatCarriage.get(4);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 5 : {
                    if (seatCarriage.get(5) != null) {
                        compartment += (int) seatCarriage.get(5);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 6 : {
                    if (seatCarriage.get(6) != null) {
                        compartment += (int) seatCarriage.get(6);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 7 : {
                    if (seatCarriage.get(7) != null) {
                        compartment += (int) seatCarriage.get(7);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 8 :{
                    if(seatCarriage.get(8) != null){
                        compartment += (int)seatCarriage.get(8);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 9 : {
                    if (seatCarriage.get(9) != null) {
                        compartment += (int) seatCarriage.get(9);
                    }else{
                        compartment += 0;
                    }
                };break;
                case 10 :{
                    if(seatCarriage.get(10) != null){
                        compartment += (int)seatCarriage.get(10);
                    }else{
                        compartment += 0;
                    }
                };break;
            }
        }
        return compartment;

    }

    /**
     * 计算座位号
     * @param seatType
     * @param ticketDto
     * @return
     */
    public int getSeat(int seatType,BuyTicketDto ticketDto){
        int num = 0;
        switch(seatType){
            case 0 :{
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)0,ticketDto.getDateStr());
            };break;
            case 1 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)1,ticketDto.getDateStr());
            };break;
            case 2 :{
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)2,ticketDto.getDateStr());
            };break;
            case 3 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short) 3,ticketDto.getDateStr());
            };break;
            case 4 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)4,ticketDto.getDateStr());
            };break;
            case 5 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)5,ticketDto.getDateStr());
            };break;
            case 6 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)6,ticketDto.getDateStr());
            };break;
            case 7 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)7,ticketDto.getDateStr());
            };break;
            case 8 :{
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)8,ticketDto.getDateStr());
            };break;
            case 9 : {
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)9,ticketDto.getDateStr());
            };break;
            case 10 :{
                num =  indentMessageDao.getCount(ticketDto.getTrainId(),ticketDto.getTrainFrom(),ticketDto.getTrainArrive(),(short)10,ticketDto.getDateStr());
            };break;
        }
        return num;
    }

    /**
     * 计算火车出发时间
     * @param oldtime
     * @param num
     * @return
     */
    public String getGoTime(String oldtime,int num){
        String [] times = oldtime.split(":");
        int newHour = 0 , newMinute = 0;
        if((Integer.parseInt(times[1]) + num ) >= 60){
            newHour = Integer.parseInt(times[0]) + 1;
            newMinute = (Integer.parseInt(times[1]) + num ) - 60;
        }else{
            newHour = Integer.parseInt(times[0]);
            newMinute = (Integer.parseInt(times[1]) + num );
        }
        String str = new String();
        if(newMinute == 0){
            str = "00";
        }else if(newMinute > 0 && newMinute < 10){
            str = "0" + newMinute;
        }else{
            str = String.valueOf(newMinute);
        }
        return String.valueOf(newHour) + ":" + str;
    }

    /**
     * 计算火车经历时长
     * @param arrviteTime
     * @param fromTime
     * @return
     */
    public static String getExperienceTime(String arrviteTime,String fromTime){
        String [] arrviteTimes = arrviteTime.split(":");
        String [] fromTimes = fromTime.split(":");
        int newHour = 0 , newMinute = 0;
        if(Integer.parseInt(arrviteTimes[1]) < Integer.parseInt(fromTimes[1])){
            newMinute = 60 - Integer.parseInt(fromTimes[1]) + Integer.parseInt(arrviteTimes[1]);
            newHour = Integer.parseInt(arrviteTimes[0]) - Integer.parseInt(fromTimes[0]) -1;
        }else{
            newMinute =Integer.parseInt(arrviteTimes[1]) - Integer.parseInt(fromTimes[1]);
            newHour = Integer.parseInt(arrviteTimes[0]) - Integer.parseInt(fromTimes[0]);
        }
        StringBuffer str = new StringBuffer();
        if(newHour == 0){
            try {
                str.append(newMinute).append(new String("分钟".getBytes(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            try {
                str.append(String.valueOf(newHour)).append(new String ("小时".getBytes(),"UTF-8"))
                        .append(String.valueOf(newMinute)) .append(new String("分钟".getBytes(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str.toString();
    }

    /**
     * 获取座位类型
     * @param seat
     * @return
     */
    public Integer getKey(String seat){
        for (int i = 0; i < SEAT_TYPE.size(); i++){
            String seatType = null;
            try {
                seatType = new String (SEAT_TYPE.get(i).getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(seat.equals(seatType)){
                return i;
            }
        }
        return null;
    }

    public TrainDto  fromBeanToBean(TrainSeat trainSeat){
        TrainDto trainDto = new TrainDto();
       if(trainSeat.getTrainId() != null){
            trainDto.setId(trainSeat.getTrainId());
        }
        if(trainSeat.getSeatBestNum() != null){
            trainDto.setSeatBestNum(trainSeat.getSeatBestNum());
        }
        if(trainSeat.getSeatBestPrice() != null){
            trainDto.setSeatBestPrice(trainSeat.getSeatBestPrice());
        }
        if(trainSeat.getSeatFirstNum() != null){
            trainDto.setSeatFirstNum(trainSeat.getSeatFirstNum());
        }
        if(trainSeat.getSeatFirstPrice() != null){
            trainDto.setSeatFirstPrice(trainSeat.getSeatFirstPrice());
        }
        if(trainSeat.getSeatSecondNum() != null){
            trainDto.setSeatSecondNum(trainSeat.getSeatSecondNum());
        }
        if(trainSeat.getSeatSecondPrice() != null){
            trainDto.setSeatSecondPrice(trainSeat.getSeatSecondPrice());
        }
        if(trainSeat.getSleeperFirstSoftNum() != null){
            trainDto.setSleeperFirstSoftNum(trainSeat.getSleeperFirstSoftNum());
        }
        if(trainSeat.getSleeperFirstSoftPrice() != null){
            trainDto.setSleeperFirstSoftPrice(trainSeat.getSleeperFirstSoftPrice());
        }
        if(trainSeat.getSleeperBestNum() != null){
            trainDto.setSleeperBestNum(trainSeat.getSleeperBestNum());
        }
        if(trainSeat.getSleeperBestPrice() != null){
            trainDto.setSleeperBestPrice(trainSeat.getSleeperBestPrice());
        }
        if(trainSeat.getSleeperSportNum() != null){
            trainDto.setSleeperSportNum(trainSeat.getSleeperSportNum());
        }
        if(trainSeat.getSleeperSportPrice() != null){
            trainDto.setSleeperSportPrice(trainSeat.getSleeperSportPrice());
        }
        if(trainSeat.getSleeperStiffNum() != null){
            trainDto.setSleeperStiffNum(trainSeat.getSleeperStiffNum());
        }
        if(trainSeat.getSleeperStiffPrice() != null){
            trainDto.setSleeperStiffPrice(trainSeat.getSleeperStiffPrice());
        }
        if(trainSeat.getSeatSoftNum() != null){
            trainDto.setSeatSoftNum(trainSeat.getSeatSoftNum());
        }
        if(trainSeat.getSeatSoftPrice() != null){
            trainDto.setSeatSoftPrice(trainSeat.getSeatSoftPrice());
        }
        if(trainSeat.getSeatStiffNum() != null){
            trainDto.setSeatStiffNum(trainSeat.getSeatStiffNum());
        }
        if(trainSeat.getSeatStiffPrice() != null){
            trainDto.setSeatStiffPrice(trainSeat.getSeatStiffPrice());
        }
        if(trainSeat.getSeatNoNum() != null){
            trainDto.setSeatNoNum(trainSeat.getSeatNoNum());
        }
        if(trainSeat.getSeatNoPrice() != null){
            trainDto.setSeatNoPrice(trainSeat.getSeatNoPrice());
        }
        if(trainSeat.getSeatOtherNum() != null){
            trainDto.setSeatOtherNum(trainSeat.getSeatOtherNum());
        }
        if (trainSeat.getSeatOtherPrice() != null){
            trainDto.setSeatOtherPrice(trainSeat.getSeatOtherPrice());
        }
        return trainDto;
    }


}
