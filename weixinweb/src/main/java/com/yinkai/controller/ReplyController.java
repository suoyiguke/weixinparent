package com.yinkai.controller;

import com.yinkai.entities.Reply;
import com.yinkai.service.ReplyService;
import com.yinkai.utils.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReplyController {

    @Resource
    private JedisClient jedisClient;

    @Resource
    private ReplyService replyService;

    @Value("${SESSION_TIME}")
    private  Integer SESSION_TIME;


    /**
     * 小程序---评论
     * @param
     * @return
     */
    @RequestMapping(value="/addReply",method = RequestMethod.POST)
    public   void  addReply(String sessionId, HttpServletResponse response,  Reply reply,Integer topicId) throws Exception {
        //TODO 就继续完成！！
        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId,SESSION_TIME);//重置过期时间
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(replyService.addReply(openId,reply,topicId));

    }

    //小程序=--显示评论
    @RequestMapping(value="/getReply",method = RequestMethod.GET)
    public  void  getReply(HttpServletResponse response, Integer topicId,Integer page , Integer rows) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(replyService.getReplyByTopicId(topicId,  page,  rows));

    }

    //后台=--显示评论
    @RequestMapping(value="/replyPageQuery",method = RequestMethod.GET)
    public  void  replyPageQuery(HttpServletResponse response, Integer page , Integer rows) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(replyService.replyPageQuery(page,  rows));

    }



}
