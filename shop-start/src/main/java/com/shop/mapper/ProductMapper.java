package com.shop.mapper;

import com.shop.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> getPartProduct(int category_id,int limit);

}
