package com.buyticket.demo.dto;

/**
 * 火车添加座位信息
 */
public class TrainSeatMessageDto {
    private String seatType;//座位级别
    private Long seatNum;
    private double seatPrice;
    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Long getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Long seatNum) {
        this.seatNum = seatNum;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }


}
