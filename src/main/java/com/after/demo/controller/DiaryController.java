package com.after.demo.controller;

import com.after.demo.entity.Diary;
import com.after.demo.service.impl.DiaryServiceImpl;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    public JsonResult getDiary(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        List<Diary> diaryList = diaryService.getDiaryByName(nickName);
        return JsonResult.ok(diaryList);
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

    @ApiOperation("根据id更新日记")
    @PostMapping("/diary/update")
    public JsonResult updateDiary(HttpServletRequest request,
                                  @RequestParam("image") MultipartFile image){
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        try {
            diaryService.updateDiary(uploadService.getPic(request,image),title,content,time,id);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.ok();
    }

    @ApiOperation("根据id删除日记")
    @PostMapping("/diary/delete")
    public JsonResult deleteDiary(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int ok = diaryService.deleteDiary(id);
        return JsonResult.ok(ok);
    }
}