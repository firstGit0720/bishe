package com.buyticket.demo.dto;

import java.util.Date;

/**
 * 购买车票时传递得数据对象
 */
public class BuyTicketDto {
    private Long trainId;  //车次
    private String trainFrom;  //出发地
    private String trainArrive; //目的地
    private String dateStr; //出发时间
    private String seatType; //座位等级0：特等座，1：一等座，2：二等座，3：软卧一等卧，4：高级软卧，5：动卧，6：硬卧，7：软座，8：硬座，9：无座
    private Double seatPrice;
    private String userName; //用户信息


    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
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

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
