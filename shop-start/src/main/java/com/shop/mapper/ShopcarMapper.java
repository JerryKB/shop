package com.shop.mapper;

import com.shop.pojo.Shopcar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.pojo.UserShopCar;
import io.swagger.models.auth.In;
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
public interface ShopcarMapper extends BaseMapper<Shopcar> {
    List<UserShopCar> getAllCar(Integer id);

}
