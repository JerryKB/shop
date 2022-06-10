package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.pojo.AttributeKey;
import com.shop.mapper.AttributeKeyMapper;
import com.shop.service.IAttributeKeyService;
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
public class AttributeKeyServiceImpl extends ServiceImpl<AttributeKeyMapper, AttributeKey> implements IAttributeKeyService {
    @Autowired
    AttributeKeyMapper attributeKeyMapper;

    public List<AttributeKey> getName(Integer category_id){
        return attributeKeyMapper.selectList(new QueryWrapper<AttributeKey>().eq("category_id",category_id));
    }
}
