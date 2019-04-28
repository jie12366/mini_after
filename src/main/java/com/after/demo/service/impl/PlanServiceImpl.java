package com.after.demo.service.impl;

import com.after.demo.entity.Plan;
import com.after.demo.entity.Progress;
import com.after.demo.mapper.PlanMapper;
import com.after.demo.mapper.ProgressMapper;
import com.after.demo.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    ProgressMapper progressMapper;

    @Override
    public int savePlan(String userName, String content) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(date);
        int res = planMapper.savePlan(userName, content,date1);
        Progress progress = progressMapper.getProgress(userName,date1);
        System.out.println(progress);
        if (progress == null){
            progressMapper.saveProgress(userName,"0/0",date1);
        }else {
            int res1 = updateProgress(userName,date1,progress.getId());
        }
        return res;
    }

    private int updateProgress(String userName,String time,int progressId){
        int status1 = planMapper.getSumByStatus(userName,time);
        System.out.println(status1);
        int sum = planMapper.listPlanByName(userName,time).size();
        System.out.println(sum);
        String value = status1 + "/" + sum;
        return progressMapper.updateProgress(value,progressId);
    }

    @Override
    public List<Plan> listPlanByName(String userName,String time) {
        return planMapper.listPlanByName(userName,time);
    }

    @Override
    public Progress getProgress(String userName, String time) {
        return progressMapper.getProgress(userName, time);
    }

    @Override
    public int updateStatus(int id) {
        int status = planMapper.getStatusById(id);
        System.out.println("status = " + status);
        Plan plan = planMapper.getPlanById(id);
        String userName = plan.getUserName();
        String date = plan.getTime();
        Progress progress = progressMapper.getProgress(userName,date);
        if (status == 1){
            int res = planMapper.updateStatus(0,id);
            updateProgress(userName,date,progress.getId());
            return res;
        }else if(status == 0){
            int res = planMapper.updateStatus(1,id);
            updateProgress(userName,date,progress.getId());
            return res;
        }
        return 0;
    }

    @Override
    public int deletePlan(int id) {
        return planMapper.deletePlan(id);
    }
}