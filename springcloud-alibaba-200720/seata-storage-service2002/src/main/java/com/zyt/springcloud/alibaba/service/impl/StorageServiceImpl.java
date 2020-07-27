package com.zyt.springcloud.alibaba.service.impl;

import com.zyt.springcloud.alibaba.dao.StorageDao;
import com.zyt.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhj
 * @date 2020/7/24 13:50
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
    @Resource
    private StorageDao storageDao;
}
