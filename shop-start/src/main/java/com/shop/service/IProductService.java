package com.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.RespBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */

public interface IProductService extends IService<Product> {
    List<Product> findRequirePro(Integer category_id);
    IPage<Product> find(Product product, Integer currentPage, Integer Size);
    RespBean update(Product product,@RequestBody MultipartFile file) throws IOException;
    RespBean delete(Product product);
    //获取分页
    IPage<Product> getPage(int current, int querrywrapper, Product product);
    //判断类型修改
    Boolean modify(Product product);
    //根据id删除
    Boolean deleteById(Integer id);
    List<Product> getPartProduct(int category_id,int limit);
    //如其名
    List<Product> getAll();

    List<Product> getData(Product product);
}
