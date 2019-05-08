package com.ticketother.demo.dto;

/**
 * 经过的站点的信息
 */
public class PassSiteDto {

    private String  trainArrive;//站点名称
    private int trainArriveWite; //该站点停留的时间
    private String trainArriveTime; //到达的时间
    private String trainAfter;  //经历的时长

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

}
