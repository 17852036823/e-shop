package com.eshop.dto.resp;

import com.eshop.pojo.WalletDetails;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/4
 * @Describe
 **/
@Getter
@Setter
public class WalletDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String year_month;

    private List<WalletDetailsResp> list;

}
