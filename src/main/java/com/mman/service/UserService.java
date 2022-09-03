package com.mman.service;

import com.mman.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mman.form.UserRegisterForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
public interface UserService extends IService<User> {
    public User register(UserRegisterForm userRegisterForm);
}
