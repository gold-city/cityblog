package com.city.blog.blog.service;

import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/24
 * Time: 22:24
 * Description: No Description
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void updateUser(User user1) {
        User dbUser = userMapper.queryUserByAccountId(user1.getAccount_id());
        if (dbUser == null) {
            //将插入时间放里面可以避免每次查询github都跟新插入数据库的时间，而头像，名字不用，因为github可能更改
            user1.setGmt_create(System.currentTimeMillis());//用户建入数据库的时间
            user1.setGmt_modified(user1.getGmt_create());//用户修改用户信息的时间
            userMapper.insert(user1);
        }else {
            dbUser.setGmt_modified(System.currentTimeMillis());
            dbUser.setAvatar_url(user1.getAvatar_url());
            dbUser.setName(user1.getName());
            dbUser.setToken(user1.getToken());
            userMapper.updateUser(dbUser);
        }
    }
}