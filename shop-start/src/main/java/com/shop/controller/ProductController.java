package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.pojo.Product;
import com.shop.pojo.RespBean;
import com.shop.service.impl.ProductServiceImpl;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @GetMapping("/findRequirePro")
    public List<Product> findRequirePro(Integer category_id,HttpServletRequest request){
        return productService.findRequirePro(category_id);
    }

    @GetMapping("/find")
    public IPage<Product> find(Product product, Integer currentPage, Integer Size, HttpServletRequest httpServletRequest){
        return productService.find(product,currentPage,Size);
    }

    @GetMapping("update")
    public RespBean update(Product product,@RequestBody MultipartFile file) throws IOException {
        return productService.update(product,file);
    }

    @GetMapping("/delete")
    public RespBean delete(Product product,HttpServletRequest request){
        return productService.delete(product);
    }
}
