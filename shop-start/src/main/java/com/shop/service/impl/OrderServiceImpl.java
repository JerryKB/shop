package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.controller.AdminController;
import com.shop.controller.OrderController;
import com.shop.controller.UserController;
import com.shop.pojo.Admin;
import com.shop.pojo.Order;
import com.shop.mapper.OrderMapper;
import com.shop.pojo.Shopcar;
import com.shop.pojo.User;
import com.shop.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    //查询所选订单
    @Override
    public Order findOne(Order order, HttpServletRequest httpServletRequest) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>();
        Map map = new HashMap<>();
        map.put("user_id",httpServletRequest.getAttribute("username"));
        map.put("order_code",order.getOrder_code());
        queryWrapper.allEq(map);
        Order order1 = orderMapper.selectOne(queryWrapper);
        return order1;
    }

    //查询当前用户所有订单
    @Override
    public List<Order> findAll(Order order, HttpServletRequest httpServletRequest) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>();
        List<Order> orders = orderMapper.selectList(queryWrapper.eq("user_id",httpServletRequest.getAttribute("username")));
        return orders;
    }

    @Override
    public String saveOrder(Shopcar shopcar,String order_receiver,Integer order_mobile,String order_remark) {
        Order order = new Order();
        String uuid = UUID.randomUUID().toString();
        order.setOrder_code(uuid);
        order.setOrder_reciever(order_receiver);
        order.setOrder_mobile(order_mobile);
        order.setUser_id(shopcar.getUser_id());
        order.setOrder_remark(order_remark);
        order.setProduct_price(shopcar.getProducts_price());
        order.setProduct_count(shopcar.getProduct_num());
        order.setOrder_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        order.setOrder_statu(1);
        orderMapper.insert(order);
        return uuid;
    }
}
