package com.city.blog.blog.controller;

import com.city.blog.blog.dto.CommentDTO;
import com.city.blog.blog.mapper.CommentMapper;
import com.city.blog.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/4
 * Time: 11:58
 * Description: No Description
 */
@Controller
public class CommmentController {

    @Autowired
    private CommentMapper commentMapper;

    /*@ResponseBody对象转json给前端，如果返回的数据不用转换json则不需要写*/
    @ResponseBody
    @PostMapping("/comment")
    public HashMap<String,String> post(@RequestBody/*接收前端的json数据转对象*/ CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setCommentator(1);
        comment.setGmtCreate(System.currentTimeMillis());
        int i = commentMapper.insertSelective(comment);
        if (i!=1){
            return null;
        }
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("message","回复成功！");
        return stringStringHashMap;
    }
}
