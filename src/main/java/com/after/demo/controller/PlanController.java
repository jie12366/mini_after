package com.after.demo.controller;

import com.after.demo.entity.Plan;
import com.after.demo.entity.Progress;
import com.after.demo.service.impl.PlanServiceImpl;
import com.after.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("将用户的计划存入数据库")
    @PostMapping("/plan/save")
    public JsonResult savePlan(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        String content = request.getParameter("content");
        planService.savePlan(nickName,content);
        return JsonResult.ok();
    }

    @ApiOperation("根据用户名和时间返回计划")
    @PostMapping("/plan/get")
    public JsonResult getPlan(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        String date = request.getParameter("date");
        List<Plan> planList = planService.listPlanByName(nickName,date);
        return JsonResult.ok(planList);
    }

    @ApiOperation("根据id删除计划")
    @PostMapping("/plan/delete")
    public JsonResult deletePlan(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int res = planService.deletePlan(id);
        return JsonResult.ok(res);
    }

    @ApiOperation("根据id更新计划的状态")
    @PostMapping("/plan/update")
    public JsonResult updatePlanStatus(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int res = planService.updateStatus(id);
        if (res == 1){
            return JsonResult.ok(res);
        }else {
            return JsonResult.errorMsg("error");
        }
    }

    @ApiOperation("根据用户名和时间获取计划进度")
    @PostMapping("/progress/get")
    public JsonResult getProgress(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        String date = request.getParameter("date");
        Progress progress = planService.getProgress(nickName,date);
        if (progress == null){
            return JsonResult.errorMsg("error");
        }
        return JsonResult.ok(progress);
    }
}