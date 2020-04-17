package com.city.blog.blog.dto;

import com.city.blog.blog.model.Notification;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/17
 * Time: 21:20
 * Description: No Description
 */
@Data
public class NotificationDTO {
    //评论的问题的标题
    private String typeName;
    private Notification notification;
    private String outerTitle;
    private String commentUserName;
}
