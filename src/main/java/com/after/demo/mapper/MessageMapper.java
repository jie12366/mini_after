package com.after.demo.mapper;

import com.after.demo.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/11 7:53
 */
@Mapper
public interface MessageMapper {

    /**
     * 增加评论
     * @param wishId
     * @param nickName
     * @param avatarUrl
     * @param mess
     * @param date
     * @return
     */
    @Insert("insert into message(wishId,nickName,avatarUrl,mess,date) values(#{wishId},#{nickName},#{avatarUrl},#{mess},#{date})")
    int saveMessage(int wishId, String nickName, String avatarUrl, String mess, String date);

    /**
     * 根据文章id获取评论
     * @param wishId
     * @return
     */
    @Select("select * from message where wishId = #{wishId} order by date desc")
    List<Message> listMessage(int wishId);

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @Delete("delete from message where id=#{id}")
    int deleteMessage(int id);
}