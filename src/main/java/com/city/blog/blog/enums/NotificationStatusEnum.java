package com.city.blog.blog.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/17
 * Time: 17:59
 * Description: No Description
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1)
    ;

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }}
