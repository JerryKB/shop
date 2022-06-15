package com.shop.service;

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
    public List<Shopcar> findAll(HttpServletRequest httpServletRequest);
    public void delete(Shopcar shopcar);
}
