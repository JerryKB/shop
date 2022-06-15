package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.Order;
import com.shop.pojo.RespBean;
import com.shop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Order findDetail(@RequestBody Order order, HttpServletRequest httpServletRequest){
        return orderService.findOne(order,httpServletRequest);
    }
    @GetMapping("/updateOrder")
    public RespBean updateOrder(@RequestBody Order order){
        return orderService.update(order);
    }
    @GetMapping("/deleteOrder")
    public RespBean deleteOrder(@RequestBody Order order){
        return orderService.delete(order);
    }
    @GetMapping("/findOrder")
    public IPage<Order> findAllOrder(@RequestBody Order order, Integer Current, Integer Size, HttpServletRequest httpServletRequest){
        return orderService.findOrder(order,Current,Size,httpServletRequest);
    }

}
