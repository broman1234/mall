package com.mman.result;

public enum ResponseEnum {
    USER_INFO_NULL(300, "用户信息不能为空");

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
