package com.shop.service;

import com.shop.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.RespBean;
import com.shop.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
public interface IAdminService extends IService<Admin> {
    RespBean login(String username, String password, HttpServletRequest request);

    Admin getUserByUserName(String username);

    RespBean registry(UserLogin userLogin);
}
