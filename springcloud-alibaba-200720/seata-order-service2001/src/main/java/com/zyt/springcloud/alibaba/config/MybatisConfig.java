package com.zyt.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhj
 * @date 2020/7/22 17:14
 */
@Configuration
@MapperScan({"com.zyt.springcloud.alibaba.dao"})
public class MybatisConfig {
}
