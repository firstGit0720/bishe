package com.ticketother.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketother.demo.dao.TrainFunctionDao;
import com.ticketother.demo.dto.PassSiteDto;
import com.ticketother.demo.dto.TrainSeatMessageDto;
import com.ticketother.demo.entity.Train;
import com.ticketother.demo.entity.TrainArrive;
import com.ticketother.demo.entity.TrainSeat;
import com.ticketother.demo.fegin.TicketBuyFegin;
import com.ticketother.demo.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainFunctionDao trainDao;
    @Autowired
    private TicketBuyFegin ticketBuyFegin;
    //计算座位的价格
    private static double BEST_SEAT_FACTOR = 3.5; //特等座的价格增长倍数
    private static double FIRST_SEAT_FACTOR = 3.25; //一等座的价格增长倍数
    private static double SECOND_SEAT_FACTOR = 3.0; //二等座的价格增长倍数
    private static double SLEEPER_FIRST_SOFT_FACTOR = 2.75; //软卧一等卧的价格增长倍数
    private static double SLEEPER_BEST_FACTOR = 2.5; //高级软卧价格增长倍数
    private static double SLEEPER_SPORT_FACTOR = 2.25; //动卧的价格增长倍数
    private static double SLEEPER_STIFF_FACTOR = 2.0; //硬卧的价格增长倍数
    private static double SEAT_SOFT_FACTOR = 1.75; //软座的价格增长倍数
    private static double SEAT_STIFF_FACTOR = 1.5; //硬座的价格增长倍数
    private static double SEAT_NO_FACTOR = 1.5; //无座的价格增长倍数


    @Override
    @Transactional
    public boolean addTrainMessage(String message) throws ParseException {
        //判断条件
        boolean addSeat = false, addArrive = false,addTrain = false;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        //将字符串转换为json对象
        JSONObject jsonObject = JSON.parseObject(message);
        //获取座位信息
        JSONArray seatType = JSONArray.parseArray(jsonObject.get("seatType").toString());
        JSONArray seatNum = JSONArray.parseArray(jsonObject.get("seatNum").toString());
        JSONArray seatCarriage = JSONArray.parseArray(jsonObject.get("seatCarriage").toString());
        JSONArray seatPrice = JSONArray.parseArray(jsonObject.get("seatPrice").toString());
        //获取站点信息
        JSONArray trainArrive = JSONArray.parseArray(jsonObject.get("trainArrive").toString());
        JSONArray trainArriveWite = JSONArray.parseArray(jsonObject.get("trainArriveWite").toString());
        JSONArray trainArriveTime = JSONArray.parseArray(jsonObject.get("trainArriveTime").toString());
        //获取火车信息
        Train train = JSONObject.parseObject(jsonObject.get("train").toString(),Train.class);
        //计算行驶时间
        train.setTrainAfter(getDatePoor(sdf.parse(train.getTrainArriveTime()),sdf.parse(train.getTrainFromTime())));
        //计算座位总数
        long nums = 0;
        for (int i = 0 ; i< seatNum.size() ; i++){
            nums += seatNum.getLong(i);
        }
        train.setTrainSeat(nums);
        //添加火车信息
        addTrain = trainDao.addTicket(train);
        long trainId = trainDao.selectIdByCard(train.getTrainCard());
        System.out.println(trainId);
        if (addTrain){
            //封装座位信息
            TrainSeat trainSeat = new TrainSeat();
            trainSeat.setTrainId(trainId);
            for (int i = 0 ; i< seatType.size(); i++){
                switch(seatType.getInteger(i)){
                    case 0 :{  //特等座
                        trainSeat.setSeatBestCarriage(seatCarriage.getLong(i));  //设置车厢数量
                        trainSeat.setSeatBestNum(seatNum.getLong(i));  //设置座位总数
                        trainSeat.setSeatBestEnery(seatNum.getLong(i) / seatCarriage.getLong(i)); //设置每节的车作数
                        trainSeat.setSeatBestPrice(seatPrice.getDouble(i));
                    }; break;
                    case 1 : {   //一等座
                        trainSeat.setSeatFirstCarriage(seatCarriage.getLong(i));
                        trainSeat.setSeatFirstNum(seatNum.getLong(i));
                        trainSeat.setSeatFirstEnery(seatNum.getLong(i) / seatCarriage.getLong(i));
                        trainSeat.setSeatFirstPrice(seatPrice.getDouble(i));
                    };break;
                    case 2 : {  //二等座
                        trainSeat.setSeatSecondNum(seatNum.getLong(i));
                        trainSeat.setSeatSecondCarriage(seatCarriage.getLong(i));
                        trainSeat.setSeatSecondEnery(seatNum.getLong(i) /seatCarriage.getLong(i));
                        trainSeat.setSeatSecondPrice(seatPrice.getDouble(i));
                    };break;
                    case 3 : {//软卧一等卧
                        trainSeat.setSleeperFirstSoftNum(seatNum.getLong(i));
                        trainSeat.setSleeperFirstSoftCarriage(seatCarriage.getLong(i));
                        trainSeat.setSleeperFirstSoftEnery(seatNum.getLong(i) /seatCarriage.getLong(i));
                        trainSeat.setSleeperFirstSoftPrice(seatPrice.getDouble(i));
                    } break;
                    case 4 : {//高级软卧
                        trainSeat.setSleeperBestNum(Long.parseLong(seatNum.get(i).toString()));
                        trainSeat.setSleeperBestCarriage(Long.parseLong(seatCarriage.get(i).toString()));
                        trainSeat.setSleeperBestEnery(Long.parseLong(seatNum.get(i).toString()) / Long.parseLong(seatCarriage.get(i).toString()));
                        trainSeat.setSleeperBestPrice(Double.parseDouble(seatPrice.get(i).toString()));
                    }break;
                    case 5 : {//动卧
                        trainSeat.setSleeperSportNum(Long.parseLong(seatNum.get(i).toString()));
                        trainSeat.setSleeperSportCarriage(Long.parseLong(seatCarriage.get(i).toString()));
                        trainSeat.setSleeperSportEnery(Long.parseLong(seatNum.get(i).toString()) / Long.parseLong(seatCarriage.get(i).toString()));
                        trainSeat.setSleeperSportPrice(Double.parseDouble(seatPrice.get(i).toString()));
                    } break;
                    case 6 : { //硬卧
                        trainSeat.setSleeperStiffNum(Long.parseLong(seatNum.get(i).toString()));
                        trainSeat.setSleeperStiffCarriage(Long.parseLong(seatCarriage.get(i).toString()));
                        trainSeat.setSleeperStiffEnery(Long.parseLong(seatNum.get(i).toString()) / Long.parseLong(seatCarriage.get(i).toString()));
                        trainSeat.setSleeperStiffPrice(seatPrice.getDouble(i));
                    } break;
                    case 7 : {//软座
                        trainSeat.setSeatSoftNum(seatNum.getLong(i));
                        trainSeat.setSeatSoftCarriage(seatCarriage.getLong(i));
                        trainSeat.setSeatSoftEnery(seatNum.getLong(i) / seatCarriage.getLong(i));
                        trainSeat.setSeatSoftPrice(seatPrice.getDouble(i));
                    } break;
                    case 8 :{//硬座
                        trainSeat.setSeatStiffNum(seatNum.getLong(i));
                        trainSeat.setSeatStiffCarriage(seatCarriage.getLong(i));
                        trainSeat.setSeatStiffEnery(seatNum.getLong(i) / seatCarriage.getLong(i));
                        trainSeat.setSeatStiffPrice(seatPrice.getDouble(i));
                    } break;
                    case 9 : {//无座
                        trainSeat.setSeatNoNum(seatNum.getLong(i));
                        trainSeat.setSeatNoCarriage(seatCarriage.getLong(i));
                        trainSeat.setSeatNoEnery(seatNum.getLong(i) / seatCarriage.getLong(i));
                        trainSeat.setSeatNoPrice(seatPrice.getDouble(i));
                    } break;
                    case 10 : { //其他
                        trainSeat.setSeatOtherNum(seatNum.getLong(i));
                        trainSeat.setSeatOtherCarriage(seatCarriage.getLong(i));
                        trainSeat.setSeatOtherEnery(seatNum.getLong(i) / seatCarriage.getLong(i));
                        trainSeat.setSeatOtherPrice(seatPrice.getDouble(i));
                    } break;
                }
            }
            //添加座位信息
            addSeat = trainDao.addTrainSeat(trainSeat);
            //封装站点信息
            List<PassSiteDto> trainArrives = new ArrayList<>();
            TrainArrive arrive = new TrainArrive();
            for (int i = 0 ; i <trainArrive.size(); i ++){
                arrive.setTrainId(trainId);
                arrive.setTrainArriveGrade((short) i);  //站点等级
                arrive.setTrainArrive(trainArrive.getString(i));
                arrive.setTrainAfter(trainArrive.get(i).toString());
                arrive.setTrainArriveTime(trainArriveTime.get(i).toString());
                arrive.setTrainArriveWite(trainArriveWite.getInteger(i));
                //计算行驶时长
                if(i == 0){
                    arrive.setTrainAfter(getDatePoor(sdf.parse(trainArriveTime.get(i).toString()),sdf.parse(train.getTrainFromTime())));
                }else{
                    arrive.setTrainAfter(getDatePoor(sdf.parse(trainArriveTime.get(i).toString()),sdf.parse(trainArriveTime.get(i - 1).toString())));
                }
                addArrive = trainDao.addTrainPassSite(arrive);
            }

        }
        return addArrive && addSeat;
    }

    /**
     * 前端显示的座位信息以及相关座位的价格
     * @param trainId
     * @return
     */
    @Override
    public List<TrainSeatMessageDto> getTrainSeats(Long trainId,String from,String arrive) {
        TrainSeat trainSeat = trainDao.selectSeatByTrainId(trainId);
        Train train = trainDao.selectTrainByTrainId(trainId);
        //获取站点等级  用于计算座位价格
        List<TrainArrive> arriveList = ticketBuyFegin.allTrainArrive(trainId);
        //座位信息
        List<TrainSeatMessageDto> trainSeatMessages = new ArrayList<TrainSeatMessageDto>();
        short start = 0 , end = 0;
        //首先判断是不是始发地
//        System.out.println(train.getTrainArrive());
//        System.out.println(train.getTrainArrive());
//        System.out.println(train.getTrainFrom());
//        System.out.println(train.getTrainArrive());
        if (from.equals(train.getTrainFrom())){
//            System.out.println("判断出发地");
            start = 0;
        }
        if (arrive.equals(train.getTrainArrive())){
//            System.out.println("判断目的地");
            end = (short)(arriveList.size() + 1);
        }

        if(!from.equals(train.getTrainFrom()) || !arrive.equals(train.getTrainArrive())){
//            System.out.println("判断中间");
            for (TrainArrive trainArrive:arriveList) {
//                System.out.println("中间站点信息" + trainArrive.getTrainArrive());
                if (arrive.equals(trainArrive.getTrainArrive())){
                    end = trainArrive.getTrainArriveGrade();
                }
                if (arrive.equals(trainArrive.getTrainArrive())){
                    end = trainArrive.getTrainArriveGrade();
                }
                if (from.equals(trainArrive.getTrainArrive())){
                    start = trainArrive.getTrainArriveGrade();
                }
                if (from.equals(trainArrive.getTrainArrive())){
                    start = trainArrive.getTrainArriveGrade();
                }

            }
        }
        System.out.println(start + " : " + end);


        //封装数据
        if (trainSeat != null){
            if (trainSeat.getSeatBestCarriage() != null){  //特等座
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("特等座");
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatBestNum());
                //更具站点等级计算车票价格

                trainSeatMessageDto.setSeatPrice((end * BEST_SEAT_FACTOR *trainSeat.getSeatBestPrice())  - (start * BEST_SEAT_FACTOR *trainSeat.getSeatBestPrice()));
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSeatFirstCarriage() != null) {  //一等座
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("一等座");
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatFirstNum());
                trainSeatMessageDto.setSeatPrice((end * FIRST_SEAT_FACTOR * trainSeat.getSeatFirstPrice()) - (start * FIRST_SEAT_FACTOR * trainSeat.getSeatFirstPrice()));
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if(trainSeat.getSeatSecondCarriage() != null){ //二等座
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("二等座");
                trainSeatMessageDto.setSeatPrice((end * SECOND_SEAT_FACTOR * trainSeat.getSeatSecondPrice()) - (start * SECOND_SEAT_FACTOR * trainSeat.getSeatSecondPrice()));
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatSecondNum());
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSleeperFirstSoftCarriage() != null){  //软卧一等卧
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("软卧一等卧");
                trainSeatMessageDto.setSeatNum(trainSeat.getSleeperFirstSoftNum());
                trainSeatMessageDto.setSeatPrice((end * SLEEPER_FIRST_SOFT_FACTOR * trainSeat.getSleeperFirstSoftPrice()) - (start * SLEEPER_FIRST_SOFT_FACTOR * trainSeat.getSleeperFirstSoftPrice()));
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSleeperBestCarriage() != null){  //高级软卧
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("高级软卧");
                trainSeatMessageDto.setSeatPrice((end * SLEEPER_BEST_FACTOR * trainSeat.getSleeperBestPrice()) - (start * SLEEPER_BEST_FACTOR * trainSeat.getSleeperBestPrice()));
                trainSeatMessageDto.setSeatNum(trainSeat.getSleeperBestNum());
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSleeperSportCarriage() != null){  //动卧
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("动卧");
                trainSeatMessageDto.setSeatNum(trainSeat.getSleeperSportNum());
                trainSeatMessageDto.setSeatPrice((end * SLEEPER_SPORT_FACTOR * trainSeat.getSleeperSportPrice()) - (start * SLEEPER_SPORT_FACTOR * trainSeat.getSleeperSportPrice()));
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSleeperStiffCarriage() != null){  //硬卧
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("硬卧");
                trainSeatMessageDto.setSeatPrice((end * SLEEPER_STIFF_FACTOR * trainSeat.getSleeperStiffPrice()) - (start * SLEEPER_STIFF_FACTOR * trainSeat.getSleeperStiffPrice()));
                trainSeatMessageDto.setSeatNum(trainSeat.getSleeperStiffNum());
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSeatSoftCarriage() != null){  //软座
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("软座");
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatSoftNum());
                trainSeatMessageDto.setSeatPrice((end * SEAT_SOFT_FACTOR * trainSeat.getSeatSoftPrice()) - (start * SEAT_SOFT_FACTOR * trainSeat.getSeatSoftPrice()));
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSeatStiffCarriage() != null){ //硬座
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("硬座");
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatStiffNum());
                trainSeatMessageDto.setSeatPrice((end * SEAT_STIFF_FACTOR * trainSeat.getSeatStiffPrice()) - (start * SEAT_STIFF_FACTOR * trainSeat.getSeatStiffPrice()));
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSeatNoCarriage() != null){ //无座
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("无座");
                trainSeatMessageDto.setSeatPrice((end * SEAT_NO_FACTOR * trainSeat.getSeatNoPrice()) - (start * SEAT_NO_FACTOR * trainSeat.getSeatNoPrice()));
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatNoNum());
                trainSeatMessages.add(trainSeatMessageDto);
            }
            if (trainSeat.getSeatOtherCarriage() != null){
                TrainSeatMessageDto trainSeatMessageDto = new TrainSeatMessageDto();
                trainSeatMessageDto.setSeatType("其他");
                trainSeatMessageDto.setSeatNum(trainSeat.getSeatOtherNum());
                trainSeatMessageDto.setSeatPrice(trainSeat.getSeatOtherPrice());
                trainSeatMessages.add(trainSeatMessageDto);
            }
        }

        return trainSeatMessages;
    }

    /**
     * 根据火车编号查询火车信息
     * @param trainCard
     * @return
     */
    @Override
    public Train selectTrain(String trainCard) {
        return trainDao.selectTrainByTrainCard(trainCard);
    }

    @Override
    public Train selectTrainById(Long trainId) {
        return trainDao.selectTrainByTrainId(trainId);
    }

    @Override
    public List<Train> getAllTrain(Integer start,Integer end) {
        return trainDao.getAllTrain( start, end);
    }

    @Override
    public boolean updateTrain(Train train) {
        train.setTrainAfter(getExperienceTime(train.getTrainArriveTime(),train.getTrainFromTime()));
        return trainDao.updateTrain(train);
    }

    /**
     * 计算时间差
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        StringBuffer time = new StringBuffer();
        long nd = 1000 * 24 * 60 * 60;//每天毫秒数
        long nh = 1000 * 60 * 60;//每小时毫秒数
        long nm = 1000 * 60;//每分钟毫秒数
        long diff = endDate.getTime() - nowDate.getTime(); // 获得两个时间的毫秒时间差异
        long day = diff / nd;   // 计算差多少天
        if (day > 0){
            try {
                time.append( day + new String("天".getBytes(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        long hour = diff % nd / nh; // 计算差多少小时
        if (hour > 0){
            try {
                time.append( hour + new String("小时".getBytes(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        long min = diff % nd % nh / nm;  // 计算差多少分钟
        if (min > 0){
            try {
                time.append(min + new String("分钟".getBytes(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return time.toString() ;
    }

    /**
     * 计算时长
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

}
