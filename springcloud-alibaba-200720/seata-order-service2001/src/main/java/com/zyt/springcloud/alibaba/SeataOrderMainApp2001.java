package com.zyt.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhj
 * @date 2020/7/22 17:16
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude ={ DataSourceAutoConfiguration.class})//取消数据源自动创建的配置
public class SeataOrderMainApp2001{

    public static void main(String[] args)
    {
        SpringApplication.run(SeataOrderMainApp2001.class, args);
    }
}
