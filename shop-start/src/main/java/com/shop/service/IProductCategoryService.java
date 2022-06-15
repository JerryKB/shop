package com.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.Product;
import com.shop.pojo.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.ProductCategoryResult;
import com.shop.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */

public interface IProductCategoryService extends IService<ProductCategory> {
    List<ProductCategoryResult> showAllCategory(HttpServletRequest httpServletRequest);
    IPage<ProductCategory> find(ProductCategory productCategory, Integer currentPage, Integer Size);
    RespBean update(ProductCategory productCategory);
    RespBean delete(ProductCategory productCategory);
    RespBean isEnable(ProductCategory productCategory);
}
