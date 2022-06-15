package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.config.securityConfig.JWTTokenUtil;
import com.shop.pojo.Admin;
import com.shop.mapper.AdminMapper;
import com.shop.pojo.RespBean;
import com.shop.pojo.UserLogin;
import com.shop.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("帐户被禁用");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);


        request.getSession().setAttribute("username",username);
        System.out.println(token);
        return RespBean.success("登录成功", tokenMap);
    }

    @Override
    public Admin getUserByUserName(String username) {

        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
        return admin;
    }

    @Override
    public RespBean registry(UserLogin userLogin) {
        Admin admin = new Admin();
        admin.setUsername(userLogin.getUsername());
        admin.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        int insert = adminMapper.insert(admin);
        return insert > 0 ? RespBean.success("注册成功") : RespBean.error("注册失败");
    }
}