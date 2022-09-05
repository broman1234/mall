package com.mman.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mman.entity.Cart;
import com.mman.entity.User;
import com.mman.exception.MallException;
import com.mman.result.ResponseEnum;
import com.mman.service.CartService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车
     * @return
     */
    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ) {
        if (productId == null || price == null || quantity == null) {
            log.info("【添加购物车】参数为空");
            throw new MallException(ResponseEnum.PARAMETER_NULL);
        }
        // 判断是否为登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.info("【添加购物车】当前为未登录状态");
            throw new MallException(ResponseEnum.NOT_LOGIN);
        }
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price * quantity);
        Boolean add = this.cartService.add(cart);
        if (!add) {
            log.info("【添加购物车】添加失败");
            throw new MallException(ResponseEnum.CART_ADD_ERROR);
        }

        return "redirect:/cart/get";
    }

    @GetMapping("/get")
    public ModelAndView get(HttpSession session) {
        // 判断是否为登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.info("【添加购物车】当前为未登录状态");
            throw new MallException(ResponseEnum.NOT_LOGIN);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        modelAndView.addObject("cartList", this.cartService.findVOListByUserId(user.getId()));
        return modelAndView;
    }
}

