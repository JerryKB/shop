package com.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.ProductSpecs;
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

public interface IProductSpecsService extends IService<ProductSpecs> {
    List<ProductSpecs> findAllInfo();
    ProductSpecs findById(Integer id);
    //判断类型修改
    Boolean modify(ProductSpecs productSpecs);
    //根据id删除
    Boolean deleteById(Integer id);
    //
    List<ProductSpecs> getAll();
}
