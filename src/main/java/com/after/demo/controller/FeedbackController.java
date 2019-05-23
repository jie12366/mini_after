package com.after.demo.controller;

import com.after.demo.mapper.FeedbackMapper;
import com.after.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/23 22:03
 */
@RestController
public class FeedbackController {

    @Autowired
    FeedbackMapper feedbackMapper;

    @ApiOperation("保存用户提交的反馈信息")
    @PostMapping("/feedback/save")
    public JsonResult saveFeedback(@ApiParam("用户名")@RequestParam("nickName") String nickName,
                                   @ApiParam("内容")@RequestParam("content") String content){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int res = feedbackMapper.saveFeedback(nickName,content,sdf.format(date));
        return JsonResult.ok(res);
    }
}