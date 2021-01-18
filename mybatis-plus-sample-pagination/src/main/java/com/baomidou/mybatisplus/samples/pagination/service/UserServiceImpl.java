package com.baomidou.mybatisplus.samples.pagination.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.samples.pagination.entity.User;
import com.baomidou.mybatisplus.samples.pagination.mapper.UserMapper;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2020/7/2
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> selectAllWithoutPage(int pageNumber, int size) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("id", pageNumber * size);
        userQueryWrapper.le("id", (pageNumber + 1) * size);
        return baseMapper.selectList(userQueryWrapper);
    }

    @Override
    public List<User> selectAllWithPage(int pageNumber, int size) {
        Page<User> page = new Page<>(pageNumber, size);
        OrderItem orderItem =new OrderItem();
        orderItem.setColumn("id");
        page.setOrders(Collections.singletonList(orderItem));
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("id", 0);
        return baseMapper.selectPage(page,userQueryWrapper).getRecords();
    }

}
