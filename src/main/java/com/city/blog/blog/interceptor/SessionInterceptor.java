package com.city.blog.blog.interceptor;

import com.city.blog.blog.mapper.BlogUserMapper;
import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.BlogUser;
import com.city.blog.blog.model.BlogUserExample;
import com.city.blog.blog.model.User;
import com.city.blog.blog.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Autowired
    private BlogUserMapper blogUserMapper;
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
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(userToken);
                    List<User> users = userMapper.selectByExample(userExample);
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    BlogUserExample userExample2 = new BlogUserExample();
                    userExample2.createCriteria().andTokenEqualTo(userToken);
                    List<BlogUser> users2 = blogUserMapper.selectByExample(userExample2);
                    if (users2.size() != 0) {
                        request.getSession().setAttribute("user", users2.get(0));
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