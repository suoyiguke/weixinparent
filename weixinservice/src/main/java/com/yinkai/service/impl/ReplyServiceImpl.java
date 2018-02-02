package com.yinkai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinkai.entities.*;
import com.yinkai.entities.custom.ReplyCustom;
import com.yinkai.entities.custom.TopicCustom;
import com.yinkai.entities.custom.UserCustom;
import com.yinkai.mapper.ReplyMapper;
import com.yinkai.mapper.TopicMapper;
import com.yinkai.mapper.UserMapper;
import com.yinkai.service.ReplyService;
import com.yinkai.utilpojo.PageBean;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import com.yinkai.utils.wenxinapputils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TopicMapper topicMapper;

    //动态获取图片服务器的ip地址，现在图片服务器被我配置在本地
    @Value("${IMAGE_SRC}")
    private String IMAGE_SRC;

    @Override
    public String addReply(String openId, Reply reply, Integer topicId) {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(openId) | reply == null | StringUtils.isBlank(reply.getContent()) | topicId == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.ReplyServiceImpl.addReply参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }

        //数据库中肯定有该用户，因为通过的sessionId的验证
        UserCustom userCustom = userMapper.findUserCustomByOpenId(openId);

        //数据库中可能没有该topic！需要判断
        Topic topic = topicMapper.selectByPrimaryKey(topicId);
        if (topic == null | topic.getUserId() == null) {
            resultBean.setState(0);
            resultBean.setMessage("添加评论失败！");
            return FastJsonUtils.toJSONString(resultBean);
        }

        reply.setTopicId(topicId);
        reply.setUserId(userCustom.getUserId());
        reply.setCreatetime(wenxinapputils.getNowTimestamp());
        if (replyMapper.insertSelective(reply) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("添加评论失败！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        if (topicMapper.addOneReplyNumber(topicId) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("添加评论失败！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        resultBean.setState(1);
        resultBean.setMessage("添加评论成功！");
        resultBean.setObject(reply);
        return FastJsonUtils.toJSONString(resultBean);
    }

    @Override
    public String getReplyByTopicId(Integer topicId ,Integer page , Integer rows) {
      ResultBean resultBean = new ResultBean();
        if (topicId == null| page==null |rows==null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.ReplyServiceImpl.getReplyByTopicId参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        //数据中中可能没有该topic
        List<ReplyCustom> list =replyMapper.getReplyByTopicId(topicId);
        if(list==null){
            resultBean.setState(0);
            resultBean.setMessage("数据库中没有该topic！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        PageBean result = result(list);
        return  FastJsonUtils.toJSONString(result);
    }

    @Override
    public String replyPageQuery(Integer page, Integer rows) {
        ResultBean resultBean = new ResultBean();
        if ( page==null |rows==null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.ReplyServiceImpl.replyPageQuery参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        List<ReplyCustom> list = replyMapper.findAllReply();
        PageBean result = result(list);
        return FastJsonUtils.toJSONString(result);
    }


    /**
     * 所有的查询分页查询的放回list都组装成以下的数据结构
     * 把公共代码分装成方法
     */

    private PageBean result(List<ReplyCustom> list) {

        for (ReplyCustom custom : list) {

            custom.setHeadImage(IMAGE_SRC+custom.getHeadImage());
        }

        PageInfo<ReplyCustom> pageInfo = new PageInfo<ReplyCustom>(list);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(Integer.parseInt(pageInfo.getTotal() + ""));
        pageBean.setRows(list);

        return pageBean;
    }




}
