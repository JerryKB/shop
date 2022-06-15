package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.pojo.Order;
import com.shop.pojo.Shopcar;
import com.shop.mapper.ShopcarMapper;
import com.shop.service.IShopcarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@Service
public class ShopcarServiceImpl extends ServiceImpl<ShopcarMapper, Shopcar> implements IShopcarService {

    @Autowired
    ShopcarMapper shopcarMapper;
    @Override
    public List<Shopcar> findAll(HttpServletRequest httpServletRequest) {
        QueryWrapper<Shopcar> queryWrapper = new QueryWrapper<Shopcar>();
        List<Shopcar> shopcars =shopcarMapper.selectList(queryWrapper.eq("user_id",httpServletRequest.getAttribute("username")));
        return shopcars;
    }

    @Override
    public void delete(Shopcar shopcar) {
        shopcarMapper.deleteById(shopcar.getId());
    }
}
