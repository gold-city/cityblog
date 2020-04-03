package com.city.blog.blog.advice;

import com.city.blog.blog.exception.CustomizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
    ModelAndView handle(Throwable ex){//ex:异常类型，课自定义异常，ex的值为自己的异常时处理
        ModelAndView modelAndView = new ModelAndView("error");
        //当ex对象是CustomizeException的实例时返回true
        if (ex instanceof CustomizeException){
            modelAndView.addObject("message",ex.getMessage());
        }else {
            modelAndView.addObject("message","网络崩溃,请稍后访问!");
        }
        return modelAndView;
    }
}
