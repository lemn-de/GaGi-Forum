package com.forum.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.forum.common.utils.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子实体类
 */
@TableName(value = "forum_posts")
@Data
@ToString
public class Posts implements Serializable {
    @TableId
    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    @TableField(value = "label_id")
    private Label label;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "init_time")
    @JsonFormat(pattern = Constants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date initTime;

    private boolean top;

    private boolean good;

    @TableField(value = "user_id")
    private User user;

    @TableField(value = "reply_count")
    private int replyCount = 0;

}
