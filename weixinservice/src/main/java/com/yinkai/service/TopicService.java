package com.yinkai.service;

import com.yinkai.entities.Topic;
import com.yinkai.entities.vo.SearchTopicVo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface TopicService {

    String pageQuery(Integer page, Integer rows,SearchTopicVo searchTopicVo) throws Exception;

    String myTopicPageQuery(Integer userId,Integer page, Integer rows) throws Exception;

    String myLikeTopicPageQuery(Integer userId, Integer page, Integer rows) throws Exception;

    String addTopic(MultipartHttpServletRequest request, String openId, Integer forumId, Topic topic) throws Exception;


    String getThisSchoolTopic(String openId,Integer page, Integer rows)throws Exception;

    String getOtherSchoolTopic(Integer page, Integer rowsm,Integer forumId)throws Exception;

    String getLikeTopicPageQuery(String openId, Integer page, Integer rows)throws Exception;

    String getMyTopic(String openId, Integer page, Integer rows);

    String deleteTopicByOpenId(String openId, Integer topicId);
}
