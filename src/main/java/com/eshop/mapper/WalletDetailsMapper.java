package com.eshop.mapper;

import com.eshop.dto.req.QueryWalletDetailsByUserIdReq;
import com.eshop.dto.resp.WalletDetailsResp;
import com.eshop.pojo.WalletDetails;

import java.util.List;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
public interface WalletDetailsMapper {

   /**
    * 根据用户Id获取钱包金额变动明细
    * @param req
    * @return
    */
   public List<WalletDetailsResp> selectByUserId(QueryWalletDetailsByUserIdReq req);

   /**
    * 新增钱包明细
    * @param walletDetails
    */
   public void insertWalletDetails(WalletDetails walletDetails);
}
