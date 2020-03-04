package com.city.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/4
 * Time: 1:06
 * Description: No Description
 */
@Controller
public class AuthorizeController {
    @RequestMapping(value = "/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        //点击登录，跳到github项目id携带参数，访问返回项目服务器地址接收code，进行验证，验证完再跳到github获取个人信息，然后返回，然后项目获取用户名
        //通过okhttp在java后台进行访问url地址获取信息（okhttp是一个插件，有在后台访问网站的功能）

        return "callback";
    }
}
