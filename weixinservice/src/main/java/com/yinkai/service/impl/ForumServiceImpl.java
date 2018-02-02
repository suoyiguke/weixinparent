package com.yinkai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinkai.entities.Forum;
import com.yinkai.entities.custom.ForumCustom;
import com.yinkai.mapper.ForumMapper;
import com.yinkai.service.ForumService;
import com.yinkai.utilpojo.CustomException;
import com.yinkai.utilpojo.PageBean;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import com.yinkai.utils.wenxinapputils;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.event.FocusEvent;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {

    @Resource
    private ForumMapper forumMapper;

    @Override
    public String addForum(Forum forum) {
        ResultBean resultBean = new ResultBean();
        if (forum == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.ForumServiceImpl.addForum参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        if (forum.getAdminId()==null){
            resultBean.setState(0);
            resultBean.setMessage("forumId参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        forum.setCreatetime(wenxinapputils.getNowTimestamp());

        if(forumMapper.insertSelective(forum)!=1){
            resultBean.setState(0);
            resultBean.setMessage("添加版块失败！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        resultBean.setState(1);
        resultBean.setMessage("添加版块成功！");
        return FastJsonUtils.toJSONString(resultBean);
    }


    @Override
    public List<ForumCustom> serachForumByLikeName(String q) throws CustomException {

        return forumMapper.serachForumByLikeName(q);
    }


    @Override
    public String forumPageQuery(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        PageBean result = result(forumMapper.forumPageQuery());

        return FastJsonUtils.toJSONString(result);
    }

    @Override
    public String deleteForum(String forumIds) {
        ResultBean resultBean = new ResultBean();
        if (forumIds == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.ForumServiceImpl.deleteForum参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        String[] forumId = forumIds.split(",");
        Integer[] integers = new Integer[forumId.length];
        for (int i = 0; i < forumId.length; i++) {
            integers[i]=Integer.parseInt(forumId[i]);
        }

        if(forumMapper.deleteForum(integers)!=forumId.length){
            resultBean.setState(0);
            resultBean.setMessage("删除版块失败！");
            return FastJsonUtils.toJSONString(resultBean);

        }

        resultBean.setState(1);
        resultBean.setMessage("删除版块成功！");
        return FastJsonUtils.toJSONString(resultBean);
    }


    /**
     * 所有的查询分页查询的放回list都组装成以下的数据结构
     * 把公共代码分装成方法
     */

    private PageBean result(List<ForumCustom> list) {


        PageInfo<ForumCustom> pageInfo = new PageInfo<ForumCustom>(list);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(Integer.parseInt(pageInfo.getTotal() + ""));
        pageBean.setRows(list);

        return pageBean;
    }
}

