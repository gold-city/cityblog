package com.city.blog.blog.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/5
 * Time: 21:04
 * Description: No Description
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type=type;
    }
}
