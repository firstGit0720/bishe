package com.redis.demo.dto;

/**
 * 车票信息的dto
 */
public class TrainDto {
    private long id;
    private String trainCard;  //车次
    private String trainFrom;  //出发地
    private String trainFromTime; //出发时间
    private String trainArrive; //目的地
    private String trainArriveTime; //到达时间
    private String trainAfter;  //历时
    private String trainWait;  //停留时间
    private long seatBestNum; //特等座的数量
    private double seatBestPrice;  //特等座的价格
    private long seatFirstNum;  //一等座的数量
    private double seatFirstPrice;  //一等座的价格
    private long seatSecondNum;  //二等座的数量
    private double seatSecondPrice;  //二等座的价格
    private long sleeperFirstSoftNum;//软卧一等卧数量
    private double sleeperFirstSoftPrice; //软卧一等卧的价格
    private long sleeperBestNum;  //高级软卧的数量
    private double sleeperBestPrice; //高级软卧的价格
    private long sleeperSportNum;  //动卧的数量
    private double sleeperSportPrice;   //动卧的价格
    private long sleeperStiffNum; //硬卧的数量
    private double sleeperStiffPrice;  //动卧的价格
    private long seatSoftNum;       //软座的数量
    private double seatSoftPrice;  //软座的价格
    private long seatStiffNum;  //硬座的数量
    private double seatStiffPrice; //硬座的价格
    private long seatNoNum;//无座的数量
    private double seatNoPrice; //无座的价格
    private long seatOtherNum;  //其他的数量
    private double seatOtherPrice; //其他座位的价格

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getTrainFromTime() {
        return trainFromTime;
    }

    public void setTrainFromTime(String trainFromTime) {
        this.trainFromTime = trainFromTime;
    }

    public String getTrainArrive() {
        return trainArrive;
    }

    public void setTrainArrive(String trainArrive) {
        this.trainArrive = trainArrive;
    }

    public String getTrainArriveTime() {
        return trainArriveTime;
    }

    public void setTrainArriveTime(String trainArriveTime) {
        this.trainArriveTime = trainArriveTime;
    }

    public String getTrainAfter() {
        return trainAfter;
    }

    public void setTrainAfter(String trainAfter) {
        this.trainAfter = trainAfter;
    }

    public String getTrainWait() {
        return trainWait;
    }

    public void setTrainWait(String trainWait) {
        this.trainWait = trainWait;
    }

    public long getSeatBestNum() {
        return seatBestNum;
    }

    public void setSeatBestNum(long seatBestNum) {
        this.seatBestNum = seatBestNum;
    }

    public double getSeatBestPrice() {
        return seatBestPrice;
    }

    public void setSeatBestPrice(double seatBestPrice) {
        this.seatBestPrice = seatBestPrice;
    }

    public long getSeatFirstNum() {
        return seatFirstNum;
    }

    public void setSeatFirstNum(long seatFirstNum) {
        this.seatFirstNum = seatFirstNum;
    }

    public double getSeatFirstPrice() {
        return seatFirstPrice;
    }

    public void setSeatFirstPrice(double seatFirstPrice) {
        this.seatFirstPrice = seatFirstPrice;
    }

    public long getSeatSecondNum() {
        return seatSecondNum;
    }

    public void setSeatSecondNum(long seatSecondNum) {
        this.seatSecondNum = seatSecondNum;
    }

    public double getSeatSecondPrice() {
        return seatSecondPrice;
    }

    public void setSeatSecondPrice(double seatSecondPrice) {
        this.seatSecondPrice = seatSecondPrice;
    }

    public long getSleeperFirstSoftNum() {
        return sleeperFirstSoftNum;
    }

    public void setSleeperFirstSoftNum(long sleeperFirstSoftNum) {
        this.sleeperFirstSoftNum = sleeperFirstSoftNum;
    }

    public double getSleeperFirstSoftPrice() {
        return sleeperFirstSoftPrice;
    }

    public void setSleeperFirstSoftPrice(double sleeperFirstSoftPrice) {
        this.sleeperFirstSoftPrice = sleeperFirstSoftPrice;
    }

    public long getSleeperBestNum() {
        return sleeperBestNum;
    }

    public void setSleeperBestNum(long sleeperBestNum) {
        this.sleeperBestNum = sleeperBestNum;
    }

    public double getSleeperBestPrice() {
        return sleeperBestPrice;
    }

    public void setSleeperBestPrice(double sleeperBestPrice) {
        this.sleeperBestPrice = sleeperBestPrice;
    }

    public long getSleeperSportNum() {
        return sleeperSportNum;
    }

    public void setSleeperSportNum(long sleeperSportNum) {
        this.sleeperSportNum = sleeperSportNum;
    }

    public double getSleeperSportPrice() {
        return sleeperSportPrice;
    }

    public void setSleeperSportPrice(double sleeperSportPrice) {
        this.sleeperSportPrice = sleeperSportPrice;
    }

    public long getSleeperStiffNum() {
        return sleeperStiffNum;
    }

    public void setSleeperStiffNum(long sleeperStiffNum) {
        this.sleeperStiffNum = sleeperStiffNum;
    }

    public double getSleeperStiffPrice() {
        return sleeperStiffPrice;
    }

    public void setSleeperStiffPrice(double sleeperStiffPrice) {
        this.sleeperStiffPrice = sleeperStiffPrice;
    }

    public long getSeatSoftNum() {
        return seatSoftNum;
    }

    public void setSeatSoftNum(long seatSoftNum) {
        this.seatSoftNum = seatSoftNum;
    }

    public double getSeatSoftPrice() {
        return seatSoftPrice;
    }

    public void setSeatSoftPrice(double seatSoftPrice) {
        this.seatSoftPrice = seatSoftPrice;
    }

    public long getSeatStiffNum() {
        return seatStiffNum;
    }

    public void setSeatStiffNum(long seatStiffNum) {
        this.seatStiffNum = seatStiffNum;
    }

    public double getSeatStiffPrice() {
        return seatStiffPrice;
    }

    public void setSeatStiffPrice(double seatStiffPrice) {
        this.seatStiffPrice = seatStiffPrice;
    }

    public long getSeatNoNum() {
        return seatNoNum;
    }

    public void setSeatNoNum(long seatNoNum) {
        this.seatNoNum = seatNoNum;
    }

    public double getSeatNoPrice() {
        return seatNoPrice;
    }

    public void setSeatNoPrice(double seatNoPrice) {
        this.seatNoPrice = seatNoPrice;
    }

    public long getSeatOtherNum() {
        return seatOtherNum;
    }

    public void setSeatOtherNum(long seatOtherNum) {
        this.seatOtherNum = seatOtherNum;
    }

    public double getSeatOtherPrice() {
        return seatOtherPrice;
    }

    public void setSeatOtherPrice(double seatOtherPrice) {
        this.seatOtherPrice = seatOtherPrice;
    }
}
