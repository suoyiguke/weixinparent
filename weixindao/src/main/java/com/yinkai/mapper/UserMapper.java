package com.yinkai.mapper;

import com.yinkai.entities.User;
import com.yinkai.entities.UserExample;
import com.yinkai.entities.UserKey;
import java.util.List;

import com.yinkai.entities.custom.UserCustom;
import com.yinkai.entities.vo.UserTopicVo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(UserKey key);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 自定义
     */
    List<UserCustom> findAllUser();

    int addLikeTopic(UserTopicVo userTopicVo) ;

    int deleteLikeTopic(UserTopicVo userTopicVo);

    UserCustom findUserCustomByOpenId(String openId);

    User determineUser(String nickname);
}