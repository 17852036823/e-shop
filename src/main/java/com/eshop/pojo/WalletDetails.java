package com.eshop.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
@Getter
@Setter
@ToString
public class WalletDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 钱包变动明细Id
     */
    private String wdId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 变动资金
     */
    private BigDecimal change;

    /**
     * 流动去向
     */
    private String describe;

    /**
     * 变动时间
     */
    private LocalDateTime changeTime;

}
