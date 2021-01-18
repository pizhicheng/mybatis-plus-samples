package com.baomidou.mybatisplus.samples.pagination;

import com.baomidou.mybatisplus.samples.pagination.config.MybatisPlusConfig;
import com.baomidou.mybatisplus.samples.pagination.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaginationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaginationApplication.class, args);
        IUserService userService = context.getBean(IUserService.class);
        MybatisPlusConfig.repository.set(1L);

        //不通过分页器查询的不报错
        System.out.println(userService.selectAllWithoutPage(0,10));
        //通过分页器查询的，动态表名不生效
        System.out.println(userService.selectAllWithPage(0,10));
    }

}
