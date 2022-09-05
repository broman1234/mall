package com.mman.service;

import com.mman.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mman.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
public interface CartService extends IService<Cart> {
    public Boolean add(Cart cart);
    public List<CartVO> findVOListByUserId(Integer userId);
}
