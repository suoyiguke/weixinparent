package com.yinkai.service;

import com.yinkai.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public String findAllUser() throws Exception;

    public String findUserById(Long id)throws Exception;

    String pageQuery(Integer page, Integer count) throws Exception;

    String addLikeTopic(String openId, Integer topicId) throws Exception;

    String deleteLikeTopic(String openId, Integer topicId) throws Exception;

    String findUserByOpenId(String openId)throws Exception;

    String addUser(User user, MultipartFile uploadFile)throws Exception;


    List<User> serachUserByName(String q)throws Exception;


    String checkUser(String code) throws Exception;

    String determineUser(String nickname);

    String getUserLikeState(String openId)throws Exception;
}

