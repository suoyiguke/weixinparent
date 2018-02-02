package com.yinkai.entities;

import java.io.Serializable;

public class School implements Serializable {
    private Integer schoolId;

    private String name;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}