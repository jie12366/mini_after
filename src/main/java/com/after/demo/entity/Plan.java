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
 * @date 2019/4/21 13:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan")
public class Plan {

    @Column(name = "id", type = MySqlTypeConstant.INT, isKey = true, isAutoIncrement = true, length = 5)
    private int id;

    @Column(name = "userName",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String userName;

    @Column(name = "content", type = MySqlTypeConstant.TEXT)
    private String content;

    @Column(name = "time",type = MySqlTypeConstant.VARCHAR)
    private String time;
}