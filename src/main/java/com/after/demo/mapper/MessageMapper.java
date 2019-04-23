package com.after.demo.mapper;

import com.after.demo.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
     * @param articleId
     * @param nickName
     * @param avatarUrl
     * @param mess
     * @param date
     * @return
     */
    @Insert("insert into message(articleId,nickName,avatarUrl,mess,date) values(#{articleId},#{nickName},#{avatarUrl},#{mess},#{date})")
    int saveMessage(int articleId, String nickName, String avatarUrl, String mess, String date);

    /**
     * 根据文章id获取评论
     * @param articleId
     * @return
     */
    @Select("select * from message where articleId = #{articleId} order by date desc")
    List<Message> listMessage(int articleId);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    @Select("select picture from message where id=#{id}")
    Message getMessageById(int id);

    /**
     * 列出所有评论
     * @return
     */
    @Select("select * from message")
    List<Message> listMessageAll();

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @Delete("delete from message where id=#{id}")
    int deleteMessage(int id);
}