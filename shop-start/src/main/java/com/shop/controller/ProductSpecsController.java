package com.shop.controller;


import com.shop.pojo.ProductSpecs;
import com.shop.service.IProductSpecsService;
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
@RequestMapping("/product-specs")
public class ProductSpecsController {
    @Autowired
    IProductSpecsService productSpecsService;

    @GetMapping("/showAllInfo")
    public List<ProductSpecs> findAllInfo(HttpServletRequest httpServletRequest){
        return productSpecsService.findAllInfo();
    }
}
