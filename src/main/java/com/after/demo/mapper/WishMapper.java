package com.after.demo.mapper;

import com.after.demo.entity.Wish;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 22:51
 */
@Mapper
public interface WishMapper {

    /**
     * 插入数据
     * @param userName
     * @param avatarUrl
     * @param content
     * @param time
     * @return
     */
    @Insert("insert into wish(userName,avatarUrl,content,time) values(#{userName},#{avatarUrl},#{content},#{time})")
    int saveWish(String userName,String avatarUrl,String content,String time);

    /**
     * 获取所有数据
     * @return
     */
    @Select("select * from wish")
    List<Wish> listWish();

    /**
     * 根据id获取数据
     * @param userName
     * @return
     */
    @Select("select * from wish where userName=#{userName}")
    List<Wish> getWishByName(String  userName);

    /**
     * 根据指定开始数获取6条数据
     * @param start
     * @return
     */
    @Select("select * from wish order by id asc limit #{start},4")
    List<Wish> getWishByLimit(int start);

    /**
     * 更新日记
     * @param content
     * @param time
     * @param id
     * @return
     */
    @Update("update wish set content=#{content},time=#{time} where id=#{id}")
    int updateWish(String content,String time,int id);

    /**
     * 删除日记
     * @param id
     * @return
     */
    @Delete("delete from wish where id=#{id}")
    int deleteWish(int id);
}