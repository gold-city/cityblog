package com.city.blog.blog.service;

import com.city.blog.blog.dto.PaginationDTO;
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
    public PaginationDTO questionList(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.count();
        paginationDTO.setPagination(count, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        //（i-1）*5
        Integer offset = (page - 1) * size;
        List<Question> questions = questionMapper.questionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        paginationDTO.setQuestions(questionDTOList);
        //获取页面总数
        for (Question question : questions) {
            User user = userMapper.queryUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }

    public PaginationDTO questionByUserIdList(Integer userId,Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.countById(userId);
        paginationDTO.setPagination(count, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        //（i-1）*5
        Integer offset = (page - 1) * size;
        List<Question> questions = questionMapper.questionByList(userId,offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        paginationDTO.setQuestions(questionDTOList);
        //获取页面总数
        for (Question question : questions) {
            User user = userMapper.queryUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }


}