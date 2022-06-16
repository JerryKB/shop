package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.ProductCategory;
import com.shop.pojo.R;
import com.shop.pojo.RespBean;
import com.shop.service.IProductCategoryService;
import com.shop.service.impl.ProductCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/product-category")
public class ProductCategoryController {
    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    @GetMapping("/getInfo")
    public List<ProductCategory> showAllCategory(HttpServletRequest httpServletRequest) {
        return productCategoryService.showAllCategory(httpServletRequest);
    }

    @GetMapping("/find")
    public IPage<ProductCategory> find(@RequestBody ProductCategory productCategory, Integer currentPage, Integer Size, HttpServletRequest httpServletRequest) {
        return productCategoryService.find(productCategory, currentPage, Size);
    }

    @GetMapping("/isEnable")
    public RespBean isEnable(ProductCategory productCategory) {
        return productCategoryService.isEnable(productCategory);
    }

    @GetMapping("update")
    public RespBean update(ProductCategory productCategory) {
        return productCategoryService.update(productCategory);
    }

    @GetMapping("/delete")
    public RespBean delete(ProductCategory productCategory) {
        return productCategoryService.delete(productCategory);
    }

    //获取分页信息
    @GetMapping("/{current}/{querrywrapper}")
    public R getPage(@PathVariable int current, @PathVariable
            int querrywrapper, ProductCategory productCategory) {
        IPage<ProductCategory> cateIPage = productCategoryService.getPage(current, querrywrapper, productCategory);
        if (current > cateIPage.getPages()) {
            cateIPage = productCategoryService.getPage((int) cateIPage.getPages(), querrywrapper, productCategory);
        }
        return new R(true, cateIPage);
    }
    @GetMapping("/getsecond/{id}")
    public List<ProductCategory> getlistbyid(@PathVariable int id){
        List<ProductCategory> getlistbyid = productCategoryService.getlistbyid(id);
        System.out.println(getlistbyid);
        return getlistbyid;
    }
    //获取二级分页信息
//    @GetMapping("/secondlevel/{current2}/{querrywrapper2}")
//    public R getPage2(@PathVariable int current2, @PathVariable
//            int querrywrapper2, ProductCategory productCategory) {
//        IPage<ProductCategory> cateIPage = productCategoryService.getPagelevel2(current2, querrywrapper2, productCategory);
//        if (current2 > cateIPage.getPages()) {
//            cateIPage = productCategoryService.getPagelevel2((int) cateIPage.getPages(), querrywrapper2, productCategory);
//        }
//        return new R(true, cateIPage);
//    }

    //通过id获取数据
    @GetMapping("/getbyid/{id}")
    public R getByid(@PathVariable Integer id) {
        ProductCategory cateid = productCategoryService.getById(id);
        return new R(true, cateid);
    }

    //传对象修改数据
    @PutMapping
    public R updates(@RequestBody ProductCategory category) {
        return new R(productCategoryService.modify(category));
    }

    //通过id删除数据
    @DeleteMapping("/deletebyid/{id}")
    public R delete(@PathVariable Integer id) {
        return new R(productCategoryService.deleteById(id));
    }
    @PostMapping("/add")
    public R save(ProductCategory productCategory){
        Boolean flag=productCategoryService.save(productCategory);
        return new R(flag,flag?"添加成功^_^!":"添加失败-_-|||");
    }
}
