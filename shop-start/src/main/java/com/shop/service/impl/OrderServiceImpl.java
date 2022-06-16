package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.controller.AdminController;
import com.shop.controller.OrderController;
import com.shop.controller.UserController;
import com.shop.pojo.*;
import com.shop.mapper.OrderMapper;
import com.shop.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
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

    //查询当前用户订单
    @Override
    public IPage<Order> findOrder(Order order, Integer Current, Integer Size, HttpServletRequest httpServletRequest) {
        int current = 1,size=10;
        if (Current!=null)
            current = Current;
        if (Size!=null)
            size=Size;
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>();
        queryWrapper.and(i->i.like("order_code",order.getOrder_code()).like("order_reciever",order.getOrder_reciever()));
        IPage<Order> orders = orderMapper.selectPage(new Page<>(current,size),queryWrapper);
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

    public RespBean addOrder(Order order){
        String uuid = UUID.randomUUID().toString();
        order.setOrder_code(uuid);
        order.setOrder_statu(1);
        order.setOrder_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        int add = orderMapper.insert(order);
        return add>0 ?  RespBean.success("创建订单成功"): RespBean.error("创建订单失败");
    }

    @Override
    public RespBean update(Order order) {
        int update = orderMapper.update(order,new UpdateWrapper<Order>().eq("order_code",order.getOrder_code()));
        return update>0 ?  RespBean.success("更新成功"): RespBean.error("更新失败");
    }

    @Override
    public RespBean delete(Order order) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_code", order.getOrder_code());
        int delete = orderMapper.deleteByMap(map);
        return delete>0 ?  RespBean.success("删除成功"): RespBean.error("删除失败");
    }
    //分页
    @Override
    public IPage<Order> getPage(int current, int querrywrapper, Order order) {
        //条件查询构造器
        LambdaQueryWrapper<Order> lmd =new LambdaQueryWrapper<Order>();
        //lamda语句，like为条件匹配
        lmd.like(Strings.isNotEmpty(order.getOrder_code()),Order::getOrder_code,order.getOrder_code());
        lmd.like(Strings.isNotEmpty(order.getOrder_reciever()),Order::getOrder_reciever,order.getOrder_reciever());
        IPage<Order> page = new Page<Order>(current,querrywrapper);
        orderMapper.selectPage(page,lmd);
        return page;
    }
    //修改
    @Override
    public Boolean modify(Order order) {
        return orderMapper.updateById(order) > 0;

    }
    //    通过id删除
    @Override
    public Boolean deleteById(Integer id) {
        return orderMapper.deleteById(id)>0;
    }

}
