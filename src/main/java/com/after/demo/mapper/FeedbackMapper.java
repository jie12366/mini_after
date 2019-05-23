package com.after.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/23 22:01
 */
@Mapper
public interface FeedbackMapper {

    /**
     * 插入数据
     * @param userName
     * @param content
     * @param time
     * @return
     */
    @Insert("insert into feedback(userName,content,time) values(#{userName},#{content},#{time})")
    int saveFeedback(String userName,String content,String time);
}