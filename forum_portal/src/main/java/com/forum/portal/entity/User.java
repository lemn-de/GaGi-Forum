package com.forum.portal.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class User implements Serializable {
    private Integer id;


    private String email;


    private String username;


    // 头像
    private String Icon;

    // 个人签名
    private String signature;


    private Date initTime;

    //性别 0 ：男 1：女
    private Integer sex = 0;

    //是否被封禁,默认为１：开启
    private Integer enable = 1;
}
