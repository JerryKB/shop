package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.pojo.Order;
import com.shop.pojo.RespBean;
import com.shop.pojo.Shopcar;
import com.shop.mapper.ShopcarMapper;
import com.shop.pojo.UserShopCar;
import com.shop.service.IShopcarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ShopcarServiceImpl extends ServiceImpl<ShopcarMapper, Shopcar> implements IShopcarService {

    @Autowired
    ShopcarMapper shopcarMapper;
    @Override
    public IPage<Shopcar> findAll(Integer userId,Integer CurrentPage,Integer Size,HttpServletRequest httpServletRequest) {
        int current = 1,size=10;
        if (CurrentPage!=null)
            current = CurrentPage;
        if (Size!=null)
            size=Size;
        QueryWrapper<Shopcar> queryWrapper = new QueryWrapper<Shopcar>();
        IPage<Shopcar> shopcars =shopcarMapper.selectPage(new Page<>(current,size),queryWrapper.eq("user_id",userId));
        return shopcars;
    }

    @Override
    public void delete(Shopcar shopcar) {
        shopcarMapper.deleteById(shopcar.getId());
    }

    @Override
    public RespBean add(Shopcar shopcar) {
        int insert = shopcarMapper.insert(shopcar);
        return insert>0?RespBean.success("添加成功"):RespBean.error("添加失败");
    }

    @Override
    public List<UserShopCar> getAll(Integer id) {
        return shopcarMapper.getAllCar(id);
    }

}
