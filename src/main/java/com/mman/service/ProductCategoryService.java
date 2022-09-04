package com.mman.service;

import com.mman.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mman.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> buildProductCategoryMenu();
    public List<ProductCategoryVO> findAllProductByCategoryLevelOne();
}
