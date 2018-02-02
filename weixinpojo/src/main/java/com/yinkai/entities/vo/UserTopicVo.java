package com.yinkai.entities.vo;

import com.yinkai.entities.Topic;
import com.yinkai.entities.User;

import java.io.Serializable;

public class UserTopicVo implements Serializable{
    private User user = new User();
    private Topic topic = new Topic();


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
