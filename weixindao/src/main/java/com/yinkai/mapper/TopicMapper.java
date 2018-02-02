package com.yinkai.mapper;

import com.yinkai.entities.Topic;
import com.yinkai.entities.TopicExample;
import com.yinkai.entities.custom.TopicCustom;
import com.yinkai.entities.vo.SearchTopicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {
    long countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExampleWithBLOBs(TopicExample example);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExampleWithBLOBs(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    /**
     *
     * @return
     */
    List<TopicCustom> findAllTopic();

    List<TopicCustom> findTopicByUserId(Integer userId);

    List<TopicCustom> findUserLikeTopic(Integer userId);

    int addOneLikeNum(Integer topicId)throws Exception;

    int deleteOneLikeNum(Integer topicId)throws Exception;

    List<TopicCustom> findTopicByLike(SearchTopicVo searchTopicVo);

    List<TopicCustom> findTopicBySchoolId(Integer schoolId);

    List<TopicCustom> findOtherTopicBySchoolId(Integer forumId);

    TopicCustom findTopicById(Integer topicId);

    List<TopicCustom> getLikeTopicPageQuery(String openId);

    int addOneReplyNumber(Integer topicId);

    List<TopicCustom> findTopicByOpenId(String openId);

    int deleteTopicByOpenId(String openId, Integer topicId);

    Integer getForumIdByTopicId(Integer topicId);
    Topic getTopicByTopicId(Integer topicId);
}
