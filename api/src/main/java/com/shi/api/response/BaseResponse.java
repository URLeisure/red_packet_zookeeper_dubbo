package com.shi.api.response;

import com.shi.api.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 16:01
 **/
@Data
@AllArgsConstructor
@NotNull
@ToString
public class BaseResponse<T> {
    //状态码
    private Integer code;
    //描述信息
    private String msg;
    //响应数据
    private T data;

    //重载构造方法
    public BaseResponse(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
