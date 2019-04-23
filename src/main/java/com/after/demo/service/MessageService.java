package com.after.demo.service;

import com.after.demo.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/11 8:01
 */
@Service
public interface MessageService {

    /**
     * 增加评论
     * @param articleId
     * @param nickName
     * @param avatarUrl
     * @param mess
     * @param date
     * @return
     */
    int saveMessage(int articleId, String nickName, String avatarUrl, String mess);

    /**
     * 根据文章id获取评论
     * @param articleId
     * @return
     */
    List<Message> listMessage(int articleId);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    Message getMessageById(int id);

    /**
     * 列出所有评论
     * @return
     */
    List<Message> listMessageAll();

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    int deleteMessage(int id);
}