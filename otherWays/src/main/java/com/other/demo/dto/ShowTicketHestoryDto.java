package com.other.demo.dto;

import java.util.Date;

/**
 * 展示用户历史行程的信息
 */
public class ShowTicketHestoryDto {

    private long userId;//用户id
    private String trainCard;  //车次
    private String seatMessage; //座位信息
    private short isPayment; //是否付款，0未支付，1支付，数据库默认为0
    private short isUseTicket;  //是否出票，0未出票，1出票，数据库默认为0
    private short isChageTicket; //是否改签，0未改签，1改签，数据库默认为0
    private short isBackTicket;  //是否退票，0未退票，1退票，数据库默认为0
    private Date trainDate; //出行时间
    private String trainArrive; //目的地

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTrainCard() {
        return trainCard;
    }

    public void setTrainCard(String trainCard) {
        this.trainCard = trainCard;
    }

    public String getSeatMessage() {
        return seatMessage;
    }

    public void setSeatMessage(String seatMessage) {
        this.seatMessage = seatMessage;
    }

    public short getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(short isPayment) {
        this.isPayment = isPayment;
    }

    public short getIsUseTicket() {
        return isUseTicket;
    }

    public void setIsUseTicket(short isUseTicket) {
        this.isUseTicket = isUseTicket;
    }

    public short getIsChageTicket() {
        return isChageTicket;
    }

    public void setIsChageTicket(short isChageTicket) {
        this.isChageTicket = isChageTicket;
    }

    public short getIsBackTicket() {
        return isBackTicket;
    }

    public void setIsBackTicket(short isBackTicket) {
        this.isBackTicket = isBackTicket;
    }

    public Date getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(Date trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainArrive() {
        return trainArrive;
    }

    public void setTrainArrive(String trainArrive) {
        this.trainArrive = trainArrive;
    }

    public String getTrainFrom() {
        return trainFrom;
    }

    public void setTrainFrom(String trainFrom) {
        this.trainFrom = trainFrom;
    }

    private String trainFrom;  //出发地
}
