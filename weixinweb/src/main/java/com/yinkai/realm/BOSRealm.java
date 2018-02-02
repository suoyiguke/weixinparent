package com.yinkai.realm;

import com.yinkai.entities.Admin;
import com.yinkai.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


public class BOSRealm extends AuthorizingRealm{

    @Resource
    private AdminService adminService;

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("自定义的realm中认证方法执行了。。。。");
        String username = (String) token.getPrincipal();
        Admin admin = adminService.findByUsername(username);
        if (admin == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("213123123");


        return null;
    }

  /*  //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录用户
        TUser user = (TUser) principals.getPrimaryPrincipal();
        List<Function> list= null;
        //为用户授权
        if(user.getUsername().equals("admin")){

            DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
            //超级管理员。拥有所有权限
            list = funcationDao.findByCriteria(criteria);

        }else{
            //不是超级管理员 ，则要根据用户id去插询它所拥有的权限
            list = funcationDao.findFunctionListByUserId(user.getId());
        }

        //授权
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }


        return info;
    }*/

}