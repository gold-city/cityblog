package com.city.blog.blog.controller;

import com.city.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/14
 * Time: 21:52
 * Description: No Description
 */
@Controller
public class registController {

    @Autowired
    private UserService userService;

    @GetMapping("/toRegist")
    public String toRegist(){
        return "regist";
    }
    @PostMapping("/regist")
    public String regist(Map<String,String> map, @RequestParam("name") String name, @RequestParam("password")String password){
        if (name!=null&&name.length()!=0&&password!=null&&password.length()!=0){
            Integer regist = userService.regist(name, password);
            if (regist==1){
                map.put("message","注册成功！");
                return "regist";
            }else if (regist==2){
                map.put("message","用户名已存在,请更换！");
                return "regist";
            }
        }else{
            map.put("message","请输入用户名密码！");
            return "regist";
        }
        map.put("message","注册失败，请重试！");
        return "regist";
    }
}
