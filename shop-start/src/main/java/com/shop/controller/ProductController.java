package com.shop.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.R;
import com.shop.pojo.Product;
import com.shop.pojo.RespBean;
import com.shop.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/findRequirePro/{category_id}")
    public List<Product> findRequirePro(@PathVariable Integer category_id,HttpServletRequest request){
        return productService.findRequirePro(category_id);
    }
    @GetMapping("/getPartProduct/{category_id}/{limit}")
    public List<Product> getPart(@PathVariable int category_id,@PathVariable int limit){
        return productService.getPartProduct(category_id,limit);
    }


    @GetMapping("/update")
    public RespBean update(@RequestBody Product product,@RequestBody MultipartFile file) throws IOException {
        return productService.update(product,file);
    }

    @GetMapping("/delete")
    public RespBean delete(Product product,HttpServletRequest request){
        return productService.delete(product);
    }
    //获取分页信息
    @GetMapping("/{current}/{querrywrapper}")
    public R getPage(@PathVariable int current, @PathVariable
            int querrywrapper, Product product){
        IPage<Product> productIPage=productService.getPage(current,querrywrapper,product);
        if(current > productIPage.getPages()){
            productIPage=productService.getPage((int) productIPage.getPages(),querrywrapper,product);
        }
        return new R(true,productIPage);
    }
    //通过id获取数据
    @GetMapping("/getbyid/{id}")
    public R getByid(@PathVariable Integer id){
        Product productid=productService.getById(id);
        return new R(true, productid);
    }
    //传对象修改数据
    @PutMapping("/updateall")
    public R update(@RequestBody Product product){
        return new R(productService.modify(product));
    }
    //通过id删除数据
    @DeleteMapping("/deletebyid/{id}")
    public R delete(@PathVariable Integer id){
        return new R(productService.deleteById(id));
    }
    //新增数据{有bug}
    @PostMapping("/add")
    public R save(Product product){
        boolean flag = productService.save(product);
        return new R(flag,flag?"添加成功！":"添加失败！");
    }
    //Sku获取列表
    @GetMapping("/skugetall")
    public List<Product> skuget(){
        List<Product> all = productService.getAll();
        System.out.println(all);
        return all;
    }
    //通过name来getlist
    @GetMapping("/getbyname/{name}")
    public List<Product> getbyname(@PathVariable String name){
        Product product = new Product();
        product.setName(name);
        List<Product> data = productService.getData(product);
//        System.out.println(data);
        return data;
    }
}
