package com.yinkai.entities.custom;

import com.yinkai.entities.Reply;

import java.io.Serializable;

public class ReplyCustom extends Reply implements Serializable{

    private String nickname;//评论作者名字
    private String sex;//作者性别
    private String school;//作者学校

    private String headImage;//作者头像

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }






    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


}
