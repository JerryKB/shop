package com.shop.service;

import com.shop.pojo.ProductDetailImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */

public interface IProductDetailImgService extends IService<ProductDetailImg> {
    public List<ProductDetailImg> findImgById(Integer id);
}
