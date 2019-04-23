package com.after.demo.controller;

import com.after.demo.entity.Diary;
import com.after.demo.service.impl.DiaryServiceImpl;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.utils.GetString;
import com.after.demo.utils.JsonResult;
import com.after.demo.utils.QiniuUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    @PostMapping("/diary/get")
    public JsonResult getDiary(HttpServletRequest request){
        String userName = request.getParameter("nickName");
        List<Diary> diaryList = diaryService.getDiaryByName(userName);
        return JsonResult.ok(diaryList);
    }

    @PostMapping("/diary/save")
    public JsonResult saveDiary(HttpServletRequest request,@RequestParam(value = "image", required = false) MultipartFile image){
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String userName = request.getParameter("nickName");
            String time = request.getParameter("time");
            diaryService.saveDiary(userName,uploadService.getPic(request,image),title,content,time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.ok();
    }

    @PostMapping("/diary/update")
    public JsonResult updateDiary(HttpServletRequest request,@RequestParam("image") MultipartFile image){
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String time = request.getParameter("time");
            diaryService.updateDiary(uploadService.getPic(request,image),title,content,time,id);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.ok();
    }

    @PostMapping("/diary/delete")
    public JsonResult deleteDiary(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int ok = diaryService.deleteDiary(id);
        return JsonResult.ok(ok);
    }
}