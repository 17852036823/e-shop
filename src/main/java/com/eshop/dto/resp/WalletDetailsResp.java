package com.eshop.dto.resp;

import com.eshop.pojo.WalletDetails;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/4
 * @Describe
 **/
@Setter
@Getter
public class WalletDetailsResp extends WalletDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String year_month;
}
