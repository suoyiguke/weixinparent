package com.yinkai.entities.custom;

import com.yinkai.entities.User;

import java.io.Serializable;

/**
 * resultType方式，这只是做返回结果的一个扩展！如果pojo类需要添加属性，请写到User中，不要写在这！
 */
public class UserCustom extends User implements Serializable{


    private String headimage;

    private String schoolname;



    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    //返回全路径
    public  String getHeadimage(){
        return  this.headimage;
    }
}
