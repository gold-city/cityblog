package com.city.blog.blog.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/30
 * Time: 11:39
 * Description: No Description
 *
 * 定义一个异常信息的枚举接口，通过该类的枚举编号获取值
 */
public interface ICustomizeErrorCode {
    String getMessage();
    Integer getCode();
}