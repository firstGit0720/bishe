package com.other.demo.dto;

/**
 * 火车座位的显示
 */
public class ShowSeatDto {
    private int seatType;
    private long seatNum;
    private long carriage;
    private double price;

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public long getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(long seatNum) {
        this.seatNum = seatNum;
    }

    public long getCarriage() {
        return carriage;
    }

    public void setCarriage(long carriage) {
        this.carriage = carriage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
