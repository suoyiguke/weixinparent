package com.yinkai.service.impl;

import com.yinkai.entities.Admin;
import com.yinkai.entities.AdminExample;
import com.yinkai.mapper.AdminMapper;
import com.yinkai.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Override
    public Admin findByUsername(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size()!=0) {
            return list.get(0);
        }
        return null;

    }

}
