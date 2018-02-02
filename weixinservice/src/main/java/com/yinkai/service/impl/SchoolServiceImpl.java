package com.yinkai.service.impl;

import com.yinkai.entities.School;
import com.yinkai.entities.SchoolExample;
import com.yinkai.mapper.SchoolMapper;
import com.yinkai.service.SchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService{

    @Resource
    private SchoolMapper schoolMapper;
    @Override
    public List<School> findAllSchool() {
        SchoolExample example = new SchoolExample();
        //SchoolExample.Criteria criteria =example.createCriteria();
        return schoolMapper.selectByExample(example);
    }



    @Override
    public List<School> serachSchoolByName(String q) {
        return schoolMapper.serachSchoolByName(q);
    }
}
