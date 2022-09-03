package com.mman.service.impl;

import com.mman.entity.ProductCategory;
import com.mman.mapper.ProductCategoryMapper;
import com.mman.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mman.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    /**
     * 构建商品分类菜单
     * @return
     */
    @Override
    public List<ProductCategoryVO> buildProductCategoryMenu() {
        // 1. 查询所有的商品分类数据
        List<ProductCategory> productCategoryList = this.productCategoryMapper.selectList(null);
        // 2. 数据类型转换成ProductCategoryVO
        List<ProductCategoryVO> productCategoryVOList = productCategoryList.stream().map(ProductCategoryVO::new).collect(Collectors.toList());
        // 3. 进行父子级菜单的封装
        final List<ProductCategoryVO> levelOneList = buildMenu(productCategoryVOList);
        return levelOneList;
    }

    /**
     * 构建子菜单
     * @param list
     */
    public List<ProductCategoryVO> buildMenu(List<ProductCategoryVO> list) {
        // 找到一级菜单
        List<ProductCategoryVO> levelOneList = list.stream().filter(c -> c.getParentId() == 0).collect(Collectors.toList());
        for (ProductCategoryVO vo : levelOneList) {
            recursion(list, vo);
        }
        return levelOneList;
    }

    /**
     * 递归分类
     * @param list
     * @param vo
     */
    public void recursion(List<ProductCategoryVO> list, ProductCategoryVO vo) {
        // 找到子菜单
        List<ProductCategoryVO> children = getChildrenList(list, vo);
        vo.setChildren(children);
        // 继续查找子菜单
        if (children.size() > 0) {
            for (ProductCategoryVO child : children) {
                recursion(list, child);
            }
        }
    }

    /**
     * 获取子菜单
     * @param list
     * @param vo
     */
    public List<ProductCategoryVO> getChildrenList(List<ProductCategoryVO> list, ProductCategoryVO vo) {
        List<ProductCategoryVO> children = new ArrayList<>();
        Iterator<ProductCategoryVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            ProductCategoryVO next = iterator.next();
            if (next.getParentId().equals(vo.getId())) {
                children.add(next);
            }
        }
        return children;
    }
}
