package com.yinkai.test;

import com.yinkai.entities.Topic;
import com.yinkai.mapper.TopicMapper;
import com.yinkai.mapper.UserTopicMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-dao.xml","classpath:springmvc-transaction.xml"})
@Rollback(value=true)//回滚
@Transactional(transactionManager="transactionManager",readOnly = false)
public class mapperTest {

    @Resource
    UserTopicMapper userTopicMapper;
    @Resource
    TopicMapper topicMapper;
    @Test
    public void xx(){
       int i = userTopicMapper.deleteByTopicId(107);

        System.out.println(i);
    }

    @Test
    public void yy(){
        Integer forumIdByTopicId = topicMapper.getForumIdByTopicId(new Integer(108));
        System.out.println(forumIdByTopicId);
    }
    @Test
    public void zz(){
        Topic topic = topicMapper.selectByPrimaryKey(101);
        System.out.println(topic.getForumId());
    }

}
