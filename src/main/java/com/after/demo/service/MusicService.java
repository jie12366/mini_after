package com.after.demo.service;

import com.after.demo.entity.Music;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/19 21:09
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface MusicService {

    /**
     * 将爬取的歌曲信息存入数据库
     * @param name 歌曲名
     * @param imgUrl 歌曲封面
     * @param src 歌曲地址
     * @return 是否成功
     */
    int saveMusic(String name,String imgUrl,String src);

    /**
     * 获取数据库中的歌曲信息
     * @return list
     */
    List<Music> listMusic();

    /**
     * 根据id随机获取一首歌
     * @param id int
     * @return Music
     */
    Music getMusicById(int id);
}