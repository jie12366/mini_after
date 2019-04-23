package com.after.demo.service.impl;

import com.after.demo.entity.Plan;
import com.after.demo.mapper.PlanMapper;
import com.after.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/21 14:08
 */
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    PlanMapper planMapper;

    @Override
    public int savePlan(String userName, String content) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return planMapper.savePlan(userName, content,sdf.format(date));
    }

    @Override
    public List<Plan> listPlanByName(String userName) {
        return planMapper.listPlanByName(userName);
    }
}