package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.pojo.AttributeValue;
import com.shop.mapper.AttributeValueMapper;
import com.shop.service.IAttributeValueService;
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
public class AttributeValueServiceImpl extends ServiceImpl<AttributeValueMapper, AttributeValue> implements IAttributeValueService {
    @Autowired
    AttributeValueMapper attributeValueMapper;

    public List<AttributeValue> getValue(Integer id){
        return attributeValueMapper.selectList(new QueryWrapper<AttributeValue>().eq("attribute_id",id));
    }
}
