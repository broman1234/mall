package com.mman.controller;


import com.mman.entity.User;
import com.mman.exception.MallException;
import com.mman.form.UserRegisterForm;
import com.mman.result.ResponseEnum;
import com.mman.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    public String register(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        // 非空校验
        if (bindingResult.hasErrors()) {
            log.info("【用户注册】用户信息不能为空");
            throw new MallException(ResponseEnum.USER_INFO_NULL);
        }
        // 邮箱格式校验
        if (!RegexValidateUtil.checkEmail(userRegisterForm.getEmail())) {
            log.info("【用户注册】邮箱格式错误");
            throw new MallException(ResponseEnum.EMAIL_ERROR);
        }
        // 手机格式校验
        if (!RegexValidateUtil.checkTelephone(userRegisterForm.getMobile())) {
            log.info("【用户注册】手机格式错误");
            throw new MallException(ResponseEnum.MOBILE_ERROR);
        }
        return null;
    }

}

