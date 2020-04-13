package com.city.blog.blog.controller;

import com.city.blog.blog.dto.CommentDTO;
import com.city.blog.blog.dto.CommentListDTO;
import com.city.blog.blog.dto.ResultDTO;
import com.city.blog.blog.enums.CommentTypeEnum;
import com.city.blog.blog.exception.CustomizeErrorCode;
import com.city.blog.blog.model.Comment;
import com.city.blog.blog.model.User;
import com.city.blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private CommentService commentService;
    /**
     * @ResponseBody对象转json给前端，如果返回的数据不用转换json则不需要写
     */
    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody/*接收前端的json数据转对象*/ CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Comment comment = new Comment();
            //前端拿到评论的类型，评论的是问题还是问题的评论
            comment.setType(commentDTO.getType());
            comment.setContent(commentDTO.getContent());
            //前端拿到回复的评论的id
            comment.setParentId(commentDTO.getParentId());
            comment.setCommentator(user.getId());
            comment.setGmtCreate(System.currentTimeMillis());
            //service细化流程，对属性值异常处理
            commentService.insertSelective(comment);
            return ResultDTO.okOf();
        }
        //抛异常使用码
        return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
    }

    @ResponseBody
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentListDTO>> getTwoComment(@PathVariable("id") Integer id){
        List<CommentListDTO> commentListDTOS = commentService.queryCommentByQuestionId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentListDTOS);
    }
}
