package com.after.demo.service.impl;

import com.after.demo.entity.Diary;
import com.after.demo.mapper.DiaryMapper;
import com.after.demo.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 20:14
 */
@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryMapper diaryMapper;

    @Override
    public int saveDiary(String userName, String photo, String title, String content,String time) {
        return diaryMapper.saveDiary(userName, photo, title, content, time);
    }

    @Override
    public List<Diary> listDiary() {
        return diaryMapper.listDiary();
    }

    @Override
    public List<Diary> getDiaryByName(String  userName) {
        return diaryMapper.getDiaryByName(userName);
    }

    @Override
    public int updateDiary(String photo,String title, String content,String time, int id) {
        return diaryMapper.updateDiary(photo,title, content, time, id);
    }

    @Override
    public int deleteDiary(int id) {
        return diaryMapper.deleteDiary(id);
    }
}