package com.city.blog.blog.mapper;

import com.city.blog.blog.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/6
 * Time: 23:11
 * Description: No Description
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);//sql语句中需要的值自动再类中找

    @Select("select * from user where token=#{userToken}")
    User queryUserByToken(@Param("userToken") String userToken);//不是类则自己获取
}
