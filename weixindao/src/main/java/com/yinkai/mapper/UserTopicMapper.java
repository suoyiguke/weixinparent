package com.yinkai.mapper;

import com.yinkai.entities.UserTopicExample;
import com.yinkai.entities.UserTopicKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTopicMapper {
    long countByExample(UserTopicExample example);

    int deleteByExample(UserTopicExample example);

    int deleteByPrimaryKey(UserTopicKey key);

    int insert(UserTopicKey record);

    int insertSelective(UserTopicKey record);

    List<UserTopicKey> selectByExample(UserTopicExample example);

    int updateByExampleSelective(@Param("record") UserTopicKey record, @Param("example") UserTopicExample example);

    int updateByExample(@Param("record") UserTopicKey record, @Param("example") UserTopicExample example);

    List<UserTopicKey> findUserTopicByOpenId(String openId);

    int deleteByTopicId(Integer topicId);
}