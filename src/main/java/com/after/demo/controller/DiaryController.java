package com.after.demo.controller;

import com.after.demo.entity.Diary;
import com.after.demo.service.impl.DiaryServiceImpl;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 20:28
 */
@RestController
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;

    @Autowired
    UploadServiceImpl uploadService;

    @ApiOperation("获取某个用户的所有日记")
    @PostMapping("/diary/get")
    public JsonResult getDiary(@ApiParam("用户名")@RequestParam("nickName") String nickName,
                               @ApiParam("当前页") @RequestParam("start") int start,
                               @ApiParam("每页大小") @RequestParam("size") int size){
        //用pageHelper拦截器分页
        PageHelper.startPage(start,size,"time desc");
        List<Diary> diaryList = diaryService.getDiaryByName(nickName);
        return JsonResult.ok(diaryList);
    }

    @ApiOperation("获取一篇日记")
    @PostMapping("/diary/getOne")
    public JsonResult getDiary(@ApiParam("日记id") @RequestParam("id") int id){
        Diary diary = diaryService.getDiaryById(id);
        return JsonResult.ok(diary);
    }

    @ApiOperation("将用户的日记存入数据库")
    @PostMapping("/diary/save")
    public JsonResult saveDiary(HttpServletRequest request,
                                @RequestParam(value = "image", required = false) MultipartFile image){
        String nickName = request.getParameter("nickName");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        try{
            diaryService.saveDiary(nickName,uploadService.getPic(request,image),title,content,time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.ok();
    }

    @ApiOperation("根据id更新日记（更新图片）")
    @PostMapping("/diary/update1")
    public JsonResult updateDiary(HttpServletRequest request,
                                  @ApiParam("图片") @RequestParam("image") MultipartFile image,
                                  @ApiParam("日记id") @RequestParam("id") int id,
                                  @ApiParam("标题") @RequestParam("title") String title,
                                  @ApiParam("内容") @RequestParam("content") String content,
                                  @ApiParam("时间") @RequestParam("time") String time){
        try {
            diaryService.updateDiary(uploadService.getPic(request,image),title,content,time,id);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.ok();
    }

    @ApiOperation("根据id更新日记（不更新图片）")
    @PostMapping("/diary/update2")
    public JsonResult updateDiary(@ApiParam("图片") @RequestParam("image") String image,
                                  @ApiParam("日记id") @RequestParam("id") int id,
                                  @ApiParam("标题") @RequestParam("title") String title,
                                  @ApiParam("内容") @RequestParam("content") String content,
                                  @ApiParam("时间") @RequestParam("time") String time){

        int res = diaryService.updateDiary(image,title,content,time,id);
        return JsonResult.ok(res);
    }

    @ApiOperation("根据id删除日记")
    @PostMapping("/diary/delete")
    public JsonResult deleteDiary(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int ok = diaryService.deleteDiary(id);
        return JsonResult.ok(ok);
    }

    @ApiOperation("根据用户名和时间获取过去一周的情绪值")
    @PostMapping("/sentiment/get")
    public JsonResult getSentiment(HttpServletRequest request){
        String userName = request.getParameter("nickName");
        String time = request.getParameter("time");
        JSONObject jsonObject = diaryService.listSentiment(userName,time);
        return JsonResult.ok(jsonObject);
    }
}