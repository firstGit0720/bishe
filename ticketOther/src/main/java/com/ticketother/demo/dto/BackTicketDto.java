package com.ticketother.demo.dto;

/**
 * 退票改签现实
 */
public class BackTicketDto {
    private Long id;
    private String trainCard;  //火车的id
    private String backChangeMessage; //退票或改签的信息
    private String trainFrom;
    private String trainArrive;
    private String trainTime;
    private String seatType;
    private int status; //是否已经卖出0：为卖出 1 卖出

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

    public String getBackChangeMessage() {
        return backChangeMessage;
    }

    public void setBackChangeMessage(String backChangeMessage) {
        this.backChangeMessage = backChangeMessage;
    }

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

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
