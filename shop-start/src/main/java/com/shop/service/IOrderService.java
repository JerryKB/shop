package com.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.OrderBean;
import com.shop.pojo.RespBean;
import com.shop.pojo.Shopcar;
import org.mockito.internal.matchers.Or;
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
    IPage<Order> findOrder(Order order, Integer Current, Integer Size, HttpServletRequest httpServletRequest);
    String saveOrder(Shopcar shopcar,String order_receiver,Integer order_mobile,String order_remark);
    RespBean addOrder(Order order);
    RespBean update(Order order);
    RespBean delete(Order order);
    //获取分页
    IPage<Order> getPage(int current, int querrywrapper, Order order);
    //判断类型修改
    Boolean modify(Order order);
    //根据id删除
    Boolean deleteById(Integer id);

    List<OrderBean> getOrder(Integer id);
}
