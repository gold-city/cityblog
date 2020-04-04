package com.city.blog.blog.service;

import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.User;
import com.city.blog.blog.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user1.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            //将插入时间放里面可以避免每次查询github都跟新插入数据库的时间，而头像，名字不用，因为github可能更改
            user1.setGmtCreate(System.currentTimeMillis());//用户建入数据库的时间
            user1.setGmtModified(user1.getGmtCreate());//用户修改用户信息的时间
            userMapper.insertSelective(user1);
        }else {
            User dbUser = users.get(0);//数据库user拿id查询
            User updateUser = new User();//更新的user
            updateUser.setGmtModified(System.currentTimeMillis());//设置更新的值
            updateUser.setAvatarUrl(user1.getAvatarUrl());
            updateUser.setName(user1.getName());
            updateUser.setToken(user1.getToken());
            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andIdEqualTo(dbUser.getId());//根据id查
            //查到的user和updateuser相比插入
            userMapper.updateByExampleSelective(updateUser,userExample1);//读方法名，updateByExample更新自定义sql，updateByExampleSelective更新局部变量
        }
    }
}