package com.baomidou.mybatisplus.samples.pagination.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@Slf4j
public class MybatisPlusConfig {

    public static ThreadLocal<Long> repository = new ThreadLocal<>();

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.H2);

        /*动态表名*/
        DynamicTableNameInnerInterceptor dynamicTableNameParser = new DynamicTableNameInnerInterceptor();
        Map<String, TableNameHandler> tableNameHandlerMap = new HashMap<>();
        //user是数据库表名
        tableNameHandlerMap.put("user", (sql, tableName) -> {
            log.info("table name: {}. sql: {}. repository:{}", tableName, sql, repository.get());
            return "user" + (repository.get() == null ? "" : repository.get());
        });
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);

        //TODO: 交换添加顺序之后不报错
        interceptor.addInnerInterceptor(paginationInterceptor);
        interceptor.addInnerInterceptor(dynamicTableNameParser);

        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
