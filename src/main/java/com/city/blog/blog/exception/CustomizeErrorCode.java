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
    /*200*为系统异常*/
    QUESTION_NOT_FOUND(2001,"该文章不存在或已删除！"),
    NO_LOGIN(2002,"请登录后进行评论！"),
    QUESTION_NOT_EXIST(2003,"该问题不存在或与用户不匹配，请重试！"),
    TARGET_PARAM_NOT_EXIST(2004,"请选择问题或评论进行回复！"),
    SYS_ERROR(2005,"网络崩溃,请稍后访问!"),
    TYPE_PARAM_WRONG(2006,"该评论级别出错或已删除！"),
    COMMENT_NOT_FOUND(2007,"你回复的评论不存在或已删除！");

    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}