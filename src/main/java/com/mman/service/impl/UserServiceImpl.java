package com.mman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mman.entity.User;
import com.mman.exception.MallException;
import com.mman.form.UserRegisterForm;
import com.mman.mapper.UserMapper;
import com.mman.result.ResponseEnum;
import com.mman.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mman.utils.RegexValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(UserRegisterForm userRegisterForm) {
        // 用户名是否可用
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userRegisterForm.getLoginName());
        User one = this.userMapper.selectOne(queryWrapper);
        if (one != null) {
            log.info("【用户注册】用户名已存在");
            throw new MallException(ResponseEnum.USERNAME_EXISTS);
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
