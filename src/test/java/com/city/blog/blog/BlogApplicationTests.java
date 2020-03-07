package com.city.blog.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
        int a=10;
        int b=20;
        System.out.println("a+b="+a+b);
        System.out.println("a+b="+(a+b));
        System.out.println(a+b);
    }

}
