package com.mman.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mman.entity.User;
import com.mman.exception.MallException;
import com.mman.form.UserLoginForm;
import com.mman.form.UserRegisterForm;
import com.mman.result.ResponseEnum;
import com.mman.service.UserService;
import com.mman.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        // 非空校验
        if (bindingResult.hasErrors()) {
            log.info("【用户注册】用户信息不能为空");
            throw new MallException(ResponseEnum.USER_INFO_NULL);
        }
        User register = this.userService.register(userRegisterForm);
        if (register == null) {
            log.info("【用户注册】添加用户失败");
            throw new MallException(ResponseEnum.USER_REGISTER_ERROR);
        }
        return "redirect:/login";
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginForm userLoginForm, BindingResult bindingResult) {
        // 非空校验
        if (bindingResult.hasErrors()) {
            log.info("【用户登录】用户信息不能为空");
            throw new MallException(ResponseEnum.USER_INFO_NULL);
        }
        User login = this.userService.login(userLoginForm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }
}

