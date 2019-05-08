package com.other.demo.entity;

import java.util.Date;

public class UserType {
    private Long id; //id
    private Long userId;  //用户id
    private Short userType;  //用户类别0：管理员，1，票务人员，2：普通用户
    private Date createTime; //创建的时间
    private Date updateTime; //权限修改的时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
