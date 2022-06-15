package com.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.config.securityConfig.JWTTokenUtil;
import com.shop.pojo.*;
import com.shop.mapper.UserMapper;
import com.shop.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
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
import java.util.List;
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

        System.out.println(userDetails);
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("info",userDetails);
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
    public RespBean registry(UserBean userBean) {

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if (code==httpServletRequest.getSession().getAttribute("checkCode"))
//        {
//            int insert = userMapper.insert(user);
//            return insert > 0 ? RespBean.success("注册成功") : RespBean.error("注册失败");
//        }
//        else
//            return RespBean.error("验证码错误");
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            if (user.getUsername().equals(userBean.getUsername())){
                return RespBean.error("用户名已存在");
            }
        }
        String password = (passwordEncoder.encode(userBean.getPassword()));
        User user=new User(userBean.getUsername(),password, userBean.getReal_name(), userBean.getEmail());
        int insert =userMapper.insert(user);
        return insert > 0 ? RespBean.success("注册成功") : RespBean.error("注册失败");
    }

    @Override
    public RespBean forgetPwd(UserBean userBean, String code, HttpServletRequest httpServletRequest)  {
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", userBean.getUsername()));
        if (user1.getEmail().equals(userBean.getEmail())){
            if (code.toUpperCase().equals(httpServletRequest.getSession().getAttribute("checkCode")))
            {
                user1.setPassword(passwordEncoder.encode(userBean.getPassword()));
                int update = userMapper.update(user1,new UpdateWrapper<User>().eq("email",userBean.getEmail()));
                return update>0 ?  RespBean.success("修改成功"): RespBean.error("修改失败,请更换密码");
            }
            else{
                return RespBean.error("验证码错误");
            }
        }

        if (user1 ==null)
            return RespBean.error("该用户不存在，请重新输入");
        if (!user1.getEmail().equals(userBean.getEmail())){
            return RespBean.error("该邮箱与用户名不匹配，请重新输入");
        }
        return RespBean.error("未知错误,请联系管理员");


    }



    //分页
    @Override
    public IPage<User> getPage(int current, int querrywrapper, User user) {
        //条件查询构造器
        LambdaQueryWrapper<User> lmd =new LambdaQueryWrapper<User>();
        //lamda语句，like为条件匹配
        lmd.like(Strings.isNotEmpty(user.getEmail()),User::getEmail,user.getEmail());
        lmd.like(Strings.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
        lmd.like(Strings.isNotEmpty(user.getPassword()),User::getPassword,user.getPassword());
        IPage<User> page = new Page<User>(current,querrywrapper);
        userMapper.selectPage(page,lmd);
        return page;
    }
    //修改
    @Override
    public Boolean modify(User user) {
            return userMapper.updateById(user) > 0;

    }
//    通过id删除
    @Override
    public Boolean deleteById(Integer id) {
        return userMapper.deleteById(id)>0;
    }

//    @Override
//    public RespBean addUser(User user) {
//        int insert = userMapper.insert(user);
//        return insert>0 ?  RespBean.success("新增成功"): RespBean.error("新增失败");
//    }
}

