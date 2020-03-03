package com.city.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/3
 * Time: 0:42
 * Description: No Description
 */
@Controller
public class IndexController {
    @GetMapping("/")//设置首页
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/toIndex")
    public String toIndex(){
        return "index";
    }
}