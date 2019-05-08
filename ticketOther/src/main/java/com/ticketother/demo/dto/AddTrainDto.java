package com.ticketother.demo.dto;

import java.util.List;

/**
 * 添加车票信息的数据传送对象
 */
public class AddTrainDto {
    private String trainCard; //车次i
    private String from; //出发地点
    private String fromTime; //出发的时间，只要时间，不要日期
    private String arrive; //最终的目的地
    private String arriveTime; //最终到达的时间
    private List<TrainSeatMessageDto> treanSeat; //座位信息
    private List<PassSiteDto> trainSpace;//经历过的站点

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public List<TrainSeatMessageDto> getTreanSeat() {
        return treanSeat;
    }

    public void setTreanSeat(List<TrainSeatMessageDto> treanSeat) {
        this.treanSeat = treanSeat;
    }

    public List<PassSiteDto> getTrainSpace() {
        return trainSpace;
    }

    public void setTrainSpace(List<PassSiteDto> trainSpace) {
        this.trainSpace = trainSpace;
    }

    public String getTrainCard() {
        return trainCard;
    }

    public void setTrainCard(String trainCard) {
        this.trainCard = trainCard;
    }
}
