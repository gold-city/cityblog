package com.city.blog.blog.service;

import com.city.blog.blog.mapper.BlogUserMapper;
import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.BlogUser;
import com.city.blog.blog.model.BlogUserExample;
import com.city.blog.blog.model.User;
import com.city.blog.blog.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private BlogUserMapper blogUserMapper;
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

    public Integer login(String name,String password){
        BlogUserExample blogUserExample = new BlogUserExample();
        blogUserExample.createCriteria().andNameEqualTo(name);
        List<BlogUser> blogUsers = blogUserMapper.selectByExample(blogUserExample);
        if (blogUsers.size()!=0){
            for (BlogUser blogUser : blogUsers) {
                String password1 = blogUser.getPassword();
                if (password.equals(password1)){
                    return 0;
                }else{
                    return 3;
                }
            }
        }else {
            return 1;
        }
        return 2;
    }
    public Integer regist(String name, String password){
        BlogUserExample blogUserExample = new BlogUserExample();
        blogUserExample.createCriteria().andNameEqualTo(name);
        List<BlogUser> blogUsers = blogUserMapper.selectByExample(blogUserExample);
        if (blogUsers.size()!=0){
            return 2;
        }else {
            BlogUser blogUser = new BlogUser();
            blogUser.setName(name);
            blogUser.setAvatarUrl("/image/Headportrait.jpg");
            blogUser.setGmtCreate(System.currentTimeMillis());
            blogUser.setGmtModified(System.currentTimeMillis());
            blogUser.setPassword(password);
            String token = UUID.randomUUID().toString();
            blogUser.setToken(token);
            int i = blogUserMapper.insertSelective(blogUser);
            return i;
        }
    }
}