package com.redis.demo.dto;

import java.util.Date;

/**
 * 用户的数据传送对象
 */
public class UserDto {

    private long id; //用户id，自增
    private String userName;  //用户名，不能为空
    private String userPname;  //用户姓名，不能为空
    private short userSex;   //用户性别，不能为空，0，男，1：女
    private String userAddress;  //用户地址，
    private String userCard;  //用户身份证
    private Date userBirthday;  //用户出身日期,不能为空
    private String userPhone;  //用户的手机号,不能为空
    private String userEmail;  //用户的e-mail地址
    private int userAge;   //用户的年龄，不能为空
    private short userType;  //用户类别0：管理员，1，票务人员，2：普通用户
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPname() {
        return userPname;
    }

    public void setUserPname(String userPname) {
        this.userPname = userPname;
    }

    public short getUserSex() {
        return userSex;
    }

    public void setUserSex(short userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public short getUserType() {
        return userType;
    }

    public void setUserType(short userType) {
        this.userType = userType;
    }
}
