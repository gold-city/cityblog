package com.city.blog.blog.controller;

import com.city.blog.blog.dto.PaginationDTO;
import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.User;
import com.city.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/3
 * Time: 0:42
 * Description: No Description
 */
@Controller
//前后端分离的话返回的全是数据，每个加@ResponseBody,可以再类中加@RestController,是controller和RestController的结合
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    //这里的逻辑相当于过滤器，如果没有登录不能通过该接口跳转首页，重新验证查询是否存在用户还可以解决重启服务器刷新页面登录失效问题
    @GetMapping("/")//设置首页
    public String index(HttpServletRequest request, Map<String,Object> map,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,//默认页码第几页，显示的数据是从几到几的总量是size
                        @RequestParam(name = "size",defaultValue = "13") Integer size) {//数据量
        //插入数据库后，自定义token的cookie跳转到这里进行去到首页，插完之后再查才能通过登录，
        // 在这里通过token去数据库查user为空则不登陆（减少可能插入失败的可能），不为空则登录
        //插入的时候把token放入了cookie中，通过response放token通过request拿
        Cookie[] tokens = request.getCookies();
        if (tokens != null && tokens.length != 0) {
            for (Cookie token : tokens) {
                if ("token".equals(token.getName())) {
                    String userToken = token.getValue();
                    //通过token查数据库是否存在,这样就不用没登录的时候每次都去访问github登录了，这样很慢，判断数据库库是或否存在用户不存在再登录
                    User user = userMapper.queryUserByToken(userToken);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
            //获取所有发布数据,其数据种包括user的信息，所以使用service进行数据组合，包装成dto对象返回
            /*
            *select * from question limit 0,5;查询前五条数据
            *0开始，查5条数据，5开始，查五条数据
            *0,5     1  (i-1)*5
            *5,5     2
            *10，5   3
            * */
            PaginationDTO paginationDTO = questionService.questionList(page,size);
            map.put("paginationDTO",paginationDTO);
            return "index";
        }else {
            return "index";
        }
    }
}