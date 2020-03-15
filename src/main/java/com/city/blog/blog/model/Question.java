package com.city.blog.blog.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/10
 * Time: 0:15
 * Description: No Description
 */
@Data
public class Question {
    private Integer id;//发布-问题的id
    private String title;//问题的标题
    private String description;//问题的描述
    private Long gmt_create;//创建时间
    private Long gmt_modified;//修改时间
    private Integer creator;//创建该问题的用户id
    private Integer comment_count;//总评论数量
    private Integer view_count;//点击数
    private Integer like_count;//收藏数
    private String tag;//标签
}