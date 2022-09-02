package com.mman.exception;

import com.mman.result.ResponseEnum;
import com.mman.result.ResponseEnum;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = MallException.class)
    public ModelAndView handlerException(MallException e) {
        return null;
    }

}
