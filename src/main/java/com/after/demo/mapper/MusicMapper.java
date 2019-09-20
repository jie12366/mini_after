package com.after.demo.mapper;

import com.after.demo.entity.Music;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/19 21:03
 */
@Mapper
public interface MusicMapper {

    /**
     * 将爬取的歌曲信息存入数据库
     * @param name 歌曲名
     * @param imgUrl 歌曲封面
     * @param src 歌曲地址
     * @return 是否成功
     */
    @Insert("insert into music(name,imgUrl,src) values(#{name},#{imgUrl},#{src})")
    int saveMusic(String name,String imgUrl,String src);

    /**
     * 获取数据库记录的大小
     * @return list
     */
    @Select("select count(*) from music")
    int getSize();

    /**
     * 根据名字判断是否存在
     * @param name
     * @return
     */
    @Select("select * from music where name=#{name}")
    Music getIdByName(String name);

    /**
     * 根据id随机获取一首歌
     * @param id int
     * @return Music
     */
    @Select("select * from music where id=#{id}")
    Music getMusicById(int id);
}