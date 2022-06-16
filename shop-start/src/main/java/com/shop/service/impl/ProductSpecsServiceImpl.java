package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.ProductSpecs;
import com.shop.mapper.ProductSpecsMapper;
import com.shop.service.IProductSpecsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
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

    //修改
    @Override
    public Boolean modify(ProductSpecs productSpecs) {
        return productSpecsMapper.updateById(productSpecs) > 0;

    }
    //    通过id删除
    @Override
    public Boolean deleteById(Integer id) {
        return productSpecsMapper.deleteById(id)>0;
    }

    @Override
    public List<ProductSpecs> getAll() {
        return productSpecsMapper.selectList(null);
    }

    @Override
    public ProductSpecs findById(Integer id) {
        return productSpecsMapper.selectById(id);
    }
}
