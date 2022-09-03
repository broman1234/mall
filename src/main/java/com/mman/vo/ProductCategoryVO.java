package com.mman.vo;

import com.mman.entity.Product;
import com.mman.entity.ProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {

    private Integer id;
    private String name;
    private Integer parentId;
    private List<ProductCategoryVO> children;
    private List<Product> productList;

    public ProductCategoryVO(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.parentId = productCategory.getParentId();
    }
}
