package com.city.blog.blog.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/17
 * Time: 17:59
 * Description: No Description
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1," 回复了你的问题 "),
    REPLY_COMMENT(2," 回复了你的评论 ");
    /**
     * 枚举码
     */
    private int type;
    /**
     * 枚举信息
     */
    private String name;
    NotificationTypeEnum(int type, String name){
        this.type=type;
        this.name=name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }}
