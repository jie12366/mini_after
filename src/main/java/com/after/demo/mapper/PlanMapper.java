package com.after.demo.mapper;

import com.after.demo.entity.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
     * @return
     */
    @Select("select * from plan where userName=#{userName}")
    List<Plan> listPlanByName(String userName);
}