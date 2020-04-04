package com.city.blog.blog.service;

import com.city.blog.blog.dto.PaginationDTO;
import com.city.blog.blog.dto.QuestionDTO;
import com.city.blog.blog.exception.CustomizeErrorCode;
import com.city.blog.blog.exception.CustomizeException;
import com.city.blog.blog.mapper.QuestionExtMapper;
import com.city.blog.blog.mapper.QuestionMapper;
import com.city.blog.blog.mapper.UserMapper;
import com.city.blog.blog.model.Question;
import com.city.blog.blog.model.QuestionExample;
import com.city.blog.blog.model.User;
import org.apache.ibatis.session.RowBounds;
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
    /**
     * 个人设置数据库方法
     * 先写代码，每个功能对应一张表，需要用到另一张表的时候
     * 用一个字段存放另一个表的使用到的id减少数据的沉余
     * 用到两张表的时候不使用sql联合查询，新建一个第三方数据传输实体类DTO
     * 通过service调用mapper组合数据封装到DTO返回前端
     * */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private QuestionMapper questionMapper;


    //业务逻辑，组装user和question
    public PaginationDTO questionList(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = (int)questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPagination(count, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        //（i-1）*5
        Integer offset = (page - 1) * size;
        //List<Question> questions = questionMapper.questionList(offset, size);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        paginationDTO.setQuestions(questionDTOList);
        //获取页面总数
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }

    public PaginationDTO questionByUserIdList(Integer userId,Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer count = (int)questionMapper.countByExample(questionExample);
        paginationDTO.setPagination(count, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        //（i-1）*5
        Integer offset = (page - 1) * size;
        QuestionExample questionExample1 = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        paginationDTO.setQuestions(questionDTOList);
        //获取页面总数
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }


    public QuestionDTO queryQuestionByQuestionId(Integer questionId) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }


    public void insertOrUpdate(Question question,Integer questionId) {
        if (questionId!=null&&question.getId()!=null){
            if (questionId.equals(question.getId())){
                //更新
                Question question1 = new Question();
                question1.setGmtModified(System.currentTimeMillis());
                question1.setTitle(question.getTitle());
                question1.setDescription(question.getDescription());
                question1.setTag(question.getTag());
                QuestionExample questionExample = new QuestionExample();
                questionExample.createCriteria().andIdEqualTo(question.getId());
                int i = questionMapper.updateByExampleSelective(question1, questionExample);
                if (i != 1) {
                    throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                }
            }else {
                //问题不存在
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_EXIST);
            }
        }else{
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            //insertSelective选择性插入如果值为空，则不插入该值，insert，全部插入，如果值为空，则插入空
            questionMapper.insertSelective(question);
        }
    }

    public void incView(Integer questionId) {
        //存在线程并发安全问题
        /*Question question1 = questionMapper.selectByPrimaryKey(questionId);
        Question question = new Question();
        question.setViewCount(question1.getViewCount()+1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(questionId);
        questionMapper.updateByExampleSelective(question,questionExample);*/
        Question question = new Question();
        question.setId(questionId);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}