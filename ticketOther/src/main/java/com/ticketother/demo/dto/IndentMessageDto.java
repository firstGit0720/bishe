package com.ticketother.demo.dto;

import java.util.Date;

/**
 * 订单dto前端显示
 */
public class IndentMessageDto {

    private Long id;
    private String trainCrad; //火车id
    private String username;//用户名
    private String userPanem; //用户姓名
    private String seatMessage; //座位信息
    private String indentTime; //订单创建时间
    private String trainStartTime; //火车出发时间
    private String indentFrom; //火车出发的位置
    private String indentArrive; //火车到达的位置
    private String seatType; //座位等级
    private String isPayment; //是否付款：0未付款，1付款，2退票已退款
    private String isStatus; //信息标记：0未出票，1已出票，2已改签，3已退票
    private String isSuccess;  //是否完成行程：0未完成，1完成


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainCrad() {
        return trainCrad;
    }

    public void setTrainCrad(String trainCrad) {
        this.trainCrad = trainCrad;
    }

    public String getSeatMessage() {
        return seatMessage;
    }

    public void setSeatMessage(String seatMessage) {
        this.seatMessage = seatMessage;
    }

    public String getIndentTime() {
        return indentTime;
    }

    public void setIndentTime(String indentTime) {
        this.indentTime = indentTime;
    }

    public String getTrainStartTime() {
        return trainStartTime;
    }

    public void setTrainStartTime(String trainStartTime) {
        this.trainStartTime = trainStartTime;
    }

    public String getIndentFrom() {
        return indentFrom;
    }

    public void setIndentFrom(String indentFrom) {
        this.indentFrom = indentFrom;
    }

    public String getIndentArrive() {
        return indentArrive;
    }

    public void setIndentArrive(String indentArrive) {
        this.indentArrive = indentArrive;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(String isPayment) {
        this.isPayment = isPayment;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPanem() {
        return userPanem;
    }

    public void setUserPanem(String userPanem) {
        this.userPanem = userPanem;
    }
}
