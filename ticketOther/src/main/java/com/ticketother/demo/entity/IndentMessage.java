package com.ticketother.demo.entity;

import java.util.Date;

/**
 * 订单实体类
 */
public class IndentMessage {

    private Long id;
    private Long userId; //用户id
    private Long trainId; //火车id
    private String seatMessage; //座位信息
    private Date indentTime; //订单创建时间
    private String trainStartTime; //火车出发时间
    private String indentFrom; //火车出发的位置
    private String indentArrive; //火车到达的位置
    private Integer seatType; //座位等级
    private Short isPayment; //是否付款：0未付款，1付款，2退票已退款
    private Short isStatus; //信息标记：0未出票，1已出票，2已改签，3已退票
    private Short isSuccess;  //是否完成行程：0未完成，1完成

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getIndentTime() {
        return indentTime;
    }

    public void setIndentTime(Date indentTime) {
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

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Short getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Short isPayment) {
        this.isPayment = isPayment;
    }

    public Short getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Short isStatus) {
        this.isStatus = isStatus;
    }

    public Short getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Short isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getSeatMessage() {
        return seatMessage;
    }

    public void setSeatMessage(String seatMessage) {
        this.seatMessage = seatMessage;
    }
}
