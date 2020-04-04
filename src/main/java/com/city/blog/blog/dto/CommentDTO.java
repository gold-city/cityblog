package com.city.blog.blog.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/4
 * Time: 12:13
 * Description: No Description
 * @author Cheng
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    /**
     * type=1为回复的级别
     */
    private Integer type;
}
