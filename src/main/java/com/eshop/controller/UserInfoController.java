package com.eshop.controller;

import com.eshop.common.ResponseCodeConstant;
import com.eshop.common.ResponseMessage;
import com.eshop.dto.req.UpdateMoneyReq;
import com.eshop.pojo.UserInfo;
import com.eshop.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
@Controller
@RequestMapping("/userInfo")
@Api(tags = "用户信息")
@Slf4j
public class UserInfoController {

   @Autowired
   private UserInfoService userInfoService;

   @GetMapping("/queryByUserId")
   @ApiOperation("查询用户钱包余额")
   @ApiImplicitParam(name = "userId",value = "用户Id",required = true)
   public ResponseEntity<ResponseMessage<UserInfo>> getUserInfo(String userId){
      ResponseMessage<UserInfo> result = new ResponseMessage<>();
      try {
         UserInfo userInfo = userInfoService.selectUserInfoById(userId);
         result.setData(userInfo);
         result.setResponseCode(ResponseCodeConstant.SUCCESS_CODE);
         return ResponseEntity.ok(result);
      }catch (Exception e){
         if(log.isErrorEnabled()){
            log.error(e.getMessage(),e);
         }
         result.setResponseCode(ResponseCodeConstant.ERROR_CODE);
         result.setResponseMessage(e.getMessage());
         return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/consumption")
   @ApiOperation("消费，输入的金额为负数")
   @Transactional(isolation = Isolation.REPEATABLE_READ)
   public ResponseEntity<ResponseMessage> consumption(@RequestBody @Valid UpdateMoneyReq req){
      ResponseMessage result = new ResponseMessage();
      try {
         userInfoService.consumption(req);
         result.setResponseCode(ResponseCodeConstant.SUCCESS_CODE);
         return ResponseEntity.ok(result);
      }catch (Exception e){
         if(log.isErrorEnabled()){
            log.error(e.getMessage(),e);
         }
         //事务回滚
         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         result.setResponseCode(ResponseCodeConstant.ERROR_CODE);
         result.setResponseMessage(e.getMessage());
         return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PostMapping("/drawback")
   @ApiOperation("退款，输入的金额为正数")
   @Transactional(isolation = Isolation.REPEATABLE_READ)
   public ResponseEntity<ResponseMessage> drawback(@RequestBody @Valid UpdateMoneyReq req){
      ResponseMessage result = new ResponseMessage();
      try {
         userInfoService.drawback(req);
         result.setResponseCode(ResponseCodeConstant.SUCCESS_CODE);
         return ResponseEntity.ok(result);
      }catch (Exception e){
         if(log.isErrorEnabled()){
            log.error(e.getMessage(),e);
         }
         //事务回滚
         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         result.setResponseCode(ResponseCodeConstant.ERROR_CODE);
         result.setResponseMessage(e.getMessage());
         return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

}
