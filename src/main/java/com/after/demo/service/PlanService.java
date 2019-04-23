package com.after.demo.service;

import com.after.demo.entity.Plan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/21 14:07
 */
@Service
public interface PlanService {

    /**
     * 增加计划
     * @param userName 用户名
     * @param content 内容
     * @return
     */
    int savePlan(String userName,String content);

    /**
     * 根据用户名获取计划信息
     * @param userName 用户名
     * @return
     */
    List<Plan> listPlanByName(String userName);
}