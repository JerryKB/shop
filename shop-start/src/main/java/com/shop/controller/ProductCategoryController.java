package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.Product;
import com.shop.pojo.ProductCategory;
import com.shop.pojo.ProductCategoryResult;
import com.shop.pojo.RespBean;
import com.shop.service.IProductCategoryService;
import com.shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
@RequestMapping("/product-category")
public class ProductCategoryController {
    @Autowired
    IProductCategoryService productCategoryService;

    @GetMapping("/getInfo")
    public List<ProductCategory> showAllCategory(HttpServletRequest httpServletRequest){
        return productCategoryService.showAllCategory(httpServletRequest);
    }

    @GetMapping("/find")
    public IPage<ProductCategory> find(ProductCategory productCategory, Integer currentPage, Integer Size, HttpServletRequest httpServletRequest){
        return productCategoryService.find(productCategory,currentPage,Size);
    }

    @GetMapping("/isEnable")
    public RespBean isEnable(ProductCategory productCategory){
        return productCategoryService.isEnable(productCategory);
    }

    @GetMapping("update")
    public RespBean update(ProductCategory productCategory) {
        return productCategoryService.update(productCategory);
    }

    @GetMapping("/delete")
    public RespBean delete(ProductCategory productCategory){
        return productCategoryService.delete(productCategory);
    }
}
