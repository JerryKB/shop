package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.pojo.Product;
import com.shop.pojo.ProductCategory;
import com.shop.mapper.ProductCategoryMapper;
import com.shop.pojo.ProductCategoryResult;
import com.shop.pojo.RespBean;
import com.shop.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryResult> showAllCategory(HttpServletRequest httpServletRequest) {
        return productCategoryMapper.showAll();
    }

    @Override
    public IPage<ProductCategory> find(ProductCategory productCategory, Integer currentPage, Integer Size) {
        int current = 1,size=10;
        if (currentPage!=null)
            current = currentPage;
        if (Size!=null)
            size=Size;
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name",productCategory.getName());
        IPage<ProductCategory> productCategoryIPage = productCategoryMapper.selectPage(new Page<>(current,size),queryWrapper);
        return productCategoryIPage;
    }

    @Override
    public RespBean isEnable(ProductCategory productCategory){
        int isEnable = productCategoryMapper.update(productCategory,new UpdateWrapper<ProductCategory>().eq("status",productCategory.getStatus()));
        if (isEnable>0){
            return productCategory.getStatus()==0 ? RespBean.success("禁用成功"): RespBean.success("启用成功");
        }
        return RespBean.error("请务频繁操作");
    }
    @Override
    public RespBean update(ProductCategory productCategory) {
        int update = productCategoryMapper.update(productCategory,new UpdateWrapper<ProductCategory>().eq("id",productCategory.getId()));
        return update>0 ?  RespBean.success("更新成功"): RespBean.error("更新失败");
    }

    @Override
    public RespBean delete(ProductCategory productCategory) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", productCategory.getId());
        int delete = productCategoryMapper.deleteByMap(map);
        return delete>0 ?  RespBean.success("删除成功"): RespBean.error("删除失败");
    }
}
