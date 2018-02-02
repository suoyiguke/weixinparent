package com.yinkai.service;

import com.yinkai.entities.School;

import java.util.List;

public interface SchoolService {

    public List<School> findAllSchool();

    List<School> serachSchoolByName(String q);
}
