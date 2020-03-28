package com.city.blog.blog.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/4
 * Time: 21:41
 * Description: No Description
 */
@Data
public class AccessTokenDTO {
    //github返回access_token所携带的参数
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
