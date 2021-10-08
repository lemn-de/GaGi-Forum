package com.forum.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data   //lombok的注解用来生成get set 等相关方法
@TableName(value = "forum_label")
public class Label implements Serializable {
    @TableId
    private Integer id;

    @TableField(value = "name")
    private String name;

    //主题数量
    @TableField(value = "posts_count")
    private Integer postsConunt = 0;

    //详情
    @TableField(value = "details")
    private String details;
}
