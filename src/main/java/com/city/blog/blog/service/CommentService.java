package com.city.blog.blog.service;

import com.city.blog.blog.dto.CommentListDTO;
import com.city.blog.blog.enums.CommentTypeEnum;
import com.city.blog.blog.enums.NotificationStatusEnum;
import com.city.blog.blog.enums.NotificationTypeEnum;
import com.city.blog.blog.exception.CustomizeErrorCode;
import com.city.blog.blog.exception.CustomizeException;
import com.city.blog.blog.mapper.*;
import com.city.blog.blog.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/5
 * Time: 21:12
 * Description: No Description
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;
    //事物-如果方法中需要多次对数据库进行关联的操作，则需要加入事物，要么全部成功，如果部分出现异常，则进行错做回滚
    @Transactional//springboot提供的事物注解
    public void insertSelective(Comment comment) {
        //service,对业务流程逻辑处理,在没插入前逻辑处理，而不是插入后出问题才处理
        if (comment.getParentId()==null||comment.getParentId()==0){//想评论但是被删了的情况为空
            //评论不存在，抛出异常，使用枚举，传递异常界面显示文字
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_EXIST);
        }
        if (StringUtils.isBlank(comment.getContent())){
            throw new CustomizeException(CustomizeErrorCode.CONTENT_NOT_FOUND);
        }

        if (comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            //评论--如果是评论的回复，则先通过parentid查到父级评论，进行逻辑处理
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (comment1 == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            Question question = questionMapper.selectByPrimaryKey(comment1.getParentId());
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            comment1.setCommentCount(1);
            commentExtMapper.incCommentCount(comment1);
            Notification notification = new Notification();
            notification.setGmtCreate((int)System.currentTimeMillis());
            notification.setType(NotificationTypeEnum.REPLY_COMMENT.getType());
            notification.setOuterid(comment.getParentId());
            notification.setNotifier(comment.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(comment1.getCommentator());
            notificationMapper.insertSelective(notification);
        }else {
            //回复，如果type是question（2）parentid是question的id，所以用parentid查评论的父级
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertSelective(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            Notification notification = new Notification();
            notification.setGmtCreate((int)System.currentTimeMillis());
            notification.setType(NotificationTypeEnum.REPLY_QUESTION.getType());
            notification.setOuterid(comment.getParentId());
            notification.setNotifier(comment.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(question.getCreator());
            notificationMapper.insertSelective(notification);
        }
    }

    public List<CommentListDTO> queryCommentByQuestionId(Integer questionId,CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTypeEqualTo(type.getType()).andParentIdEqualTo(questionId);
        //排序，查询出来后排序
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size()==0) {
            return new ArrayList<CommentListDTO>();
        }
        //抽取集合中的一个字段的值，重复会去掉，在根据值查取user
        Set<Integer> collect = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> integers = new ArrayList<>();
        integers.addAll(collect);
        UserExample userExample = new UserExample();
        //根据list id集合查询
        userExample.createCriteria().andIdIn(integers);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentListDTO> commentListDTOS=comments.stream().map(comment -> {
            CommentListDTO commentListDTO = new CommentListDTO();
            BeanUtils.copyProperties(comment,commentListDTO);
            commentListDTO.setUser(collect1.get(comment.getCommentator()));
            return commentListDTO;
        }).collect(Collectors.toList());

        return commentListDTOS;
    }
}
