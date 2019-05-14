package com.after.demo.controller;

import com.after.demo.entity.Wish;
import com.after.demo.service.MessageService;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.service.impl.WishServiceImpl;
import com.after.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    MessageService messageService;

    @ApiOperation("根据用户名返回所有wish")
    @PostMapping("/wish/get")
    public JsonResult getWish(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        List<Wish> wishList = wishService.getWishByName(nickName);
        return JsonResult.ok(wishList);
    }

    @ApiOperation("随机返回4条wish")
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

    @ApiOperation("将wish数据存入数据库")
    @PostMapping("/wish/save")
    public JsonResult saveWish(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        String avatarUrl = request.getParameter("avatarUrl");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        wishService.saveWish(nickName,avatarUrl,content,time);
        return JsonResult.ok();
    }

    @ApiOperation("根据id更新wish")
    @PostMapping("/wish/update")
    public JsonResult updateDiary(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        wishService.updateWish(content,time,id);
        return JsonResult.ok();
    }

    @ApiOperation("根据id删除wish")
    @PostMapping("/wish/delete")
    public JsonResult deleteDiary(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        int ok = wishService.deleteWish(id);
        return JsonResult.ok(ok);
    }

    @ApiOperation("增加评论")
    @PostMapping("/message/save")
    public JsonResult saveMessage(@ApiParam("愿望id") @RequestParam("wishId") int wishId,
                                  @ApiParam("用户名") @RequestParam("nickName") String nickName,
                                  @ApiParam("用户头像") @RequestParam("avatarUrl") String avatarUrl,
                                  @ApiParam("用户评论") @RequestParam("mess") String mess){

        int res = messageService.saveMessage(wishId,nickName,avatarUrl,mess);
        return JsonResult.ok(res);
    }

    @ApiOperation("根据id获取评论")
    @PostMapping("/message/get")
    public JsonResult getMessage(@ApiParam("愿望id") @RequestParam("wishId") int wishId){

        return JsonResult.ok(messageService.listMessage(wishId));
    }
}