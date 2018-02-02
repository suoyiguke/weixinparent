package com.yinkai.mapper;

import com.yinkai.entities.Forum;
import com.yinkai.entities.ForumExample;
import java.util.List;

import com.yinkai.entities.custom.ForumCustom;
import org.apache.ibatis.annotations.Param;

public interface ForumMapper {
    long countByExample(ForumExample example);

    int deleteByExample(ForumExample example);

    int deleteByPrimaryKey(Integer forumId);

    int insert(Forum record);

    int insertSelective(Forum record);

    List<Forum> selectByExample(ForumExample example);

    Forum selectByPrimaryKey(Integer forumId);

    int updateByExampleSelective(@Param("record") Forum record, @Param("example") ForumExample example);

    int updateByExample(@Param("record") Forum record, @Param("example") ForumExample example);

    int updateByPrimaryKeySelective(Forum record);

    int updateByPrimaryKey(Forum record);
    /**
     * 自定义
     * @param q
     * @return
     */
    List<ForumCustom> serachForumByLikeName(String q);

    int addOneTopiccount(Integer forumId);

    List<ForumCustom> forumPageQuery();

    int deleteForum(Integer[] forumId);

    int deleteOneTopicNum(Integer forumId);

    Forum selectForumByTopicId(Integer topicId);
}