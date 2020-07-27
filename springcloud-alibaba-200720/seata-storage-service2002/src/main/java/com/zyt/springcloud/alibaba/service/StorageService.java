package com.zyt.springcloud.alibaba.service;

/**
 * @author zhj
 * @date 2020/7/24 13:49
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
