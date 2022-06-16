package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.ProductSpecs;
import com.shop.pojo.R;
import com.shop.service.IProductSpecsService;
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
@RequestMapping("/product-specs")
public class ProductSpecsController {
    @Autowired
    IProductSpecsService productSpecsService;

    @GetMapping("/showAllInfo")
    public List<ProductSpecs> findAllInfo(HttpServletRequest httpServletRequest){
        return productSpecsService.findAllInfo();
    }
    //获取分页信息
    @GetMapping
  public  List<ProductSpecs> getlist(){
        return productSpecsService.getAll();
  }
    //通过id获取数据
    @GetMapping("/getbyid/{id}")
    public R getByid(@PathVariable Integer id){
        ProductSpecs specsid=productSpecsService.getById(id);
        return new R(true, specsid);
    }
    //传对象修改数据
    @PutMapping("/updateall")
    public R update(@RequestBody ProductSpecs productSpecs){
        return new R(productSpecsService.modify(productSpecs));
    }
    //通过id删除数据
    @DeleteMapping("/deletebyid/{id}")
    public R delete(@PathVariable Integer id){
        return new R(productSpecsService.deleteById(id));
    }
    //新增数据
    @PostMapping
    public R save(@RequestBody ProductSpecs productSpecs){
        boolean flag = productSpecsService.save(productSpecs);
        return new R(flag,flag?"添加成功！":"添加失败！");
    }

    @GetMapping("/findSpecsById")
    public ProductSpecs findById(Integer id){
        return productSpecsService.findById(id);
    }
}
