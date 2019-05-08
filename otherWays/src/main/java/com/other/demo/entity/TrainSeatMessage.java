package com.other.demo.entity;

/**
 * 火车退票和改签的信息
 */
public class TrainSeatMessage {
    private Long id;
    private Long trainId;  //火车的id
    private String backChangeMessage; //退票或改签的信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getBackChangeMessage() {
        return backChangeMessage;
    }

    public void setBackChangeMessage(String backChangeMessage) {
        this.backChangeMessage = backChangeMessage;
    }
}
