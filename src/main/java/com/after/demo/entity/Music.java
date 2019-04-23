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
 * @date 2019/4/19 19:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music")
public class Music {

    @Column(name = "id",type = MySqlTypeConstant.INT,isKey = true,isAutoIncrement = true,length = 5)
    private int id;

    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,isUnique = true)
    private String name;

    @Column(name = "imgUrl",type = MySqlTypeConstant.VARCHAR,length = 80)
    private String imgUrl;

    @Column(name = "src",type = MySqlTypeConstant.VARCHAR)
    private String src;
}