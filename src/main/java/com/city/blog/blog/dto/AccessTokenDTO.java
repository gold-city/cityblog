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
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String state;
}
