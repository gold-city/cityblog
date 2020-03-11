package com.city.blog.blog;

import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        int a=10;
        int b=20;
        System.out.println("a+b="+a+b);
        System.out.println("a+b="+(a+b));
        System.out.println(a+b);
    }
    @Test
    void contextLoads1() {
        User user = userMapper.queryUserByToken("104455ab-28fd-4e5b-af9f-c9ede9fdf182");
        System.out.println(user);
    }
}
