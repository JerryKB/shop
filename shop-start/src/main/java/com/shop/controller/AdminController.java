package com.shop.controller;


import com.shop.pojo.Admin;
import com.shop.pojo.RespBean;
import com.shop.pojo.UserLogin;
import com.shop.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JerryKB
 * @since 2022-06-10
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;
    @PostMapping("/login")
    public RespBean login(UserLogin userLogin, HttpServletRequest request) {
        return adminService.login(userLogin.getUsername(), userLogin.getPassword(),request);
    }
    @GetMapping("/getInfo")
    public Admin getUserInfo(Principal principal) {
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getUserByUserName(username);
        admin.setAdmin_password(null);
        return admin;

    }

    @PostMapping("/registry")
    public RespBean registry(UserLogin userLogin){
        return adminService.registry(userLogin);

    }

}
