package com.shi.api.enums;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 16:05
 **/
@AllArgsConstructor
@NotNull
public enum StatusCode {
    Success(0,"成功"),
    Fail(-1,"失败！"),
    InvalidParams(-1,"非法的参数"),
    InvalidGrantType(202,"非法的授权类型");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
