package com.shop.controller;


import com.shop.pojo.Admin;
import com.shop.pojo.RespBean;
import com.shop.pojo.UserLogin;
import com.shop.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        System.out.println(userLogin);
        return adminService.login(userLogin.getUsername(), userLogin.getPassword(),request);
    }
    @GetMapping("/getInfo")
    public Admin getUserInfo(Principal principal) {
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getUserByUserName(username);
        admin.setPassword(null);
        return admin;

    }

    @PostMapping("/registry")
    public RespBean registry(UserLogin userLogin){
        return adminService.registry(userLogin);
    }
}
