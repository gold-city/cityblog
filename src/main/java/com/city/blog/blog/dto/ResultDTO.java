package com.city.blog.blog.dto;

import com.city.blog.blog.exception.CustomizeErrorCode;
import com.city.blog.blog.exception.CustomizeException;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/5
 * Time: 12:36
 * Description: No Description
 * 
 * @author Cheng
 * 异步请求返回message信息（插入是否成功等）
 */
@Data
public class ResultDTO<T> {
    private String message;

    /**
     * 给message一个表示码，前端通过标识码定义下一步操作
     */
    private Integer code;
    private T data;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        return errorOf(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！");
        return resultDTO;
    }
    public static ResultDTO errorOf(CustomizeException ex){
        return errorOf(ex.getCode(),ex.getMessage());
    }
    public static <T> ResultDTO okOf(T t){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！");
        resultDTO.setData(t);
        return resultDTO;
    }
}