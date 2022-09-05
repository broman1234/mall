package com.mman.mapper;

import com.mman.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Repository
public interface CartMapper extends BaseMapper<Cart> {
    public int update(Integer id, Integer quantity, Float cost);
}
