package com.yinkai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinkai.entities.Image;
import com.yinkai.entities.User;
import com.yinkai.entities.UserExample;
import com.yinkai.entities.custom.UserCustom;
import com.yinkai.entities.vo.UserTopicVo;
import com.yinkai.mapper.ImageMapper;
import com.yinkai.mapper.TopicMapper;
import com.yinkai.mapper.UserMapper;
import com.yinkai.mapper.UserTopicMapper;
import com.yinkai.service.UserService;
import com.yinkai.utilpojo.PageBean;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import com.yinkai.utils.FtpUtil;
import com.yinkai.utils.HttpRequest;
import com.yinkai.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

//默认将类中的所有函数纳入事务管理.
@Transactional(readOnly = true) //配置事务 查询使用只读
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private ImageMapper imageMapper;

    @Resource
    private UserTopicMapper userTopicMapper;

    @Override
    public String findAllUser() {
        return null;
    }


    @Override
    public String findUserById(Long id) {
        return null;
    }


    @Value("${IMAGE_SRC}")
    private String IMAGE_SRC;

    @Override
    public String pageQuery(Integer page, Integer count) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (page == null | count == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.pageQuery参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }

        PageHelper.startPage(page, count);
        List<UserCustom> list = userMapper.findAllUser();
        for (UserCustom userCustom : list) {
            Image image = imageMapper.selectByPrimaryKey(userCustom.getHeadimageId());
            String sr = IMAGE_SRC + image.getName();
            userCustom.setHeadimage(sr);

        }

        PageInfo<UserCustom> pageInfo = new PageInfo<UserCustom>(list);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(Integer.parseInt(pageInfo.getTotal() + ""));
        pageBean.setRows(list);
        return FastJsonUtils.toJSONString(pageBean);

    }

    /**
     * 根据sessionId添加特定用户喜欢的贴子
     *
     * @param openId
     * @param topicId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public String addLikeTopic(String openId, Integer topicId) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (openId == null | topicId == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.addLikeTopic参数为空！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }

        UserCustom userCustom = userMapper.findUserCustomByOpenId(openId);//根据openId查询 userId
        UserTopicVo userTopicVo = new UserTopicVo();
        userTopicVo.getUser().setUserId(userCustom.getUserId());
        userTopicVo.getTopic().setTopicId(topicId);

        //插入user_topic表
        //可能会发生唯一冲突异常，用户重复添加喜欢的贴子。在异常管理器里处理吧
        if (userMapper.addLikeTopic(userTopicVo) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("添加喜欢的帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        //topic.likenum+1
        if (topicMapper.addOneLikeNum(topicId) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("添加喜欢的帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);

        }
        resultBean.setState(1);
        resultBean.setMessage("添加喜欢的帖子成功！");
        return FastJsonUtils.toJSONString(resultBean);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public String deleteLikeTopic(String openId, Integer topicId) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (openId == null | topicId == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.deleteLikeTopic参数为空！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        UserCustom userCustom = userMapper.findUserCustomByOpenId(openId);//根据openId查询 userId
        UserTopicVo userTopicVo = new UserTopicVo();
        userTopicVo.getUser().setUserId(userCustom.getUserId());
        userTopicVo.getTopic().setTopicId(topicId);

        if (userMapper.deleteLikeTopic(userTopicVo) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("删除喜欢的帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        if (topicMapper.deleteOneLikeNum(topicId) != 1) {
            resultBean.setState(0);
            resultBean.setMessage("删除喜欢的帖子失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }
        resultBean.setState(1);
        resultBean.setMessage("删除喜欢的帖子成功！");
        return FastJsonUtils.toJSONString(resultBean);
    }

    @Override
    public String findUserByOpenId(String openId) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(openId)) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.findUserByOpenId参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        UserCustom userCustom = userMapper.findUserCustomByOpenId(openId);
        utils(userCustom);
        return FastJsonUtils.toJSONString(userCustom);
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

    //默认头像图片id
    @Value("${DEFAULT_IMAGE_ID}")
    private Integer DEFAULT_IMAGE_ID;

    /**
     * 三个操作： 上传图片、insert图片、insert用户
     *
     * @param user
     * @param uploadFile
     * @return
     * @throws Exception
     */

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    @Override
    public String addUser(User user, MultipartFile uploadFile) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (user == null | user.getNickname() == null) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.addUser参数为空！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtils.toJSONString(resultBean);
        }



        if (uploadFile != null&&StringUtils.isNotBlank(uploadFile.getOriginalFilename())) {

            // 原图片名
            String oldName = uploadFile.getOriginalFilename();
            //新文件名=随机字符串+原文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));

            boolean flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, "/", newName, uploadFile.getInputStream());
            if (!flag) {
                resultBean.setState(0);
                resultBean.setMessage("添加新用户失败！连接ftp服务失败！");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return FastJsonUtils.toJSONString(resultBean);
            }
            //图片已上传！再将保存到数据库里
            Image image = new Image();
            image.setName(newName);
            if (imageMapper.insertSelective(image) != 1) {
                resultBean.setState(0);
                resultBean.setMessage("添加新用户失败！");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return FastJsonUtils.toJSONString(resultBean);
            }
            //给新用户指定刚刚上传的图片
            user.setHeadimageId(image.getImageId());

        }

        UserCustom uc = userMapper.findUserCustomByOpenId(user.getOpenId());
        if (uc == null) {
            //数据库中没有改用户，则插入数据
            //插入用户数据
            //如果用户没有上传图片，则使用系统默认头像
              user.setHeadimageId(DEFAULT_IMAGE_ID);
            if (userMapper.insertSelective(user) != 1) {
                resultBean.setState(0);
                resultBean.setMessage("添加新用户失败！");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return FastJsonUtils.toJSONString(resultBean);
            }
        } else {
            //数据库中有该用户记录则修改数据
            user.setUserId(uc.getUserId());//给user添加id，不然按主键修改不成功！
            if (userMapper.updateByPrimaryKeySelective(user) != 1) {
                resultBean.setState(0);
                resultBean.setMessage("修改用户失败！");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return FastJsonUtils.toJSONString(resultBean);
            }
        }

        //需要返回封装过的用户结果集
        UserCustom userCustom = userMapper.findUserCustomByOpenId(user.getOpenId());
        utils(userCustom);
        resultBean.setState(1);
        resultBean.setMessage("添加/修改用户成功！");
        resultBean.setObject(userCustom);

        return FastJsonUtils.toJSONString(resultBean);

    }


    //这个是查找方法，如果q为空的活将查询所有。dao层已近处理了q为空的情况，因此这里不用再进行非空判断！
    @Override
    public List<User> serachUserByName(String q) throws Exception {

        UserExample example = new UserExample();
        if (StringUtils.isNotBlank(q)) {
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andNicknameEqualTo(q);
        }

        return userMapper.selectByExample(example);
    }

    @Override
    public String checkUser(String code) throws Exception {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(code)) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.checkUser参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String param = "appid=wxa46bdfe4100c559f&secret=af3b421cc0e82bc1dc7a51d44703253d&js_code=" + code + "&grant_type=authorization_code";
        return HttpRequest.sendGet(url, param);

    }

    @Override
    public String determineUser(String nickname) {
        ResultBean resultBean = new ResultBean();
        if (StringUtils.isBlank(nickname)) {
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.determineUser参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        User user = userMapper.determineUser(nickname);
        if (user!=null){
            resultBean.setState(0);
            resultBean.setMessage("用户名已存在！");
            return FastJsonUtils.toJSONString(resultBean);
        }
        resultBean.setState(1);
        resultBean.setMessage("用户名可用！");
        return FastJsonUtils.toJSONString(resultBean);
    }

    @Override
    public String getUserLikeState(String openId) throws Exception {
        if (StringUtils.isBlank(openId)) {
            ResultBean resultBean = new ResultBean();
            resultBean.setState(0);
            resultBean.setMessage("com.yinkai.service.impl.UserServiceImpl.getUserLikeState参数为空！");
            return FastJsonUtils.toJSONString(resultBean);
        }
       return  FastJsonUtils.toJSONString(userTopicMapper.findUserTopicByOpenId(openId));

    }


    /**
     * 抽取功共方法
     */
    private void utils(UserCustom userCustom) {
        Image image = imageMapper.selectByPrimaryKey(userCustom.getHeadimageId());
        String sr = IMAGE_SRC + image.getName();
        userCustom.setHeadimage(sr);
        userCustom.setOpenId("");
    }


}

