package com.after.demo.service.impl;

import com.after.demo.entity.Music;
import com.after.demo.mapper.MusicMapper;
import com.after.demo.service.MusicService;
import com.after.demo.utils.GetString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/19 21:09
 */
@Service
@CacheConfig
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicMapper musicMapper;

    @Override
    public int saveMusic(String name, String imgUrl, String src) {
        List<Music> musicList = musicMapper.listMusic();
        for (Music music:musicList){
            if (music.getName().equals(name)){
                return 0;
            }
        }
        return musicMapper.saveMusic(name,imgUrl,src);
    }

    @Override
    @Cacheable(value = "music")
    public List<Music> listMusic() {
        return musicMapper.listMusic();
    }

    @Override
    public Music getMusicById(int id) {
        int maxSize = GetString.MAXSIZE;
        if (id <= maxSize){
            return musicMapper.getMusicById(id);
        }
        return null;
    }
}