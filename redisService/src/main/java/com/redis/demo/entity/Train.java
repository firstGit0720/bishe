package com.redis.demo.entity;

public class Train {
    private Long id;
    private String trainCard;  //车次
    private String trainFrom;  //出发地
    private String trainFromTime; //出发时间
    private String trainArrive; //目的地
    private String trainArriveTime; //到达时间
    private String trainAfter;  //历时
    private Long trainSeat;  //座位总数
    private String trainWait;  //停留时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainCard() {
        return trainCard;
    }

    public void setTrainCard(String trainCard) {
        this.trainCard = trainCard;
    }

    public String getTrainFrom() {
        return trainFrom;
    }

    public void setTrainFrom(String trainFrom) {
        this.trainFrom = trainFrom;
    }

    public String getTrainFromTime() {
        return trainFromTime;
    }

    public void setTrainFromTime(String trainFromTime) {
        this.trainFromTime = trainFromTime;
    }

    public String getTrainArrive() {
        return trainArrive;
    }

    public void setTrainArrive(String trainArrive) {
        this.trainArrive = trainArrive;
    }

    public String getTrainArriveTime() {
        return trainArriveTime;
    }

    public void setTrainArriveTime(String trainArriveTime) {
        this.trainArriveTime = trainArriveTime;
    }

    public String getTrainAfter() {
        return trainAfter;
    }

    public void setTrainAfter(String trainAfter) {
        this.trainAfter = trainAfter;
    }

    public Long getTrainSeat() {
        return trainSeat;
    }

    public void setTrainSeat(Long trainSeat) {
        this.trainSeat = trainSeat;
    }

    public String getTrainWait() {
        return trainWait;
    }

    public void setTrainWait(String trainWait) {
        this.trainWait = trainWait;
    }
}
