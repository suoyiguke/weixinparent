
package com.yinkai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*** <p>Title:PageController </p>* 
     <p>Description: TODO(这里用一句话描述这个类的作用) </p>* 
     <p>Company: 广州番禺职业技术学院。移动互联网开发1班</p> *
     @author Administrator* 
     @date 2017年8月14日上午11:21:32 *
     @version 1.0 */

@Controller //首页访问
public class PageController {

	
	@RequestMapping("/")
	public String showIndex(){

		return "page/common/login";
	}



	//请求一个视图。返回一个视图
	 	 @RequestMapping("{folder1}_{folder2}_{page}")
	 	 public String showPage(@PathVariable(value = "folder1") String folder1,@PathVariable(value = "folder2") String folder2,@PathVariable(value = "page") String page){

			 return folder1+"/"+folder2+"/"+page;
	 	 }
	 	 
	 	 
	 	 
	 	 

	 }


