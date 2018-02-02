package com.yinkai.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
    private Integer messageId;

    private Integer fromid;

    private Integer toid;

    private Timestamp messagedate;

    private Integer type;

    private String messagetext;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }

    public Integer getToid() {
        return toid;
    }

    public void setToid(Integer toid) {
        this.toid = toid;
    }

    public Timestamp getMessagedate() {
        return messagedate;
    }

    public void setMessagedate(Timestamp messagedate) {
        this.messagedate = messagedate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext == null ? null : messagetext.trim();
    }
}