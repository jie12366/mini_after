package com.after.demo.controller;

import com.after.demo.entity.Wish;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.service.impl.WishServiceImpl;
import com.after.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 22:50
 */
@RestController
public class WishController {

    @Autowired
    WishServiceImpl wishService;
    @Autowired
    UploadServiceImpl uploadService;

    @PostMapping("/wish/get")
    public JsonResult getWish(HttpServletRequest request){
        String userName = request.getParameter("nickName");
        List<Wish> wishList = wishService.getWishByName(userName);
        return JsonResult.ok(wishList);
    }

    @PostMapping("/wish/getLimit")
    public JsonResult getLimit(){
        int size = wishService.listWish().size();
        int start = (int)(Math.random() * size);
        if (start < 4){
            start = 0;
        }
        List<Wish> wishList = wishService.getWishByLimit(start);
        return JsonResult.ok(wishList);
    }

    @PostMapping("/wish/save")
    public JsonResult saveWish(HttpServletRequest request){
            String content = request.getParameter("content");
            String userName = request.getParameter("nickName");
            String avatarUrl = request.getParameter("avatarUrl");
            String time = request.getParameter("time");
            wishService.saveWish(userName,avatarUrl,content,time);
        return JsonResult.ok();
    }

    @PostMapping("/wish/update")
    public JsonResult updateDiary(HttpServletRequest request){
            int id = Integer.parseInt(request.getParameter("id"));
            String content = request.getParameter("content");
            String time = request.getParameter("time");
            wishService.updateWish(content,time,id);
        return JsonResult.ok();
    }

    @PostMapping("/wish/delete")
    public JsonResult deleteDiary(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int ok = wishService.deleteWish(id);
        return JsonResult.ok(ok);
    }
}