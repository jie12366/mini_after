package com.after.demo;

import com.after.demo.config.AipNlpConfig;
import com.after.demo.service.impl.DiaryServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    AipNlpConfig aipNlpConfig;

    @Autowired
    DiaryServiceImpl diaryService;

    @Test
    public void contextLoads() throws JSONException {
        String text = "今天和朋友出去玩了，吃了好多好吃的";
        JSONObject res = aipNlpConfig.sentimentClassify(text);
        System.out.println(res);
        System.out.println(res.getJSONArray("items").getJSONObject(0).getDouble("positive_prob"));
        diaryService.saveDiary("杰","sss","测试","我今天和朋友去看了个电影","2019-4-27");
    }
}
