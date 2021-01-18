package com.baomidou.mybatisplus.samples.pagination.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.samples.pagination.entity.User;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @date 2020/7/2
 */
public interface IUserService extends IService<User> {

    List<User> selectAllWithoutPage(int pageNumber, int size);

    List<User> selectAllWithPage(int pageNumber, int size);


}
