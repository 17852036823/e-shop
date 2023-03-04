package com.eshop.service;

import com.eshop.dto.req.UpdateMoneyReq;
import com.eshop.pojo.UserInfo;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
public interface UserInfoService {

   /**
    * 获取用户信息（钱包余额）
    * @param userId
    * @return
    */
   public UserInfo selectUserInfoById(String userId);


   /**
    *用户消费
    * @param req
    */
   public void consumption(UpdateMoneyReq req);

   /**
    * 用户退款
    * @param req
    */
   public void drawback(UpdateMoneyReq req);
}
