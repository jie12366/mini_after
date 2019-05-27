package com.after.demo.mapper;

import com.after.demo.entity.Plan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/21 14:00
 */
@Mapper
public interface PlanMapper {

    /**
     * 增加计划
     * @param userName 用户名
     * @param content 内容
     * @param time 时间
     * @return
     */
    @Insert("insert into plan(userName,content,time) values(#{userName},#{content},#{time})")
    int savePlan(String userName,String content,String time);

    /**
     * 根据用户名获取计划信息
     * @param userName 用户名
     * @param time 时间
     * @return
     */
    @Select("select * from plan where userName=#{userName} and time=#{time}")
    List<Plan> listPlanByName(String userName,String time);

    /**
     * 根据id获取plan信息
     * @param id
     * @return
     */
    @Select("select * from plan where id=#{id}")
    Plan getPlanById(int id);

    /**
     * 根据id获取当前计划的状态
     * @param id
     * @return
     */
    @Select("select status from plan where id=#{id}")
    int getStatusById(int id);

    /**
     * 根据用户名和时间获取已完成的计划数
     * @param userName
     * @param time
     * @return
     */
    @Select("select COUNT(status=1) from plan where userName=#{userName} and time=#{time}")
    int getSumByStatus(String userName,String time);

    /**
     * 更改计划完成的状态
     * @param status 状态
     * @param id ID
     * @return
     */
    @Update("update plan set status=#{status} where id=#{id}")
    int updateStatus(int status,int id);

    /**
     * 根据id更新计划，并将status重置
     * @param content
     * @param time
     * @param status
     * @param id
     * @return
     */
    @Update("update plan set content=#{content},time=#{time},status=#{status} where id=#{id}")
    int updatePlan(String content,String time,int status,int id);

    /**
     * 根据id删除计划
     * @param id
     * @return
     */
    @Delete("delete from plan where id=#{id}")
    int deletePlan(int id);
}