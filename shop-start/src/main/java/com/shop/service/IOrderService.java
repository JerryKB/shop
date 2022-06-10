package com.shop.service;

import com.shop.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.Shopcar;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
public interface IOrderService extends IService<Order> {
    Order findOne(Order order, HttpServletRequest httpServletRequest);
    List<Order> findAll(Order order, HttpServletRequest httpServletRequest);
    String saveOrder(Shopcar shopcar,String order_receiver,Integer order_mobile,String order_remark);
}
