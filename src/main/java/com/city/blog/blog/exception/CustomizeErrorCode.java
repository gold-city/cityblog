package com.city.blog.blog.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/2
 * Time: 9:43
 * Description: No Description
 *
 * 异常信息枚举,为什么要使用接口的形式，因为每个模块的异常信息可以分开定义，使用同一个接口，否则的话全部枚举都放一块难管理
 * new枚举类设置参数，返回接口，接口
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("该文章不存在或已删除！"),
    QUESTION_NOT_EXIST("该问题不存在或与用户不匹配，请重试！");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}