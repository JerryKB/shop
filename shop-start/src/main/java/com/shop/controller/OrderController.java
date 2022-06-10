package com.shop.controller;


import com.shop.pojo.Order;
import com.shop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @GetMapping("/orderDetail")
    public Order findDetail(Order order, HttpServletRequest httpServletRequest){
        return orderService.findOne(order,httpServletRequest);
    }
    @GetMapping("/showAllOrder")
    public List<Order> findAllOrder(Order order, HttpServletRequest httpServletRequest){
        return orderService.findAll(order,httpServletRequest);
    }

}
