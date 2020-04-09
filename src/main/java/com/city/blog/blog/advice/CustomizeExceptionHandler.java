package com.city.blog.blog.advice;

import com.alibaba.fastjson.JSON;
import com.city.blog.blog.dto.ResultDTO;
import com.city.blog.blog.exception.CustomizeErrorCode;
import com.city.blog.blog.exception.CustomizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/28
 * Time: 23:23
 * Description: No Description
 * @author Cheng
 *
 * 统一处理异常
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, HttpServletRequest request, HttpServletResponse response) throws IOException {//ex:异常类型，课自定义异常，ex的值为自己的异常时处理---方法返回obj，实际返回modelandview也能实现跳转
        ResultDTO resultDTO;
        String contentType = request.getContentType();
        ModelAndView modelAndView = new ModelAndView("error");
        String contentTypeEquals="application/json";
        if (contentTypeEquals.equals(contentType)){
            //返回json
            if (ex instanceof CustomizeException){
                resultDTO=ResultDTO.errorOf((CustomizeException)ex);
            }else {
                resultDTO=ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultDTO));
            writer.close();
            return null;
        }else {
            //跳转错误页面
            //当ex对象是CustomizeException的实例时返回true
            if (ex instanceof CustomizeException){
                modelAndView.addObject("message",ex.getMessage());
            }else {
                modelAndView.addObject("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return modelAndView;
        }
    }
}
