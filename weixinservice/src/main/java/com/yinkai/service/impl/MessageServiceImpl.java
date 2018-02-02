package com.yinkai.service.impl;

import com.yinkai.entities.Message;
import com.yinkai.mapper.MessageMapper;
import com.yinkai.service.MessageService;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import com.yinkai.utils.wenxinapputils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService{

    @Resource
    private MessageMapper messageMapper;
    @Override
    public String addMessage(Message message) {
        ResultBean resultBean = new ResultBean();
        if (message ==null| StringUtils.isBlank(message.getMessagetext()) | message.getFromid()==null| message.getFromid() == null | message.getToid()==null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.MessageServiceImpl.addMessage参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        message.setMessagedate(wenxinapputils.getNowTimestamp());
        if( messageMapper.insertSelective(message)!=1){
            resultBean.setState(0);
            resultBean.setMessage("发送消息失败！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        resultBean.setState(1);
        resultBean.setMessage("发送成功！");
        resultBean.setObject(message);
        return FastJsonUtils.toJSONString(resultBean);
    }
}
