package com.after.demo.service;

import com.after.demo.entity.Plan;
import com.after.demo.entity.Progress;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/21 14:07
 */
@Service
@CacheConfig
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
     * @param time 时间
     * @return
     */
    List<Plan> listPlanByName(String userName,String time);

    /**
     * 根据用户名和时间获取计划进度
     * @param userName
     * @param time
     * @return
     */
    Progress getProgress(String userName,String time);

    /**
     * 更改计划完成的状态
     * @param id ID
     * @return
     */
    int updateStatus(int id);

    /**
     * 根据id删除计划
     * @param id
     * @return
     */
    int deletePlan(int id);
}