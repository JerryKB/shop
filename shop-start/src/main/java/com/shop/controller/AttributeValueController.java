package com.shop.controller;


import com.shop.pojo.AttributeValue;
import com.shop.service.impl.AttributeValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/attribute-value")
public class AttributeValueController {
    @Autowired
    AttributeValueServiceImpl attributeValueService;

    @GetMapping("/getValue")
    List<AttributeValue> getValue(@RequestBody Integer id, HttpServletRequest httpServletRequest){
        return attributeValueService.getValue(id);
    }
}
