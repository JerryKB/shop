package com.shop.controller;


import com.shop.pojo.AttributeKey;
import com.shop.service.impl.AttributeKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@RestController
@RequestMapping("/attribute-key")
public class AttributeKeyController {
    @Autowired
    AttributeKeyServiceImpl attributeKeyService;

    @GetMapping("/getName")
    public List<AttributeKey> getName(Integer category_id, HttpServletRequest request){
        return attributeKeyService.getName(category_id);
    }
}
