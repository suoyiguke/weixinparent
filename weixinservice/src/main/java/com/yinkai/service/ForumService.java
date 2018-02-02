package com.yinkai.service;

        import com.yinkai.entities.Forum;
        import com.yinkai.entities.custom.ForumCustom;
        import com.yinkai.utilpojo.CustomException;

        import java.util.List;


public interface ForumService {

    String addForum(Forum forum);
    List<ForumCustom> serachForumByLikeName(String q) throws CustomException;

    String forumPageQuery(Integer page ,Integer rows);

    String deleteForum(String forumIds);
}
