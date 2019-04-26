package com.after.demo.controller;

import com.after.demo.entity.Wish;
import com.after.demo.service.impl.UploadServiceImpl;
import com.after.demo.service.impl.WishServiceImpl;
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
 * @date 2019/4/16 22:50
 */
@RestController
public class WishController {

    @Autowired
    WishServiceImpl wishService;
    @Autowired
    UploadServiceImpl uploadService;

    @ApiOperation("根据用户名返回所有wish")
    @PostMapping("/wish/get")
    public JsonResult getWish(@ApiParam("用户名") String nickName){
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
    public JsonResult saveWish(@ApiParam("wish内容") String content,@ApiParam("用户名") String userName,
                               @ApiParam("用户头像") String avatarUrl,@ApiParam("存入时间") String time){
        wishService.saveWish(userName,avatarUrl,content,time);
        return JsonResult.ok();
    }

    @ApiParam("根据id更新wish")
    @PostMapping("/wish/update")
    public JsonResult updateDiary(@ApiParam("wishID") int id,
                                  @ApiParam("wish内容") String content,@ApiParam("存入时间") String time){
        wishService.updateWish(content,time,id);
        return JsonResult.ok();
    }

    @ApiParam("根据id删除wish")
    @PostMapping("/wish/delete")
    public JsonResult deleteDiary(@ApiParam("wishID") int id){
        int ok = wishService.deleteWish(id);
        return JsonResult.ok(ok);
    }
}