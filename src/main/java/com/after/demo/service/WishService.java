package com.after.demo.service;

import com.after.demo.entity.Wish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 22:56
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public interface WishService {

    /**
     * 增加数据
     * @param userName
     * @param avatarUrl
     * @param content
     * @param time
     * @return
     */
    int saveWish(String userName,String avatarUrl,String content,String time);

    /**
     * 获取所有数据
     * @return
     */
    List<Wish> listWish();

    /**
     * 根据id获取愿望
     * @param id
     * @return
     */
    Wish getOneWishById(int id);

    /**
     * 根据用户名获取所有的数据
     * @param userName
     * @return
     */
    List<Wish> getWishByName(String  userName);

    /**
     * 根据指定开始数获取6条数据
     * @param start
     * @return
     */
    List<Wish> getWishByLimit(int start);

    /**
     * 更新数据
     * @param content
     * @param time
     * @param id
     * @return
     */
    int updateWish(String content,String time,int id);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int deleteWish(int id);
}