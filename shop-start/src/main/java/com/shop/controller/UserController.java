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
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService UserService;
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        return UserService.login(userLogin.getUsername(), userLogin.getPassword(),request);
    }
    @GetMapping("/getInfo")
    public User getUserInfo(Principal principal) {
        if (null==principal){
            return null;
        }
        String username = principal.getName();
        User user = UserService.getUserByUserName(username);
        user.setPassword(null);
        return user;

    }

    @PostMapping("/registry")
    public RespBean registry(User user,String code,HttpServletRequest httpServletRequest){
        return UserService.registry(user,code,httpServletRequest);
    }

    @PostMapping("/forgetPwd")
    public RespBean forgetPwd(User user, String code, HttpServletRequest httpServletRequest){
        return UserService.forgetPwd(user,code,httpServletRequest);
    }

    @GetMapping("/addUser")
    public RespBean addUser(@RequestBody User user){
        return UserService.addUser(user);
    }

    @GetMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user){
        return UserService.updateUser(user);
    }
    @GetMapping("/deleteUser")
    public RespBean deleteUser(@RequestBody User user){
        return UserService.deleteUser(user);
    }
    @GetMapping("/findUser")
    public IPage<User> findUser(@RequestBody User user, Integer Current, Integer Size, HttpServletRequest httpServletRequest){
        return UserService.findUser(user,Current,Size);
    }


}
