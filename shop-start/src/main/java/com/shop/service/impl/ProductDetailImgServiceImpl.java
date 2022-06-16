package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.pojo.ProductDetailImg;
import com.shop.mapper.ProductDetailImgMapper;
import com.shop.service.IProductDetailImgService;
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
public class ProductDetailImgServiceImpl extends ServiceImpl<ProductDetailImgMapper, ProductDetailImg> implements IProductDetailImgService {
    @Autowired
    ProductDetailImgMapper productDetailImgMapper;
    public List<ProductDetailImg> findImgById(Integer id){
        return productDetailImgMapper.selectList(new QueryWrapper<ProductDetailImg>().eq("product_id",id));
    }
}
