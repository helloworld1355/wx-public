package com.qsy.public_account.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    long serialVersionUID = 1L;
    private int code;//状态码
    private String msg;//返回信息
    private Object data;//返回数据

}
