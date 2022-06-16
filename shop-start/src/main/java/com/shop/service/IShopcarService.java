package com.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.RespBean;
import com.shop.pojo.Shopcar;
import com.baomidou.mybatisplus.extension.service.IService;

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

public interface IShopcarService extends IService<Shopcar> {
    public IPage<Shopcar> findAll(Integer userId,Integer CurrentPage, Integer Size, HttpServletRequest httpServletRequest);
    public void delete(Shopcar shopcar);
    RespBean add(Shopcar shopcar);
    List<Shopcar> getAll(Integer id);
}
