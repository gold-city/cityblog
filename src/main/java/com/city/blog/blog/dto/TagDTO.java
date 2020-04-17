package com.city.blog.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/16
 * Time: 16:11
 * Description: No Description
 */
@Data
public class TagDTO {
    private String bigTag;
    private List<String> tag;
}
