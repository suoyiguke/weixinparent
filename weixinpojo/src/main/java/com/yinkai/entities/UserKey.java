package com.yinkai.entities;

import java.io.Serializable;

public class UserKey implements Serializable{
    private Integer userId;

    private String openId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}