package com.zyt.springcloud.alibaba.controller;

import com.zyt.springcloud.alibaba.domain.CommonResult;
import com.zyt.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhj
 * @date 2020/7/24 13:51
 */
@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;
    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
