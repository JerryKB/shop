package com.shop.service;

import com.shop.pojo.Admin;
import com.shop.pojo.RespBean;
import com.shop.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
public interface IUserService extends IService<User> {
    RespBean login(String username, String password, HttpServletRequest request);

    User getUserByUserName(String username);

    RespBean registry(User user,String code,HttpServletRequest httpServletRequest);

    RespBean forgetPwd(User user, String code, HttpServletRequest httpServletRequest);

}
