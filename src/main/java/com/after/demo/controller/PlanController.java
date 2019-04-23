package com.after.demo.controller;

import com.after.demo.entity.Plan;
import com.after.demo.service.impl.PlanServiceImpl;
import com.after.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/21 14:09
 */
@RestController
public class PlanController {

    @Autowired
    PlanServiceImpl planService;

    @PostMapping("/plan/save")
    public JsonResult savePlan(HttpServletRequest request){
        String userName = request.getParameter("nickName");
        String content = request.getParameter("content");
        planService.savePlan(userName,content);
        return JsonResult.ok();
    }

    @PostMapping("/plan/get")
    public JsonResult getPlan(HttpServletRequest request){
        String userName = request.getParameter("nickName");
        List<Plan> planList = planService.listPlanByName(userName);
        return JsonResult.ok(planList);
    }
}