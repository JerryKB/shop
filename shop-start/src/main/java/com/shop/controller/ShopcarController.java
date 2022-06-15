package com.shop.controller;


import com.shop.pojo.RespBean;
import com.shop.pojo.Shopcar;
import com.shop.service.IOrderService;
import com.shop.service.IShopcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@RequestMapping("/shopcar")
public class ShopcarController {
    @Autowired
    IShopcarService shopcarService;
    @Autowired
    IOrderService orderService;
    @GetMapping("/showALl")
    public List<Shopcar> findAll(HttpServletRequest httpServletRequest){
        return shopcarService.findAll(httpServletRequest);
    }
    @GetMapping("transferOrder")
    public void transfer(Shopcar shopcar,String order_receiver,Integer order_mobile,String order_remark, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String orderCode = orderService.saveOrder(shopcar,order_receiver,order_mobile,order_remark);
        shopcarService.delete(shopcar);
        httpServletResponse.sendRedirect("/order/orderDetail?order_code="+orderCode);
    }
}
