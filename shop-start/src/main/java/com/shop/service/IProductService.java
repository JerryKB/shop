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
}
