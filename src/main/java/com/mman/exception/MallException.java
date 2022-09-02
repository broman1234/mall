package com.mman.exception;

import com.mman.result.ResponseEnum;

public class MallException extends RuntimeException {
    public MallException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
    }
}
