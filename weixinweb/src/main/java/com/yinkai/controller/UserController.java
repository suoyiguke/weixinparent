package com.yinkai.controller;

import com.yinkai.entities.User;
import com.yinkai.service.TopicService;
import com.yinkai.service.UserService;
import com.yinkai.utilpojo.CheckBean;
import com.yinkai.utils.FastJsonUtils;
import com.yinkai.utils.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TopicService topicService;


    @Resource
    private JedisClient jedisClient;

    @Value("${SESSION_TIME}")
    private Integer SESSION_TIME;

    /**
     * 后台----分页显示所有用户
     */


    @RequestMapping(value = "/userPageQuery", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void pageQuery(HttpServletResponse response, Integer page, Integer rows) throws Exception {


        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(userService.pageQuery(page, rows));

    }


    /**
     * 后台----分页显示用户的所有帖子myTopicPageQuery
     */

    @RequestMapping(value = "/myTopicPageQuery", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void myTopicPageQuery(HttpServletResponse response, Integer page, Integer rows, Integer userId) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(topicService.myTopicPageQuery(userId, page, rows));
    }

    /**
     * 后台----查看用户所有的喜欢帖子
     */

    @RequestMapping(value = "/myLikeTopicPageQuery", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void myLikeTopicPageQuery(HttpServletResponse response, Integer page, Integer rows, Integer userId) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(topicService.myLikeTopicPageQuery(userId, page, rows));

    }

    /**
     * 后台--serachUserByName
     */
    @RequestMapping(value = "/serachUserByName", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<User> serachUserByName(String q) throws Exception {


        return userService.serachUserByName(q);

    }


    /**
     * 小程序---用户添加喜欢帖子
     * 需要认证！
     */
    @RequestMapping(value = "/addLikeTopic", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addLikeTopic(HttpServletResponse response, String sessionId, Integer topicId) throws Exception {

        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(userService.addLikeTopic(openId, topicId));


    }

    /**
     * 小程序---用户删除喜欢帖子
     * 需要认证！
     */
    @RequestMapping(value = "/deleteLikeTopic", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteLikeTopic(HttpServletResponse response, String sessionId, Integer topicId) throws Exception {
        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(userService.deleteLikeTopic(openId, topicId));


    }


    /**
     * 小程序---添加微信用户到小程序后台
     * 需要认证！
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(String sessionId, HttpServletResponse response, User user, @RequestParam(required = false) MultipartFile uploadFile) throws Exception {


        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        user.setOpenId(openId);
        String message = userService.addUser(user, uploadFile);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(message);

    }

    /**
     * 小程序---以后每一次进入小程序都需要发的请求getUser
     * 需要认证！
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getUser(HttpServletResponse response, String sessionId) throws Exception {

        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(userService.findUserByOpenId(openId));

    }

    /**
     * 小程序----用户验证
     */
    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    public void checkUser(HttpServletResponse response, @RequestParam(value = "js_code", required = false) String code) throws Exception {

        String checkString = userService.checkUser(code);
        CheckBean checkBean = FastJsonUtils.getSingleBean(checkString, CheckBean.class);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //存入redis，模拟session
        //z
        jedisClient.set(uuid, checkBean.getOpenid());
        jedisClient.expire(uuid, SESSION_TIME);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println("{\"sessionId\":\"" + uuid + "\"}");//将sessionid返回

    }

    /**
     * app
     * ----查看用户所有的喜欢帖子
     */

    @RequestMapping(value = "/getLikeTopicPageQuery", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getLikeTopicPageQuery(HttpServletResponse response, Integer page, Integer rows, String sessionId) throws Exception {


        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(topicService.getLikeTopicPageQuery(openId, page, rows));

    }

    /**
     * app--判断用户名是否存在
     */
    @RequestMapping(value = "/determineUser", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void determineUser(HttpServletResponse response, String nickname) throws Exception {

        nickname = new String(nickname.getBytes("iso8859-1"), "utf-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(userService.determineUser(nickname));

    }

    /**
     * 获得当前用户收藏帖子情况
     */
    @RequestMapping(value = "/getUserLikeState", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getUserLikeState(HttpServletResponse response, String sessionId) throws Exception {

        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(userService.getUserLikeState(openId));

    }


    /**
     * weixin----用户得到自己的帖子
     */

    @RequestMapping(value = "/getMyTopic", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getMyTopic(String sessionId,HttpServletResponse response, Integer page, Integer rows, Integer userId) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        String openId = jedisClient.get(sessionId);
        jedisClient.expire(sessionId, SESSION_TIME);//重置过期时间
        response.getWriter().println(topicService.getMyTopic(openId, page, rows));
    }




/*
    *//**
     * 用户查看自己的浏览记录
     *//*
    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    public void getFootprint(String sessionId) throws Exception {

        String openId = jedisClient.get(sessionId);
        userService
        CheckBean checkBean = FastJsonUtils.getSingleBean(checkString, CheckBean.class);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //存入redis，模拟session
        //z
        jedisClient.set(uuid,checkBean.getOpenid());
        jedisClient.expire(uuid,2*60*60);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println("{\"sessionId\":\""+uuid+"\"}");//将sessionid返回

    }*/



}
