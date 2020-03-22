package com.city.blog.blog.mapper;

import com.city.blog.blog.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/10
 * Time: 0:10
 * Description: No Description
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit ${offset},${size}")
    List<Question> questionList(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator=#{userId}")
    Integer countById(@Param("userId") Integer userId);

    @Select("select * from question where creator=#{userId} limit ${offset},${size}")
    List<Question> questionByList(@Param("userId") Integer userId,@Param("offset") Integer offset,@Param("size") Integer size);
}
