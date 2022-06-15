package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.config.securityConfig.JWTTokenUtil;
import com.shop.mapper.AdminMapper;
import com.shop.pojo.*;
import com.shop.mapper.UserMapper;
import com.shop.service.IUserService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getPassword());
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
    public User getUserByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public RespBean registry(User user,String code,HttpServletRequest httpServletRequest) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (code==httpServletRequest.getSession().getAttribute("checkCode"))
        {
            int insert = userMapper.insert(user);
            return insert > 0 ? RespBean.success("注册成功") : RespBean.error("注册失败");
        }
        else
            return RespBean.error("验证码错误");
    }

    @Override
    public RespBean forgetPwd(User user, String code, HttpServletRequest httpServletRequest)  {
        Map map = new HashMap<>();
        map.put("email",user.getEmail());
        User select = userMapper.selectOne(new QueryWrapper<User>().eq("email",user.getEmail()));
        if (select ==null)
            return RespBean.error("邮箱不存在，请重新输入");
        if (code==httpServletRequest.getSession().getAttribute("checkCode"))
        {
            int update = userMapper.update(user,new UpdateWrapper<User>().eq("email",user.getEmail()));
            return update>0 ?  RespBean.success("修改成功"): RespBean.error("修改失败,请更换密码");
        }
        else
            return RespBean.error("验证码错误");
    }

    @Override
    public RespBean addUser(User user) {
        return null;
    }

    @Override
    public RespBean updateUser(User user) {
        int insert = userMapper.insert(user);
        return insert>0 ?  RespBean.success("新增成功"): RespBean.error("新增失败");
    }

    @Override
    public RespBean deleteUser(User user) {
        int delete = userMapper.delete(new QueryWrapper<User>().eq("id",user.getId()));
        return delete>0 ?  RespBean.success("删除成功"): RespBean.error("删除失败");
    }

    @Override
    public IPage<User> findUser(User user, Integer Current, Integer Size) {
        int current = 1,size=10;
        if (Current!=null)
            current = Current;
        if (Size!=null)
            size=Size;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("username",user.getUsername());
        IPage<User> userIPage = userMapper.selectPage(new Page<>(current,size),queryWrapper);
        return userIPage;
    }

}

