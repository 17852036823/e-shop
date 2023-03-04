package com.eshop.service;

import com.eshop.dto.req.QueryWalletDetailsByUserIdReq;
import com.eshop.dto.resp.QueryByPageResp;
import com.eshop.dto.resp.WalletDetail;
import com.eshop.pojo.WalletDetails;

import java.util.List;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
public interface WalletDetailsService {

   public QueryByPageResp<List<WalletDetail>> selectByUserId(QueryWalletDetailsByUserIdReq req);

   public void insertWalletDetails(WalletDetails walletDetails);
}
