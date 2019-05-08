package com.other.demo.dto;

import java.util.Date;

/**
 * 购买车票时传递得数据对象
 */
public class BuyTicketDto {
    private String trainCard;  //车次
    private String trainFrom;  //出发地
    private String trainArrive; //目的地
    private Date trainFromTime; //出发时间
    private Short seatType; //座位等级0：特等座，1：一等座，2：二等座，3：软卧一等卧，4：高级软卧，5：动卧，6：硬卧，7：软座，8：硬座，9：无座
    private String userName; //用户信息

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

    public String getTrainArrive() {
        return trainArrive;
    }

    public void setTrainArrive(String trainArrive) {
        this.trainArrive = trainArrive;
    }

    public Date getTrainFromTime() {
        return trainFromTime;
    }

    public void setTrainFromTime(Date trainFromTime) {
        this.trainFromTime = trainFromTime;
    }

    public Short getSeatType() {
        return seatType;
    }

    public void setSeatType(Short seatType) {
        this.seatType = seatType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
