package com.mman.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mman.entity.Product;
import com.mman.entity.User;
import com.mman.exception.MallException;
import com.mman.result.ResponseEnum;
import com.mman.service.ProductCategoryService;
import com.mman.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(@PathVariable("type") Integer type,
                             @PathVariable("id") Integer productCategoryId,
                             HttpSession session) {
        if (type == null || productCategoryId == null) {
            log.info("【商品列表】参数为空");
            throw new MallException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList", this.productService.findAllByTypeAndProductCategoryId(type, productCategoryId));
        // 判断是否为登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //未登录
            modelAndView.addObject("cartList", new ArrayList<>());
        } else {
            // 登录用户
            // 查询该用户的购物车记录
        }
        // 商品分类
        modelAndView.addObject("list", this.productCategoryService.buildProductCategoryMenu());
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(String keyWord, HttpSession session) {
        if (keyWord == null) {
            log.info("【商品列表】参数为空");
            throw new MallException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        // 模糊查询数据
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyWord);
        modelAndView.addObject("productList", this.productService.list(queryWrapper));
        // 判断是否为登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //未登录
            modelAndView.addObject("cartList", new ArrayList<>());
        } else {
            // 登录用户
            // 查询该用户的购物车记录
        }
        // 商品分类
        modelAndView.addObject("list", this.productCategoryService.buildProductCategoryMenu());
        return modelAndView;
    }
}

