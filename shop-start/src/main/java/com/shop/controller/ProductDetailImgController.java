package com.shop.controller;


import com.shop.pojo.ProductDetailImg;
import com.shop.service.impl.ProductDetailImgServiceImpl;
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
@RequestMapping("/product-detail-img")
public class ProductDetailImgController {
    @Autowired
    ProductDetailImgServiceImpl productDetailImgService;

    @GetMapping("")
    public List<ProductDetailImg> findImg(HttpServletRequest httpServletRequest){
        return productDetailImgService.findImg();
    }
}
