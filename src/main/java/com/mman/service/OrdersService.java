package com.mman.service;

import com.mman.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mman.vo.OrdersVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
public interface OrdersService extends IService<Orders> {
    public List<OrdersVO> findAllByUserId(Integer id);

}
