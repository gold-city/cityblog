package com.city.blog.blog.controller;

import com.city.blog.blog.dto.AccessTokenDTO;
import com.city.blog.blog.dto.GitHubUser;
import com.city.blog.blog.model.User;
import com.city.blog.blog.provider.GithubProvider;
import com.city.blog.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/4
 * Time: 1:06
 * Description: No Description
 *
 * ctrl+e在idea内切换class文件
 * @author Cheng
 */
@Controller
@Slf4j//日志打印
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    /**
        获取code配置文件中的参数信息
    */
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;
    @GetMapping(value = "/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
                           HttpServletRequest request, HttpServletResponse response){
        //点击登录，跳到github项目id携带参数，访问返回项目服务器地址接收code，进行验证，验证完再跳到github获取个人信息，然后返回，然后项目获取用户名
        //通过okhttp在java后台进行访问url地址获取信息（okhttp是一个插件，有在后台访问网站的功能）
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessTokenDTO1 = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GitHubUser user = githubProvider.getUser(accessTokenDTO1);
        //user.notnull
        //判断状态，空则登录失败，有则登录成功
        if (user != null&& user.getId() != null) {
            //插入数据库
            User user1 = new User();
            String token = UUID.randomUUID().toString();
            user1.setToken(token);//通过token绑定前端和后端的登录状态，选中值，ctrl+alt+v抽出变量
            //token,标识，指令
            //UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。UUID(Universally Unique Identifier)
            // 全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，是由一个十六位的数字组成,表现出来的 形式。
            //使用token的思想可以为，数据库需要与github的个人信息同步，token变化的时候从新跟新本站的user数据库，达到同步个人信息
            user1.setName(user.getName());
            user1.setAvatarUrl(user.getAvatarUrl());
            user1.setAccountId(String.valueOf(user.getId()));
            userService.updateUser(user1);
            //放入session，spring为session自动集成了自动的cookie；
            //request.getSession().setAttribute("user",user);
            //自定义session和cookie,使用指令标识符token进行绑定，前端判定token验证是否登录,以前逻辑是通过session中是否存在user（或name）对象
            response.addCookie(new Cookie("token",token));
            return "redirect:/";//重定向，回复地址,定向到/的requestmapper（“/”）:服务器内转服务器，加redirect否者认为跳页面，如果重定向页面则redirect：index。html，如果转发则index
        }else{
            //登录失败，打印日志
            log.error("user null,{}",user);
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //退出，删除cookie和session，跳到index
        request.getSession().removeAttribute("user");
        Cookie token = new Cookie("token", null);//替换
        token.setMaxAge(0);//设置最大时效为0，立即删除
        response.addCookie(token);
        return "redirect:/";
    }
}
