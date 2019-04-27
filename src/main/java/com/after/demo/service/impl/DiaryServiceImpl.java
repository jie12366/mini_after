package com.after.demo.service.impl;

import com.after.demo.config.AipNlpConfig;
import com.after.demo.entity.Diary;
import com.after.demo.mapper.DiaryMapper;
import com.after.demo.service.DiaryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    AipNlpConfig aipNlpConfig;

    @Override
    public int saveDiary(String userName, String photo, String title, String content,String time) {
        int sentiment = getSentiment(content);

        return diaryMapper.saveDiary(userName, photo, title, content, time, sentiment);
    }

    public int getSentiment(String text){
        //情感分析
        JSONObject res = aipNlpConfig.sentimentClassify(text);
        //获取items
        JSONArray items = res.getJSONArray("items");
        //取items的第一个元素，是个JSONObject对象
        JSONObject it1 = items.getJSONObject(0);
        //取it1的中属于积极类别的概率并 * 100
        int sentiment = (int)(it1.getDouble("positive_prob") * 100);
        return sentiment;
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
        int sentiment = getSentiment(content);

        return diaryMapper.updateDiary(photo,title, content, time, sentiment, id);
    }

    @Override
    public int deleteDiary(int id) {
        return diaryMapper.deleteDiary(id);
    }
}