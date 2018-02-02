package com.yinkai.entities.vo;
import com.yinkai.entities.Topic;

import java.io.Serializable;
import java.util.Date;

public class SearchTopicVo implements Serializable{
        private Topic topic = new Topic();
        private Integer schoolId;
        private Date startDate;
        private Date endDate;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
