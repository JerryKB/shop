package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.ProductSpecs;
import com.shop.mapper.ProductSpecsMapper;
import com.shop.service.IProductSpecsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProductSpecsServiceImpl extends ServiceImpl<ProductSpecsMapper, ProductSpecs> implements IProductSpecsService {

    @Autowired
    ProductSpecsMapper productSpecsMapper;
    @Override
    public List<ProductSpecs> findAllInfo() {
        return productSpecsMapper.selectList(new QueryWrapper<ProductSpecs>());
    }
}
