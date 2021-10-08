package com.forum.common.dto;

import com.forum.common.enums.StateEnum;
import com.forum.common.entity.Label;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
public class QuarkResult implements Serializable {
    /**
     * 响应业务状态
     */
    private Integer status;

    private Object data;

    private String success;

    private String error;

    private long pageSize;

    /**
     * 本页返回数量
     */
    private long total;

    /**
     * 请求成功
     * @param status
     */
    public QuarkResult(Integer status) {this.status = status;}

    /**
     * 请求成功并携带数据
     * @param status
     * @param data
     */
    public QuarkResult(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }


    /**
     * 请求成功被携带参数与页数
     * @param status
     * @param data
     * @param pageSize
     * @param total
     */
    public QuarkResult(Integer status, Object data, long pageSize, long total) {
        this.status = status;
        this.data = data;
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * 请求返回消息
     * @param status
     * @param message
     */
    public QuarkResult(Integer status, String message) {
        this.status = status;
        if (status == 500) {
            this.error = message;
        }
        else if (status == 200) {
            this.success = message;
        }
    }

    public static QuarkResult ok() {
        return new QuarkResult(StateEnum.SUCCESS.getState());
    }
    public static QuarkResult ok(Object data) {
        return new QuarkResult(StateEnum.SUCCESS.getState(), data);
    }
    public static QuarkResult ok(String message) {
        return new QuarkResult(StateEnum.SUCCESS.getState(), message);
    }
    public static QuarkResult warn(String warn) {
        return new QuarkResult(StateEnum.WARN.getState(), warn);
    }
    public static QuarkResult error(String error) {
        return new QuarkResult(StateEnum.ERROR.getState(), error);
    }
    public static QuarkResult ok(Object data, long pageSize, long total) {
        return new QuarkResult(StateEnum.SUCCESS.getState(), data, pageSize, total);
    }
}
