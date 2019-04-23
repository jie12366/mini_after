package com.after.demo.service.impl;

import com.after.demo.entity.Message;
import com.after.demo.mapper.MessageMapper;
import com.after.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/11 8:03
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public int saveMessage(int articleId,String nickName,String avatarUrl,String mess) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return messageMapper.saveMessage(articleId,nickName,avatarUrl,mess,sdf.format(date));
    }

    @Override
    public List<Message> listMessage(int articleId) {
        return messageMapper.listMessage(articleId);
    }

    @Override
    public List<Message> listMessageAll() {
        return messageMapper.listMessageAll();
    }

    @Override
    public Message getMessageById(int id) {
        return messageMapper.getMessageById(id);
    }

    @Override
    public int deleteMessage(int id) {
        return messageMapper.deleteMessage(id);
    }
}