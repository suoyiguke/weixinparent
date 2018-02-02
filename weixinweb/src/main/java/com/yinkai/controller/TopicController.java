
package com.yinkai.controller;

import com.yinkai.entities.Topic;
import com.yinkai.entities.vo.SearchTopicVo;
import com.yinkai.service.TopicService;
import com.yinkai.utils.jedis.JedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;



@Controller
public class TopicController {

	@Resource
	private TopicService topicService;

	@Resource
	private JedisClient jedisClient;

	@Value("${SESSION_TIME}")
	private  Integer SESSION_TIME;


	/**
	 * 后台----分页显示所有帖子
	 * @param
	 * @return
			*/
	@RequestMapping(value="/topicPageQuery",method = RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void pageQuery(HttpServletResponse response, Integer page , Integer rows , SearchTopicVo searchTopicVo) throws Exception {

		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods","GET");
		response.setHeader("Access-Control-Allow-Headers","Access-Control");
		response.setHeader("Allow","GET");

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(topicService.pageQuery(page, rows, searchTopicVo));
	}



	/**
	 * 小程序---发帖
	 * @param
	 * @return
	 */
	@RequestMapping(value="/addTopic",method = RequestMethod.POST)
	public   void  addTopic(String sessionId,HttpServletResponse response,MultipartHttpServletRequest request,Integer forumId,Topic topic) throws Exception {
		//TODO 就继续完成！！
		String openId = jedisClient.get(sessionId);
		jedisClient.expire(sessionId,SESSION_TIME);//重置过期时间
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(topicService.addTopic(request,openId,forumId,topic));

	}

	/**
	 * 小程序---显示校内帖子
	 * @param
	 * @return
	 */
	@RequestMapping(value="/getThisSchoolTopic",method = RequestMethod.GET)
	public   void  getThisSchoolTopic(HttpServletResponse response, Integer page , Integer rows,String sessionId) throws Exception {
		//TODO 就继续完成！！
		String openId = jedisClient.get(sessionId);
		jedisClient.expire(sessionId,SESSION_TIME);//重置过期时间
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(topicService.getThisSchoolTopic(openId,page,rows));

	}

	/**
	 * 小程序---显示校外帖子
	 * @param
	 * @return
	 */
	@RequestMapping(value="/getOtherSchoolTopic",method = RequestMethod.GET)
	public   void  getOtherSchoolTopic(HttpServletResponse response, Integer page , Integer rows,Integer forumId) throws Exception {

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(topicService.getOtherSchoolTopic(page,rows,forumId));

	}

	/**
	 * 用户删除贴子
	 */
	@RequestMapping(value="/deleteTopic",method = RequestMethod.GET)
	public   void  deleteTopic(HttpServletResponse response, String sessionId,Integer topicId) throws Exception {
		String openId = jedisClient.get(sessionId);
		jedisClient.expire(sessionId,SESSION_TIME);//重置过期时间
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(topicService.deleteTopicByOpenId(openId,topicId));

	}




}


