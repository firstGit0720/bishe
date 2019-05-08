package com.buyticket.demo.entity;

/**
 * 火车到达的站点的比哦啊
 */
public class TrainArrive {
    private long id;
    private long trainId;
    private String  trainArrive;//站点名称
    private int trainArriveWite; //该站点停留的时间
    private String trainArriveTime; //到达的时间
    private String trainAfter;  //经历的时长
    private Short trainArriveGrade; //站点的等级
    private  Double trainArrivePrice; //到达该站的价格

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public String getTrainArrive() {
        return trainArrive;
    }

    public void setTrainArrive(String trainArrive) {
        this.trainArrive = trainArrive;
    }

    public int getTrainArriveWite() {
        return trainArriveWite;
    }

    public void setTrainArriveWite(int trainArriveWite) {
        this.trainArriveWite = trainArriveWite;
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

    public Short getTrainArriveGrade() {
        return trainArriveGrade;
    }

    public void setTrainArriveGrade(Short trainArriveGrade) {
        this.trainArriveGrade = trainArriveGrade;
    }
}
