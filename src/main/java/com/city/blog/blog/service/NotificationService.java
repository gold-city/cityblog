package com.city.blog.blog.service;

import com.city.blog.blog.dto.NotificationDTO;
import com.city.blog.blog.enums.NotificationTypeEnum;
import com.city.blog.blog.mapper.CommentMapper;
import com.city.blog.blog.mapper.NotificationMapper;
import com.city.blog.blog.mapper.QuestionMapper;
import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/17
 * Time: 21:25
 * Description: No Description
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    public List<NotificationDTO> list(Integer id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(id);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setNotification(notification);
            User user = userMapper.selectByPrimaryKey(id);
            String name = user.getName();
            notificationDTO.setCommentUserName(name);
            Integer type = notification.getType();
            Integer outerid = notification.getOuterid();
            if (type==1){
                notificationDTO.setTypeName(NotificationTypeEnum.REPLY_QUESTION.getName());
                Question question = questionMapper.selectByPrimaryKey(outerid);
                notificationDTO.setOuterTitle(question.getTitle());
            }else{
                notificationDTO.setTypeName(NotificationTypeEnum.REPLY_COMMENT.getName());
                Comment comment = commentMapper.selectByPrimaryKey(outerid);
                notificationDTO.setOuterTitle(comment.getContent());
            }
            notificationDTOS.add(notificationDTO);
        }
        return notificationDTOS;
    }
}