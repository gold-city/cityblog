package com.city.blog.blog.mapper;

import com.city.blog.blog.model.Notification;
import com.city.blog.blog.model.NotificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NotificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    long countByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int deleteByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int insert(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int insertSelective(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    List<Notification> selectByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    Notification selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int updateByPrimaryKeySelective(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NOTIFICATION
     *
     * @mbg.generated Fri Apr 17 17:53:35 CST 2020
     */
    int updateByPrimaryKey(Notification record);
}