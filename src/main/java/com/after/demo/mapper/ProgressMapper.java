package com.after.demo.mapper;

import com.after.demo.entity.Progress;
import org.apache.ibatis.annotations.*;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/28 13:33
 */
@Mapper
public interface ProgressMapper {

    /**
     * 插入进度数据
     * @param userName 用户名
     * @param value 进度值
     * @param date 时间
     * @return
     */
    @Insert("insert into progress(userName,value,date) values(#{userName},#{value},#{date})")
    int saveProgress(String userName,String value,String date);

    /**
     * 根据用户名和时间查询是否存在记录
     * @param userName
     * @param date
     * @return
     */
    @Select("select * from progress where userName=#{userName} and date=#{date}")
    Progress getProgress(String userName,String date);

    /**
     * 根据id更新进度值
     * @param value
     * @param id
     * @return
     */
    @Update("update progress set value=#{value} where id=#{id}")
    int updateProgress(String value,int id);
}