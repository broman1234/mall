package com.mman.service.impl;

import com.mman.entity.Cart;
import com.mman.mapper.CartMapper;
import com.mman.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Override
    public Boolean add(Integer productId, Float price, Integer quantity) {
        Cart cart = new Cart();

        return null;
    }
}
