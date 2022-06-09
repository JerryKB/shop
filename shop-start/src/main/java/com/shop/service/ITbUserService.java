package com.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.pojo.RespBean;
import com.shop.pojo.TbUser;
import com.shop.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2022-06-09
 */
public interface ITbUserService extends IService<TbUser> {

    RespBean login(String username, String password, HttpServletRequest request);

    TbUser getUserByUserName(String username);


    RespBean registry(UserLogin userLogin);

}
