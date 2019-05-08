package com.buyticket.demo.dto;

/**
 * 车票信息的dto
 */
public class TrainDto {
    private Long id;
    private String trainCard;  //车次
    private String trainFrom;  //出发地
    private String trainFromTime; //出发时间
    private String trainArrive; //目的地
    private String trainArriveTime; //到达时间
    private String trainAfter;  //历时
    private String trainWait;  //停留时间
    private Integer seatBestNum; //特等座的数量
    private Double seatBestPrice;  //特等座的价格
    private Integer seatFirstNum;  //一等座的数量
    private Double seatFirstPrice;  //一等座的价格
    private Integer seatSecondNum;  //二等座的数量
    private Double seatSecondPrice;  //二等座的价格
    private Integer sleeperFirstSoftNum;//软卧一等卧数量
    private Double sleeperFirstSoftPrice; //软卧一等卧的价格
    private Integer sleeperBestNum;  //高级软卧的数量
    private Double sleeperBestPrice; //高级软卧的价格
    private Integer sleeperSportNum;  //动卧的数量
    private Double sleeperSportPrice;   //动卧的价格
    private Integer sleeperStiffNum; //硬卧的数量
    private Double sleeperStiffPrice;  //动卧的价格
    private Integer seatSoftNum;       //软座的数量
    private Double seatSoftPrice;  //软座的价格
    private Integer seatStiffNum;  //硬座的数量
    private Double seatStiffPrice; //硬座的价格
    private Integer seatNoNum;//无座的数量
    private Double seatNoPrice; //无座的价格
    private Integer seatOtherNum;  //其他的数量
    private Double seatOtherPrice; //其他座位的价格

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

    public Integer getSeatBestNum() {
        return seatBestNum;
    }

    public void setSeatBestNum(Integer seatBestNum) {
        this.seatBestNum = seatBestNum;
    }

    public Double getSeatBestPrice() {
        return seatBestPrice;
    }

    public void setSeatBestPrice(Double seatBestPrice) {
        this.seatBestPrice = seatBestPrice;
    }

    public Integer getSeatFirstNum() {
        return seatFirstNum;
    }

    public void setSeatFirstNum(Integer seatFirstNum) {
        this.seatFirstNum = seatFirstNum;
    }

    public Double getSeatFirstPrice() {
        return seatFirstPrice;
    }

    public void setSeatFirstPrice(Double seatFirstPrice) {
        this.seatFirstPrice = seatFirstPrice;
    }

    public Integer getSeatSecondNum() {
        return seatSecondNum;
    }

    public void setSeatSecondNum(Integer seatSecondNum) {
        this.seatSecondNum = seatSecondNum;
    }

    public Double getSeatSecondPrice() {
        return seatSecondPrice;
    }

    public void setSeatSecondPrice(Double seatSecondPrice) {
        this.seatSecondPrice = seatSecondPrice;
    }

    public Integer getSleeperFirstSoftNum() {
        return sleeperFirstSoftNum;
    }

    public void setSleeperFirstSoftNum(Integer sleeperFirstSoftNum) {
        this.sleeperFirstSoftNum = sleeperFirstSoftNum;
    }

    public Double getSleeperFirstSoftPrice() {
        return sleeperFirstSoftPrice;
    }

    public void setSleeperFirstSoftPrice(Double sleeperFirstSoftPrice) {
        this.sleeperFirstSoftPrice = sleeperFirstSoftPrice;
    }

    public Integer getSleeperBestNum() {
        return sleeperBestNum;
    }

    public void setSleeperBestNum(Integer sleeperBestNum) {
        this.sleeperBestNum = sleeperBestNum;
    }

    public Double getSleeperBestPrice() {
        return sleeperBestPrice;
    }

    public void setSleeperBestPrice(Double sleeperBestPrice) {
        this.sleeperBestPrice = sleeperBestPrice;
    }

    public Integer getSleeperSportNum() {
        return sleeperSportNum;
    }

    public void setSleeperSportNum(Integer sleeperSportNum) {
        this.sleeperSportNum = sleeperSportNum;
    }

    public Double getSleeperSportPrice() {
        return sleeperSportPrice;
    }

    public void setSleeperSportPrice(Double sleeperSportPrice) {
        this.sleeperSportPrice = sleeperSportPrice;
    }

    public Integer getSleeperStiffNum() {
        return sleeperStiffNum;
    }

    public void setSleeperStiffNum(Integer sleeperStiffNum) {
        this.sleeperStiffNum = sleeperStiffNum;
    }

    public Double getSleeperStiffPrice() {
        return sleeperStiffPrice;
    }

    public void setSleeperStiffPrice(Double sleeperStiffPrice) {
        this.sleeperStiffPrice = sleeperStiffPrice;
    }

    public Integer getSeatSoftNum() {
        return seatSoftNum;
    }

    public void setSeatSoftNum(Integer seatSoftNum) {
        this.seatSoftNum = seatSoftNum;
    }

    public Double getSeatSoftPrice() {
        return seatSoftPrice;
    }

    public void setSeatSoftPrice(Double seatSoftPrice) {
        this.seatSoftPrice = seatSoftPrice;
    }

    public Integer getSeatStiffNum() {
        return seatStiffNum;
    }

    public void setSeatStiffNum(Integer seatStiffNum) {
        this.seatStiffNum = seatStiffNum;
    }

    public Double getSeatStiffPrice() {
        return seatStiffPrice;
    }

    public void setSeatStiffPrice(Double seatStiffPrice) {
        this.seatStiffPrice = seatStiffPrice;
    }

    public Integer getSeatNoNum() {
        return seatNoNum;
    }

    public void setSeatNoNum(Integer seatNoNum) {
        this.seatNoNum = seatNoNum;
    }

    public Double getSeatNoPrice() {
        return seatNoPrice;
    }

    public void setSeatNoPrice(Double seatNoPrice) {
        this.seatNoPrice = seatNoPrice;
    }

    public Integer getSeatOtherNum() {
        return seatOtherNum;
    }

    public void setSeatOtherNum(Integer seatOtherNum) {
        this.seatOtherNum = seatOtherNum;
    }

    public Double getSeatOtherPrice() {
        return seatOtherPrice;
    }

    public void setSeatOtherPrice(Double seatOtherPrice) {
        this.seatOtherPrice = seatOtherPrice;
    }
}
