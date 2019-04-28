package com.after.demo.config;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/27 16:45
 */
@Configuration
public class AipNlpConfig {

    private static final String APP_ID = "16126070";
    private static final String API_KEY = "OSaN0W1shtWfjQAOM6MUAsdU";
    private static final String SECRET_KEY = "ACc6Mzwk8ULr7Wla42GCUwghwSqO4rwZ";

    /**
     * 根据传入的文本进行情感分析
     * @param text 文本内容
     * @return 情感分析结果
     */
    public JSONObject sentimentClassify(String text){
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        //传入可选参数调用接口
        HashMap<String ,Object> options = new HashMap<>(16);

        //情感倾向分析，并返回结果
        return client.sentimentClassify(text,options);
    }
}