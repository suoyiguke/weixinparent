package com.yinkai.portal.exception;

import com.yinkai.utilpojo.CustomException;
import com.yinkai.utilpojo.ResultBean;
import com.yinkai.utils.FastJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CutomExceptionResolver implements HandlerExceptionResolver {


    private static final Logger logger = LoggerFactory.getLogger(CutomExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

        logger.info("进入全局异常处理器。。。。。。");
        String methodName = ((HandlerMethod) handler).getMethod().getName();
        System.out.println(methodName);
        ResultBean resultBean = new ResultBean();
        // 判断是否ajax请求
        String accept = request.getHeader("accept");
        if (accept != null && !(accept.indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            resultBean.setState(0);

            //自定义异常
            if (exception instanceof CustomException) {
                resultBean.setMessage(exception.getMessage());
            } else if (exception instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
                resultBean.setMessage("文件上传失败，原因：文件大小超过系统规定范围！");
            } else if (exception instanceof org.springframework.dao.DuplicateKeyException) {
                switch (methodName) {
                    case "addLikeTopic":
                        resultBean.setMessage("你已经喜欢过该贴子了！");
                        break;
                    case "addUser":
                        resultBean.setMessage("用户昵称已经存在！");
                        break;
                    default:
                        resultBean.setMessage("出现了违反唯一约束的异常！");
                        break;
                }
            } else {
                resultBean.setMessage("系统异常,未知错误！");
            }

            //这里需要手动将异常打印出来，由于没有配置log，实际生产环境应该打印到log里面
            exception.printStackTrace();

            //对于非ajax请求，我们都统一跳转到error.jsp页面
            return new ModelAndView("page/error/exception", "message", FastJsonUtils.toJSONNoFeatures(resultBean));
        } else {
            // 如果是ajax请求，JSON格式返回
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                resultBean.setState(0);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (exception instanceof CustomException) {
                    resultBean.setMessage(exception.getMessage());
                } else if (exception instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
                    resultBean.setMessage("文件上传失败，原因：文件大小超过系统规定范围！");
                }else if (exception instanceof org.springframework.dao.DuplicateKeyException) {
                    switch (methodName) {
                        case "addLikeTopic":
                            resultBean.setMessage("你已经喜欢过该贴子了！");
                            break;
                        case "addUser":
                            resultBean.setMessage("用户昵称已经存在！");
                            break;
                        default:
                            resultBean.setMessage("出现了违反唯一约束的异常！");
                            break;
                    }
                }else {
                    resultBean.setMessage("系统异常,未知错误！");
                }
                writer.write(FastJsonUtils.toJSONString(resultBean));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}

