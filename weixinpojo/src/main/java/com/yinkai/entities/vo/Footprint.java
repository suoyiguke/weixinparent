package com.yinkai.entities.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 足迹pojo
 */
public class Footprint implements Serializable {
    private Timestamp time;//浏览时间
    private String topicContent;
    private String 	author;
    private String sex;
    private Integer likeNumber;

    private Integer replyNumber;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }




}
