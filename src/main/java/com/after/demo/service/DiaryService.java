package com.after.demo.service;

import com.after.demo.entity.Diary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/16 20:13
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface DiaryService {

    /**
     * 新增数据
     * @param userName
     * @param picture
     * @param title
     * @param content
     * @param time
     * @return
     */
    int saveDiary(String userName,String picture,String title,String content,String time);

    /**
     * 获取所有日记
     * @return
     */
    List<Diary> listDiary();

    /**
     * 根据id获取日记
     * @param userName
     * @return
     */
    List<Diary> getDiaryByName(String userName);

    /**
     * 更细日记
     * @param title
     * @param content
     * @param id
     * @param time
     * @param photo
     * @return
     */
    int updateDiary(String photo,String title,String content,String time,int id);

    /**
     * 删除日记
     * @param id
     * @return
     */
    int deleteDiary(int id);
}