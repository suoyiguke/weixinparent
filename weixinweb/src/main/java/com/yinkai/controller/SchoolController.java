package com.yinkai.controller;

import com.yinkai.entities.School;
import com.yinkai.service.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SchoolController {

    @Resource
    private SchoolService schoolService;


    @RequestMapping(value="/schoollist",method = RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<School> showPage(){

       return schoolService.findAllSchool();

    }

    /**
     * 后台-----serachSchoolByName
     */

    @RequestMapping(value="/serachSchoolByName",method = RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<School> serachSchoolByName(String q){

        return schoolService.serachSchoolByName(q);

    }


}
