package com.city.blog.blog.mapper;

import com.city.blog.blog.model.BlogUser;
import com.city.blog.blog.model.BlogUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BlogUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    long countByExample(BlogUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int deleteByExample(BlogUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int insert(BlogUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int insertSelective(BlogUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    List<BlogUser> selectByExampleWithRowbounds(BlogUserExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    List<BlogUser> selectByExample(BlogUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    BlogUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int updateByExampleSelective(@Param("record") BlogUser record, @Param("example") BlogUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int updateByExample(@Param("record") BlogUser record, @Param("example") BlogUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int updateByPrimaryKeySelective(BlogUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BLOG_USER
     *
     * @mbg.generated Fri May 15 12:53:08 CST 2020
     */
    int updateByPrimaryKey(BlogUser record);
}