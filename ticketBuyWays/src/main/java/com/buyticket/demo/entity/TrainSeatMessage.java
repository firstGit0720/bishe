package com.buyticket.demo.entity;

/**
 * 火车退票和改签的信息
 */
public class TrainSeatMessage {
    private Long id;
    private Long trainId;  //火车的id
    private String backChangeMessage; //退票或改签的信息
    private String trainFrom;
    private String trainArrive;
    private String trainTime;
    private int seatType;
    private int status; //是否已经卖出0：为卖出 1 卖出

    public String getTrainFrom() {
        return trainFrom;
    }

    public void setTrainFrom(String trainFrom) {
        this.trainFrom = trainFrom;
    }

    public String getTrainArrive() {
        return trainArrive;
    }

    public void setTrainArrive(String trainArrive) {
        this.trainArrive = trainArrive;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }



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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
