package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.pojo.Product;
import com.shop.mapper.ProductMapper;
import com.shop.pojo.RespBean;
import com.shop.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    ProductMapper productMapper;
    public List<Product> findRequirePro(Integer category_id) {
        return productMapper.selectList(new QueryWrapper<Product>().eq("category_id",category_id).eq("status",1));
    }

    @Override
    public IPage<Product> find(Product product, Integer currentPage, Integer Size) {
        int current = 1,size=10;
        if (currentPage!=null)
            current = currentPage;
        if (Size!=null)
            size=Size;
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",product.getName());
        IPage<Product> productPage = productMapper.selectPage(new Page<>(current,size),queryWrapper);
        return productPage;
    }

    @Override
    public RespBean update(Product product,@RequestBody MultipartFile file) throws IOException {
        String oldName=file.getOriginalFilename();
        //指定上传路径
        String path="F:\\upload_files\\";
        //拼接成为新文件的路径
        String filePath=path+oldName;
        //创建新文件对象 指定文件路径为拼接好的路径
        File newFile=new File(filePath);
        //将前端传递过来的文件输送给新文件 这里需要抛出IO异常 throws IOException
        file.transferTo(newFile);
        product.setImg_src(path);
        int update = productMapper.update(product,new UpdateWrapper<Product>().eq("id",product.getId()));
        return update>0 ?  RespBean.success("更新成功"): RespBean.error("更新失败");
    }

    @Override
    public RespBean delete(Product product) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        int delete = productMapper.deleteByMap(map);
        return delete>0 ?  RespBean.success("删除成功"): RespBean.error("删除失败");
    }
    //分页
    @Override
    public IPage<Product> getPage(int current, int querrywrapper, Product product) {
        //条件查询构造器
        LambdaQueryWrapper<Product> lmd = new LambdaQueryWrapper<Product>();
        //lamda语句，like为条件匹配
        if (product.getName().equals("undefined"))
            product.setName("");
        lmd.like(Strings.isNotEmpty(product.getName()), Product::getName, product.getName());
        IPage<Product> page = new Page<Product>(current, querrywrapper);
        IPage<Product> productIPage = productMapper.selectPage(page, lmd);
        return productIPage;
    }

    //修改
    @Override
    public Boolean modify(Product product) {
        return productMapper.updateById(product) > 0;

    }

    //通过id删除
    @Override
    public Boolean deleteById(Integer id) {
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public List<Product> getPartProduct(int category_id,int limit) {
        return productMapper.getPartProduct(category_id,limit);
    }

    @Override
    public List<Product> getAll() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        return  productMapper.selectList(queryWrapper);
    }
    //lamda判断name get List
    public List<Product> getData(Product product){
        LambdaQueryWrapper<Product> lmd= new LambdaQueryWrapper<Product>();
            lmd.like(Strings.isNotEmpty(product.getName()), Product::getName, product.getName());
           return productMapper.selectList(lmd);
    }
}
