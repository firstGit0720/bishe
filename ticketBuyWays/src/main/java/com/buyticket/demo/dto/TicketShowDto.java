package com.buyticket.demo.dto;

/**
 * 按车次查询时展示的dto
 */
public class TicketShowDto {
    private Long id;
    private Short grade;//站点等级
    private String trainFrom;  //出发地
    private String trainFromTime; //出发时间
    private String trainArriveTime; //到达时间
    private String trainAfter;  //历时
    private Integer trainWait;  //停留时间

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

    public Integer getTrainWait() {
        return trainWait;
    }

    public void setTrainWait(Integer trainWait) {
        this.trainWait = trainWait;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }
}
