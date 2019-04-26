package com.after.demo.controller;

import com.after.demo.entity.Plan;
import com.after.demo.service.impl.PlanServiceImpl;
import com.after.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("将用户的计划存入数据库")
    @PostMapping("/plan/save")
    public JsonResult savePlan(@ApiParam("用户名") String nickName, @ApiParam("计划内容") String content){
        planService.savePlan(nickName,content);
        return JsonResult.ok();
    }

    @ApiOperation("根据用户名返回所有计划")
    @PostMapping("/plan/get")
    public JsonResult getPlan(@ApiParam("用户名") String nickName){
        List<Plan> planList = planService.listPlanByName(nickName);
        return JsonResult.ok(planList);
    }
}