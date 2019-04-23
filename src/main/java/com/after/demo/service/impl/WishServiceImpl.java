package com.after.demo.service.impl;

import com.after.demo.entity.Wish;
import com.after.demo.mapper.WishMapper;
import com.after.demo.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 22:56
 */
@Service
public class WishServiceImpl implements WishService {

    @Autowired
    WishMapper wishMapper;

    @Override
    public int saveWish(String userName, String avatarUrl, String content, String time) {
        return wishMapper.saveWish(userName, avatarUrl, content, time);
    }

    @Override
    public List<Wish> listWish() {
        return wishMapper.listWish();
    }

    @Override
    public List<Wish> getWishByName(String userName) {
        return wishMapper.getWishByName(userName);
    }

    @Override
    public List<Wish> getWishByLimit(int start) {
        return wishMapper.getWishByLimit(start);
    }

    @Override
    public int updateWish(String content, String time, int id) {
        return wishMapper.updateWish(content, time, id);
    }

    @Override
    public int deleteWish(int id) {
        return wishMapper.deleteWish(id);
    }
}