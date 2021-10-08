package com.forum.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.forum.common.utils.Constants;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
@TableName(value = "forum_user")
@Getter
@Setter
@ToString
public class User implements Serializable {
    @TableId
    private Integer id;

    @TableField(value = "email")
    private String email;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "icon")
    private String icon ="http://127.0.0.1/images/avatar/default.png";

    //个人签名
    @TableField(value = "signature")
    private String signature;

    @TableField(value = "init_time")
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = "GMT+8")
    private Date initTime;

    @TableField(value = "sex")
    private Integer sex = 0;

    @TableField(value = "enable")
    private Integer enable = 1;
}
