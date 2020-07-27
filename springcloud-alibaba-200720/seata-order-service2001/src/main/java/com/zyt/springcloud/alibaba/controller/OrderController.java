package com.zyt.springcloud.alibaba.controller;

import com.zyt.springcloud.alibaba.domain.CommonResult;
import com.zyt.springcloud.alibaba.domain.Order;
import com.zyt.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhj
 * @date 2020/7/22 17:13
 */
@RestController
public class OrderController{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
