package com.yinkai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinkai.entities.Image;
import com.yinkai.entities.ImageExample;
import com.yinkai.entities.Topic;
import com.yinkai.entities.custom.TopicCustom;
import com.yinkai.entities.custom.UserCustom;
import com.yinkai.entities.vo.SearchTopicVo;
import com.yinkai.mapper.*;
import com.yinkai.service.TopicService;
import com.yinkai.utilpojo.PageBean;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import com.yinkai.utils.FtpUtil;
import com.yinkai.utils.IDUtils;
import com.yinkai.utils.wenxinapputils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//默认将类中的所有函数纳入事务管理.
@Transactional(readOnly = true) //配置事务 查询使用只读
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;
    @Resource
    private ImageMapper imageMapper;
    @Resource
    private ForumMapper forumMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private UserTopicMapper userTopicMapper;


    //动态获取图片服务器的ip地址，现在图片服务器被我配置在本地
    @Value("${IMAGE_SRC}")
    private String IMAGE_SRC;

    @Override
    public String pageQuery(Integer page, Integer rows, SearchTopicVo searchTopicVo) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (page == null | rows == null | searchTopicVo == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.pageQuery参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }

        PageHelper.startPage(page, rows);
        List<TopicCustom> list = topicMapper.findTopicByLike(searchTopicVo);
        //调用公共代码
        PageBean pageBean = result(list);
        return FastJsonUtils.toJSONString(pageBean);

    }


    @Override
    public String myTopicPageQuery(Integer userId, Integer page, Integer rows) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (userId == null | page == null | rows == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.myTopicPageQuery参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        List<TopicCustom> list = topicMapper.findTopicByUserId(userId);

        //调用公共代码
        PageBean pageBean = result(list);

        return FastJsonUtils.toJSONString(pageBean);
    }

    @Override
    public String myLikeTopicPageQuery(Integer userId, Integer page, Integer rows) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (userId == null | page == null | rows == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.myLikeTopicPageQuery参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }

        PageHelper.startPage(page, rows);
        List<TopicCustom> list = topicMapper.findUserLikeTopic(userId);
        //调用公共代码
        PageBean pageBean = result(list);
        return FastJsonUtils.toJSONString(pageBean);

    }


    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    @Value("${DEFAULT_FORUM_ID}")
    private Integer DEFAULT_FORUM_ID;


    /**
     * 三操作：insert 帖子，上传图片，insert 图片
     *
     * @param request
     * @param openId
     * @param forumId
     * @param topic
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public String addTopic(MultipartHttpServletRequest request, String openId, Integer forumId, Topic topic) throws Exception {

        ResultBean resultBean = new ResultBean();
        if (openId == null | topic == null | request == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.addTopic参数为空！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        topic.setType(1);//默认添加到校外
        if (forumId == null) {//版块id为空说明添加到校内
            topic.setType(0);
            forumId = DEFAULT_FORUM_ID;//校内默认添加至0版块
        }

        //插入topic
        UserCustom userCustom = userMapper.findUserCustomByOpenId(openId);
        topic.setUserId(userCustom.getUserId());
        topic.setForumId(forumId);
        topic.setCreatetime(wenxinapputils.getNowTimestamp());
        if (topicMapper.insertSelective(topic) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("添加帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        //版块帖子数量+1
        if (forumMapper.addOneTopiccount(forumId) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("添加帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        boolean flag = false;

        //批量照片上传
        List<MultipartFile> files = request.getFiles("file");
        for (MultipartFile file : files) {

            // 原图片名
            String oldName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(oldName)){
                //新文件名=随机字符串+原文件后缀
                String newName = IDUtils.genImageName();
                newName = newName + oldName.substring(oldName.lastIndexOf("."));
                flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, "/", newName, file.getInputStream());
                if (flag == false) {
                    resultBean.setState(0);
                    resultBean.setMessage("添加帖子失败，图片上传失败！ftp服务器操作出错！");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return FastJsonUtils.toJSONString(resultBean);
                }//如果上传ftp失败，则直接返回0.表示图片上传失败！

                Image image = new Image();
                image.setTopicId(topic.getTopicId());//TODO 需要获得刚插入的topicId
                image.setName(newName);
                //插入图片
                if (imageMapper.insertSelective(image) != 1) {
                    resultBean.setState(0);
                    resultBean.setMessage("添加帖子失败！");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return FastJsonUtils.toJSONString(resultBean);
                }//如果插入数据失败，则直接返回0.表示图片上传失败！

            }


        }

        resultBean.setState(1);
        resultBean.setMessage("添加帖子成功！");
        TopicCustom topicCustom = topicMapper.findTopicById(topic.getTopicId());

        List<TopicCustom> list = new ArrayList<TopicCustom>();
        list.add(topicCustom);
        result(list);
        resultBean.setObject(list.get(0));
        return FastJsonUtils.toJSONString(resultBean);


    }


    @Override
    public String getThisSchoolTopic(String openId, Integer page, Integer rows) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(openId) | page == null | rows == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.getThisSchool参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        UserCustom userCustom = userMapper.findUserCustomByOpenId(openId);
        PageBean result = result(topicMapper.findTopicBySchoolId(userCustom.getSchoolId()));
        return FastJsonUtils.toJSONString(result);
    }

    @Override
    public String getOtherSchoolTopic(Integer page, Integer rows, Integer forumId) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (forumId == null | page == null | rows == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.getOtherSchoolTopic参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        PageBean result = result(topicMapper.findOtherTopicBySchoolId(forumId));
        return FastJsonUtils.toJSONString(result);
}

    @Override
    public String getLikeTopicPageQuery(String openId, Integer page, Integer rows) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(openId)| page == null | rows == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.getLikeTopicPageQuery参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        PageBean result = result(topicMapper.getLikeTopicPageQuery(openId));
        return FastJsonUtils.toJSONString(result);
    }

    @Override
    public String getMyTopic(String openId, Integer page, Integer rows) {
        ResultBean resultBean = new ResultBean();
        if (openId == null | page == null | rows == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.getMyTopic参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        PageHelper.startPage(page, rows);
        List<TopicCustom> list = topicMapper.findTopicByOpenId(openId);

        //调用公共代码
        PageBean pageBean = result(list);

        return FastJsonUtils.toJSONString(pageBean);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public String deleteTopicByOpenId(String openId, Integer topicId) {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(openId) | topicId == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.TopicServiceImpl.deleteTopicByOpenId参数为空！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }

        //目标帖子是否属于该该用户？  这个问题可以不管他，因为我得sql的where子句用了 AND 并列
        Topic topic = topicMapper.selectByPrimaryKey(topicId);
        //版块的帖子数减一
        if (topic==null){
            resultBean.setState(0);
            resultBean.setMessage("删除帖子失败！原因：数据库中不存在该帖子！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }

        //删除该帖子一对多的评论
        //返回0则没有评论
        if (replyMapper.deleteReplyByTopicId(topicId)<0){
            resultBean.setState(0);
            resultBean.setMessage("删除帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }

        //删除帖子一对多的图片
        //返回0则没有图片
        if(imageMapper.deleteImageByTopicId(topicId)<0){
            resultBean.setState(0);
            resultBean.setMessage("删除帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }

        //用户收藏过该帖子的话也需要删除
        int i = userTopicMapper.deleteByTopicId(topicId);
        System.out.println("删除喜欢的帖子成功！删除数"+i);


        //删除帖子
        if (topicMapper.deleteTopicByOpenId(openId, topicId)!=1){
            resultBean.setState(0);
            resultBean.setMessage("删除帖子失败！原因：目标帖子不属于你，无权删除！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }


        if (forumMapper.deleteOneTopicNum(topic.getForumId())!=1){
            resultBean.setState(0);
            resultBean.setMessage("删除帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }


        resultBean.setState(1);
        resultBean.setMessage("删除帖子成功！");
        return FastJsonUtils.toJSONString(resultBean);
    }


    /**
     * 所有的查询分页查询的放回list都组装成以下的数据结构
     * 把公共代码分装成方法
     */

    private PageBean result(List<TopicCustom> list) {

        for (TopicCustom topicCustom : list) {
            ImageExample imageExample = new ImageExample();
            //拼接图片路径返回给app
            ImageExample.Criteria criteria = imageExample.createCriteria();
            criteria.andTopicIdEqualTo(topicCustom.getTopicId());
            List<Image> images = imageMapper.selectByExample(imageExample);

            for (Image image : images) {
                image.setName(IMAGE_SRC + image.getName());
            }
            topicCustom.setImageList(images);

            Integer headImageId = topicCustom.getHeadImageId();
            if(headImageId !=null){
                Image image = imageMapper.selectByPrimaryKey(topicCustom.getHeadImageId());
                String sr = IMAGE_SRC + image.getName();
                topicCustom.setHeadimage(sr);
            }

        }

        PageInfo<TopicCustom> pageInfo = new PageInfo<TopicCustom>(list);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(Integer.parseInt(pageInfo.getTotal() + ""));
        pageBean.setRows(list);

        return pageBean;
    }



}
