package com.mman.service;

import com.mman.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
public interface CartService extends IService<Cart> {
    public Boolean add(Integer productId, Float price, Integer quantity);
}
