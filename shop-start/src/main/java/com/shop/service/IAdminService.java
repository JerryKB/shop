package com.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.RespBean;
import com.shop.pojo.User;
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

    RespBean addUser(User user);

    RespBean updateUser(User user);

    RespBean deleteUser(User user);

    IPage<User> findUser(User user, Integer Current, Integer Size);
}
