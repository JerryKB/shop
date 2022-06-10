package com.shop.service.impl;

import com.shop.pojo.Admin;
import com.shop.mapper.AdminMapper;
import com.shop.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JerryKB
 * @since 2022-06-10
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
