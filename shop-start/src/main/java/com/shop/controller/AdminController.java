package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.Admin;
import com.shop.pojo.RespBean;
import com.shop.pojo.User;
import com.shop.pojo.UserLogin;
import com.shop.service.IAdminService;
import com.shop.service.IUserService;
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
    @Autowired
    IUserService userService;
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
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
    public RespBean registry(@RequestBody UserLogin userLogin){
        return adminService.registry(userLogin);
    }

    @GetMapping("/addUser")
    public RespBean addUser(@RequestBody User user){
        return adminService.addUser(user);
    }

    @GetMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user){
        return adminService.updateUser(user);
    }
    @GetMapping("/deleteUser")
    public RespBean deleteUser(@RequestBody User user){
        return adminService.deleteUser(user);
    }
    @GetMapping("/findUser")
    public IPage<User> findUser(@RequestBody User user, Integer Current, Integer Size, HttpServletRequest httpServletRequest){
        return adminService.findUser(user,Current,Size);
    }
}
