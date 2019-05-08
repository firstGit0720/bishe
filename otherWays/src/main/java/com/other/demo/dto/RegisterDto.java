package com.other.demo.dto;

import java.util.Date;

/**
 * 数据传送对象
 */
public class RegisterDto {
    private String userName;  //用户名，不能为空
    private String password; //用户密码不能为空
    private String userPname;  //用户姓名，不能为空
    private short userSex;   //用户性别，不能为空，0，男，1：女
    private String userAddress;  //用户地址，
    private String userCard;  //用户身份证
    private Date userBirthday;  //用户出身日期,不能为空
    private String userPhone;  //用户的手机号,不能为空
    private String userEmail;  //用户的e-mail地址

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
