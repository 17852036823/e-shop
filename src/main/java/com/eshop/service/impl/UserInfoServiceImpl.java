package com.eshop.service.impl;

import com.eshop.dto.req.UpdateMoneyReq;
import com.eshop.mapper.UserInfoMapper;
import com.eshop.pojo.UserInfo;
import com.eshop.pojo.WalletDetails;
import com.eshop.service.UserInfoService;
import com.eshop.service.WalletDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

   @Autowired
   private UserInfoMapper userInfoMapper;

   @Autowired
   private WalletDetailsService walletDetailsService;

   /**
    * 获取用户信息（钱包余额）
    * @param userId
    * @return
    */
   @Override
   public UserInfo selectUserInfoById(String userId) {
      UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
      if(ObjectUtils.isEmpty(userInfo)){
         throw new RuntimeException("该用户不存在");
      }
      return userInfo;
   }


   /**
    * 用户消费
    *
    * @param req
    */
   @Override
   public void consumption(UpdateMoneyReq req) {
      //1.根据前端传递的信息查询订单信息
      //2.判断订单是否支付，如果已经支付抛出异常信息返回前端
      //3.否则，获取订单金额
      //4.获取前端选择支付方式
      //5.如果使用钱包余额支付，获取用户余额信息
      UserInfo userInfo = this.selectUserInfoById(req.getUserId());
      if(ObjectUtils.isEmpty(userInfo)){
         throw new RuntimeException("该用户不存在");
      }
      BigDecimal change =  req.getMoney();//消费金额
      //判断用户余额是否足够支付
      if(userInfo.getMoney().compareTo(change.abs()) < 0){
         throw new RuntimeException("用户余额不足,无法完成支付");
      }
      req.setMoney(userInfo.getMoney().add(change));//账户余额减去消费金额
      userInfoMapper.updateMoneyByUserId(req);
      //插入余额变动明细
      WalletDetails details = new WalletDetails();
      details.setMoney(req.getMoney());//最新的余额
      details.setChange(change);
      details.setUserId(req.getUserId());
      details.setDescribe(req.getDescribe());
      details.setChangeTime(LocalDateTime.now());
      walletDetailsService.insertWalletDetails(details);
      //6.如果是银行卡支付，调用外部接口实现支付，不扣减钱包余额和生成钱包余额变动明细
      //7.更新订单信息
   }

   /**
    * 用户退款
    *
    * @param req
    */
   @Override
   public void drawback(UpdateMoneyReq req) {
      //1.根据前端传递的信息查询订单信息
      //2.获取订单信息
      //3.判断支付方式是银行卡支付还是钱包支付等
      //4.如果是银行卡支付，调用外部接口，将金额原路退回对应的银行卡
      //5.如果是钱包余额支付
      BigDecimal change =  req.getMoney().abs();//退款金额
      //获取用户信息
      UserInfo userInfo = this.selectUserInfoById(req.getUserId());
      if(ObjectUtils.isEmpty(userInfo)){
         throw new RuntimeException("该用户不存在");
      }
      req.setMoney(userInfo.getMoney().add(change));//更新用户钱包余额
      userInfoMapper.updateMoneyByUserId(req);
      //插入余额变动明细
      WalletDetails details = new WalletDetails();
      details.setMoney(req.getMoney());//最新的余额
      details.setChange(change);
      details.setUserId(req.getUserId());
      details.setDescribe(req.getDescribe());
      details.setChangeTime(LocalDateTime.now());
      walletDetailsService.insertWalletDetails(details);
      //6.更新订单状态
      //7.商品库存+1
   }
}
