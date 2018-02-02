package com.yinkai.test;

import com.yinkai.entities.Forum;
import com.yinkai.entities.UserExample;
import com.yinkai.entities.custom.UserCustom;
import com.yinkai.mapper.ForumMapper;
import com.yinkai.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yinkai.entities.User;
import java.util.List;

public class SchoolMapperTest {

    private ApplicationContext applicationContext;
    @Before
    public void before(){

         applicationContext =new ClassPathXmlApplicationContext("classpath:springmvc-dao.xml");


    }



  //  @Test
    public void selectByExample() throws Exception {
        UserMapper userMapperCustom = applicationContext.getBean(UserMapper.class);
        List<User> list = userMapperCustom.selectByExample(new UserExample());
        for (User user : list) {

           // School school = user.getSchool();
           // System.out.println(school.toString());

        }
}


    @Test
    public void findAllUser(){

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        List<UserCustom> list = userMapper.findAllUser();
        for (UserCustom user : list) {
            System.out.println(user.toString());
        }
    }


    @Test
    public void updateUser(){

        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user = new User();
        user.setSchoolId(403);
        user.setNickname("鞋子");
        user.setSex("女");
        user.setAutograph("xiuxixixi");
        user.setOpenId("oiO0e0dMItRyuqpHbeoji-mp5r64");
        user.setUserId(36);

        int i = userMapper.updateByPrimaryKeySelective(user);
        System.out.println(i);

    }

    @Test
    public void xxx(){
        ForumMapper forumMapper = applicationContext.getBean(ForumMapper.class);
        Integer[] ints = new Integer[10];
        ints[0]=8;
        ints[1]=9;
        int i = forumMapper.deleteForum(ints);
        System.out.println(i);

    }

}