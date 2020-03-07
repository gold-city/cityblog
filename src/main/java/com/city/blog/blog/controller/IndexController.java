package com.city.blog.blog.controller;

import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/3
 * Time: 0:42
 * Description: No Description
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    //这里的逻辑相当于过滤器，如果没有登录不能通过该接口跳转首页，重新验证查询是否存在用户还可以解决重启服务器刷新页面登录失效问题
    @RequestMapping("/")//设置首页
    public String index(HttpServletRequest request){
        //插入数据库后，自定义token的cookie跳转到这里进行去到首页，插完之后再查才能通过登录，
        // 在这里通过token去数据库查user为空则不登陆（减少可能插入失败的可能），不为空则登录
        //插入的时候把token放入了cookie中，通过response放token通过request拿
        Cookie[] tokens = request.getCookies();
        if (tokens != null) {
            for (Cookie token : tokens) {
                if ("token".equals(token.getName())){
                    String userToken = token.getValue();
                    //通过token查数据库是否存在,这样就不用没登录的时候每次都去访问github登录了，这样很慢，判断数据库库是或否存在用户不存在再登录
                    User user = userMapper.queryUserByToken(userToken);
                    if (user != null) {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}