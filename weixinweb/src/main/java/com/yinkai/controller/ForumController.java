
package com.yinkai.controller;

import com.yinkai.entities.Admin;
import com.yinkai.entities.Forum;
import com.yinkai.entities.custom.ForumCustom;
import com.yinkai.service.ForumService;
import com.yinkai.utilpojo.CustomException;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ForumController {

    @Resource
    private ForumService forumService;

    /**
     * 后台----添加版块
     *
     */
    @RequestMapping(value = "/addForum", method = RequestMethod.POST)
    public void addForum(HttpServletResponse response,HttpSession session, Forum forum) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            ResultBean resultBean = new ResultBean();
            resultBean.setState(0);
            resultBean.setMessage("管理员未登录！");
            response.getWriter().println(FastJsonUtils.toJSONString(resultBean));
            return;
        }
        forum.setAdminId(admin.getAdminId());
        response.getWriter().println(forumService.addForum(forum));

    }

    /**
     *后台删除版块
     * deleteForum.action?forumIds=9"
     *
     */
    @RequestMapping(value = "/deleteForum", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteForum(HttpServletResponse response,String forumIds) throws CustomException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(forumService.deleteForum(forumIds));

    }


		/*
       serachTopicByForumId
	 */

    @RequestMapping(value = "/serachForumByLikeName", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<ForumCustom> serachForumByLikeName(String q) throws CustomException {

        return forumService.serachForumByLikeName(q);

    }

    /**
     * 后台+app  分页查询所有版块forumPageQuery
     */
    @RequestMapping(value = "/forumPageQuery", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  void forumPageQuery(HttpServletResponse response, Integer page , Integer rows) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(forumService.forumPageQuery( page ,rows));

    }


}


