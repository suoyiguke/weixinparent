package com.yinkai.entities;

import java.io.Serializable;

public class UserTopicKey implements Serializable {
    private Integer userId;

    private Integer topicId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}