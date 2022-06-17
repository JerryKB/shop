package com.shop.mapper;

import com.shop.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.pojo.OrderBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    List<OrderBean> getOrder(Integer id);

}
