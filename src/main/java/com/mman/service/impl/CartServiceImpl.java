package com.mman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mman.entity.Cart;
import com.mman.entity.Product;
import com.mman.exception.MallException;
import com.mman.mapper.CartMapper;
import com.mman.mapper.ProductMapper;
import com.mman.result.ResponseEnum;
import com.mman.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mman.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public Boolean add(Cart cart) {

        int insert = cartMapper.insert(cart);
        if (insert != 1) {
            throw new MallException(ResponseEnum.CART_ADD_ERROR);
        }
        // 商品减库存
        Integer stock = this.productMapper.getStockById(cart.getProductId());
        if (stock == null) {
            log.info("【添加购物车】商品不存在");
            throw new MallException(ResponseEnum.PRODUCT_NOT_EXISTS);
        }
        if (stock == 0) {
            log.info("【添加购物车】库存不足");
            throw new MallException(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        Integer newStock = stock - cart.getQuantity();
        if (newStock < 0) {
            log.info("【添加购物车】库存不足");
            throw new MallException(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        this.productMapper.updateStockById(cart.getProductId(), newStock);
        return true;
    }

    @Override
    public List<CartVO> findVOListByUserId(Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = this.cartMapper.selectList(queryWrapper);
        List<CartVO> cartVOList = new ArrayList<>();
        for (Cart cart : cartList) {
            Product product = this.productMapper.selectById(cart.getProductId());
            CartVO cartVO = new CartVO();
            BeanUtils.copyProperties(product, cartVO);
            BeanUtils.copyProperties(cart, cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }
}
