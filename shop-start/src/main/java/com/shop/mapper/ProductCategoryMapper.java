package com.shop.mapper;

import com.shop.pojo.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.pojo.ProductCategoryResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    public List<ProductCategory> showAll();
}
