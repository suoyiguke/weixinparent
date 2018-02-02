package com.yinkai.service;

import com.yinkai.entities.Reply;

public interface ReplyService {
    String addReply(String openId, Reply reply, Integer topicId);

    String getReplyByTopicId(Integer topicId ,Integer page , Integer rows);

    String replyPageQuery(Integer page, Integer rows);
}
