package com.eshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.eshop.common.ResponseCodeConstant;
import com.eshop.common.ResponseMessage;
import com.eshop.dto.req.QueryWalletDetailsByUserIdReq;
import com.eshop.dto.resp.QueryByPageResp;
import com.eshop.dto.resp.WalletDetail;
import com.eshop.dto.resp.WalletDetailsResp;
import com.eshop.pojo.WalletDetails;
import com.eshop.service.WalletDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/4
 * @Describe
 **/
@Controller
@RequestMapping("/walletDetails")
@Slf4j
@Api(tags = "钱包明细")
public class WalletDetailsController {

   @Autowired
   private WalletDetailsService walletDetailsService;

   @PostMapping("/queryWalletDetailsByUserId")
   @ApiOperation("查询用户钱包金额变动明细")
   public ResponseEntity<ResponseMessage<QueryByPageResp<List<WalletDetail>>>> queryWalletDetailsByUserId(@RequestBody @Valid QueryWalletDetailsByUserIdReq req){
      ResponseMessage<QueryByPageResp<List<WalletDetail>>> result = new ResponseMessage<>();
      try {
            QueryByPageResp<List<WalletDetail>> resp = walletDetailsService.selectByUserId(req);
            result.setData(resp);
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


}
