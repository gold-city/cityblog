package com.city.blog.blog.service;

import com.city.blog.blog.dto.QuestionDTO;
import com.city.blog.blog.mapper.QuestionMapper;
import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.Question;
import com.city.blog.blog.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/14
 * Time: 23:08
 * Description: No Description
 */
@Service
public class QuestionService {
    /*
    * 个人设置数据库方法
    * 先写代码，每个功能对应一张表，需要用到另一张表的时候
    * 用一个字段存放另一个表的使用到的id减少数据的沉余
    * 用到两张表的时候不使用sql联合查询，新建一个第三方数据传输实体类DTO
    * 通过service调用mapper组合数据封装到DTO返回前端
    * */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    //业务逻辑，组装user和question
    public List<QuestionDTO> questionList() {
        List<Question> questions = questionMapper.questionList();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.queryUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
