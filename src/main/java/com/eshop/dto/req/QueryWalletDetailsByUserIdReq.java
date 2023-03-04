package com.eshop.dto.req;

import io.swagger.models.auth.In;
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
public class QueryWalletDetailsByUserIdReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Integer currentPage;

    private Integer pageSize;

    private String month;

    private String year;
}
