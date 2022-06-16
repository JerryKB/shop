package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.R;
import com.shop.pojo.Order;
import com.shop.pojo.RespBean;
import com.shop.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    //查询当前用户指定订单
    @GetMapping("/orderDetail")
    public Order findDetail(@RequestBody Order order, HttpServletRequest httpServletRequest){
        return orderService.findOne(order,httpServletRequest);
    }
    //根据订单号更新订单
    @GetMapping("/updateOrder")
    public RespBean updateOrder(@RequestBody Order order){
        return orderService.update(order);
    }
    //根据订单号删除订单
    @GetMapping("/deleteOrder")
    public RespBean deleteOrder(@RequestBody Order order){
        return orderService.delete(order);
    }

    @GetMapping("/addOrder")
    @ApiOperation("商品详情->新增订单")
    public RespBean addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }
    //查询当前用户所有订单，分页
    @GetMapping("/findOrder")
    public IPage<Order> findAllOrder(@RequestBody Order order, Integer Current, Integer Size, HttpServletRequest httpServletRequest){
        return orderService.findOrder(order,Current,Size,httpServletRequest);
    }
    //获取分页信息
    @GetMapping("/{current}/{querrywrapper}")
    public R getPage(@PathVariable int current, @PathVariable
            int querrywrapper, Order user){
        IPage<Order> orderIPage=orderService.getPage(current,querrywrapper,user);
        if(current > orderIPage.getPages()){
            orderIPage=orderService.getPage((int) orderIPage.getPages(),querrywrapper,user);
        }
        return new R(true,orderIPage);
    }
    //通过id获取数据
    @GetMapping("/getbyid/{id}")
    public R getByid(@PathVariable Integer id){
        Order orderid=orderService.getById(id);
        return new R(true, orderid);
    }

    //传对象修改数据
    @PutMapping
    public R update(@RequestBody Order order){
        return new R(orderService.modify(order));
    }
    //通过id删除数据
    @DeleteMapping("/deletebyid/{id}")
    public R delete(@PathVariable Integer id){
        return new R(orderService.deleteById(id));
    }
}
