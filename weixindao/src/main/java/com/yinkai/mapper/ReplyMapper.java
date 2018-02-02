package com.yinkai.mapper;

import com.yinkai.entities.Reply;
import com.yinkai.entities.ReplyExample;
import com.yinkai.entities.custom.ReplyCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    long countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(Integer replyId);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExampleWithBLOBs(ReplyExample example);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer replyId);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    List<ReplyCustom> getReplyByTopicId(Integer topicId);

    List<ReplyCustom> findAllReply();

    int deleteReplyByTopicId(Integer topicId);
}