package com.after.demo.mapper;

import com.after.demo.entity.Diary;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 20:05
 */
@Mapper
public interface DiaryMapper {

    /**
     * 插入数据
     * @param userName
     * @param photo
     * @param title
     * @param content
     * @param time
     * @param sentiment
     * @return
     */
    @Insert("insert into diary(userName,photo,title,content,time,sentiment) values(#{userName},#{photo},#{title},#{content},#{time},#{sentiment})")
    int saveDiary(String userName,String photo,String title,String content,String time,int sentiment);

    /**
     * 获取所有数据
     * @return
     */
    @Select("select * from diary")
    List<Diary> listDiary();

    /**
     * 根据id获取数据
     * @param userName
     * @return
     */
    @Select("select * from diary where userName=#{userName}")
    List<Diary> getDiaryByName(String  userName);

    /**
     * 更新日记
     * @param title
     * @param photo
     * @param content
     * @param time
     * @param id
     * @param sentiment
     * @return
     */
    @Update("update diary set photo=#{photo},title=#{title},content=#{content},time=#{time},sentiment=#{sentiment} where id=#{id}")
    int updateDiary(String photo,String title,String content,String time,int sentiment,int id);

    /**
     * 删除日记
     * @param id
     * @return
     */
    @Delete("delete from diary where id=#{id}")
    int deleteDiary(int id);
}