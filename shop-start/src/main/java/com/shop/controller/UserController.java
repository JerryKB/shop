package com.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.*;
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
    public RespBean registry(@RequestBody UserBean userBean){
        return UserService.registry(userBean);
    }

    @PostMapping("/forgetPwd/{code}")
    public RespBean forgetPwd(@RequestBody UserBean userBean, @PathVariable String code, HttpServletRequest httpServletRequest){
        return UserService.forgetPwd(userBean,code,httpServletRequest);
    }
    //获取分页信息
    @GetMapping("/{current}/{querrywrapper}")
    public R getPage(@PathVariable int current, @PathVariable
            int querrywrapper, User user){
        IPage<User> userIPage=UserService.getPage(current,querrywrapper,user);
        if(current > userIPage.getPages()){
            userIPage=UserService.getPage((int) userIPage.getPages(),querrywrapper,user);
        }
        return new R(true,userIPage);
    }
    //通过id获取数据
    @GetMapping("/getbyid/{id}")
    public R getByid(@PathVariable Integer id){
        User usrid=UserService.getById(id);
        return new R(true, usrid);
    }
    //传对象修改数据
    @PutMapping
    public R update(@RequestBody User user){
        return new R(UserService.modify(user));
    }
    //通过id删除数据
    @DeleteMapping("/deletebyid/{id}")
    public R delete(@PathVariable Integer id){
        return new R(UserService.deleteById(id));
    }

}
