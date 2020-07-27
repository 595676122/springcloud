package com.zyt.springcloud.alibaba.domain;

import lombok.Data;

/**
 * @author zhj
 * @date 2020/7/24 13:46
 */
@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
