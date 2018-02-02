package com.yinkai.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Forum implements Serializable {
    protected Integer forumId;

    protected Integer adminId;//必要

    protected String description;

    protected String name;

    protected Integer position;

    protected Timestamp createtime;//必要

    protected Integer topiccount;

    protected Integer replycount;

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getTopiccount() {
        return topiccount;
    }

    public void setTopiccount(Integer topiccount) {
        this.topiccount = topiccount;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
    }
}