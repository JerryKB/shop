package com.shop.controller;

import com.shop.pojo.RespBean;
import com.shop.pojo.TbUser;
import com.shop.pojo.UserLogin;
import com.shop.service.impl.TbUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
@RestController
public class LoginController {
    @Autowired
    private TbUserServiceImpl tbUserService;

    @PostMapping("/login")
    public RespBean login(UserLogin userLogin, HttpServletRequest request) {
        return tbUserService.login(userLogin.getUsername(), userLogin.getPassword(),request);
    }

    @GetMapping("/user/info")
    public TbUser getUserInfo(Principal principal) {
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        TbUser user = tbUserService.getUserByUserName(username);
        user.setPassword(null);
        return user;

    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
    @PostMapping("/registry")
    public RespBean registry(UserLogin userLogin){
        return tbUserService.registry(userLogin);

    }

}
