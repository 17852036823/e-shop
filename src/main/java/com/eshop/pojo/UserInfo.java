package com.eshop.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
@Getter
@Setter
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 支付密码
     */
    private String payPwd;

}
