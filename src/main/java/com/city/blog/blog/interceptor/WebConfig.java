package com.city.blog.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/22
 * Time: 21:47
 * Description: No Description
 *
 *
 * 过滤器
 * @EnableWebMvc---拦截静态资源
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有地址执行sessionintercceptor方法
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}