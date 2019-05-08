package com.ticketother.demo.entity;

import java.util.Date;

/**
 * 用户买票的信息
 */
public class UserTickerMessage {

    private Long id;
    private Long userId;//用户id
    private Long tarinId; //火车id
    private String seatMessage; //座位信息
    private Short isPayment; //是否付款，0未支付，1支付，数据库默认为0
    private Short isUseTicket;  //是否出票，0未出票，1出票，数据库默认为0
    private Short isChageTicket; //是否改签，0未改签，1改签，数据库默认为0
    private Short isBackTicket;  //是否退票，0未退票，1退票，数据库默认为0
    private Date trainDate; //出行时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTarinId() {
        return tarinId;
    }

    public void setTarinId(Long tarinId) {
        this.tarinId = tarinId;
    }

    public String getSeatMessage() {
        return seatMessage;
    }

    public void setSeatMessage(String seatMessage) {
        this.seatMessage = seatMessage;
    }

    public Short getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Short isPayment) {
        this.isPayment = isPayment;
    }

    public Short getIsUseTicket() {
        return isUseTicket;
    }

    public void setIsUseTicket(Short isUseTicket) {
        this.isUseTicket = isUseTicket;
    }

    public Short getIsChageTicket() {
        return isChageTicket;
    }

    public void setIsChageTicket(Short isChageTicket) {
        this.isChageTicket = isChageTicket;
    }

    public Short getIsBackTicket() {
        return isBackTicket;
    }

    public void setIsBackTicket(Short isBackTicket) {
        this.isBackTicket = isBackTicket;
    }

    public Date getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(Date trainDate) {
        this.trainDate = trainDate;
    }
}
