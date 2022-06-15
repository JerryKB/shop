package com.shop.service;

import com.shop.pojo.AttributeKey;
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

public interface IAttributeKeyService extends IService<AttributeKey> {
    public List<AttributeKey> getName(Integer category_id);
}
