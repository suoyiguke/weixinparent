package com.yinkai.entities;

import java.io.Serializable;

public class User extends UserKey implements Serializable{
    private Integer schoolId;

    private String nickname;

    private String sex;

    private String autograph;

    private Integer headimageId;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph == null ? null : autograph.trim();
    }

    public Integer getHeadimageId() {
        return headimageId;
    }

    public void setHeadimageId(Integer headimageId) {
        this.headimageId = headimageId;
    }



}