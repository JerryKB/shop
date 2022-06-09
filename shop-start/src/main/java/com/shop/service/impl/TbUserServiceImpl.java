package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.config.JWTTokenUtil;
import com.shop.mapper.TbUserMapper;
import com.shop.pojo.RespBean;
import com.shop.pojo.TbUser;
import com.shop.pojo.UserLogin;
import com.shop.service.ITbUserService;
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
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2022-06-09
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("帐户被禁用");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }

    @Override
    public TbUser getUserByUserName(String username) {
        TbUser username1 = tbUserMapper.selectOne(new QueryWrapper<TbUser>().eq("username", username));
        System.out.println(username1);
        return username1;
    }

    @Override
    public RespBean registry(UserLogin userLogin) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(userLogin.getUsername());

        tbUser.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        int insert = tbUserMapper.insert(tbUser);
        return insert>0?RespBean.success("注册成功"):RespBean.error("注册失败");
    }


}
