package com.city.blog.blog.dto;

import com.city.blog.blog.model.Comment;
import com.city.blog.blog.model.User;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/11
 * Time: 15:39
 * Description: No Description
 */
@Data
public class CommentListDTO extends Comment {
    private User user;
}
