package com.city.blog.blog.interceptor;

import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/22
 * Time: 21:56
 * Description: No Description
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {
//验证是否登录
    @Autowired
    private UserMapper userMapper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] tokens = request.getCookies();
        if (tokens != null && tokens.length != 0) {
            for (Cookie token : tokens) {
                if ("token".equals(token.getName())) {
                    String userToken = token.getValue();
                    User user = userMapper.queryUserByToken(userToken);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }
}