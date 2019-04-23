package com.after.demo.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/11 7:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Column(name = "id", type = MySqlTypeConstant.INT, isKey = true, isAutoIncrement = true, length = 5)
    private int id;

    @Column(name = "articleId", type = MySqlTypeConstant.INT, length = 5)
    private String articleId;

    @Column(name = "nickName", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String nickName;

    @Column(name = "avatarUrl",type = MySqlTypeConstant.VARCHAR)
    private String avatarUrl;

    @Column(name = "mess", type = MySqlTypeConstant.TEXT)
    private String  mess;

    @Column(name = "date",type = MySqlTypeConstant.VARCHAR)
    private String date;
}