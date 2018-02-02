package com.yinkai.entities.custom;

import com.yinkai.entities.Image;
import com.yinkai.entities.Topic;

import java.io.Serializable;
import java.util.List;

public class TopicCustom extends Topic implements Serializable {

    private String forumName;
    private String nickname;
    private String schoolName;


    private Integer headImageId;

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public Integer getHeadImageId() {
        return headImageId;
    }

    public void setHeadImageId(Integer headImageId) {
        this.headImageId = headImageId;
    }

    private String headimage;

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }






    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }




    /**
     * 图片列表
     */
    private  List<Image> imageList;

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }






    }



