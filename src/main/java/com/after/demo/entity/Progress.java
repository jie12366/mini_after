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
 * @date 2019/4/28 11:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progress")
public class Progress {

    @Column(name = "id", type = MySqlTypeConstant.INT,isKey = true,isAutoIncrement = true,length = 5)
    private int id;

    @Column(name = "userName", type = MySqlTypeConstant.VARCHAR,length = 20)
    private String userName;

    @Column(name = "value", type = MySqlTypeConstant.VARCHAR,length = 10)
    private String value;

    @Column(name = "date",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String date;
}