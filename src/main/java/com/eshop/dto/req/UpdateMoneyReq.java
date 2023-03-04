package com.eshop.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
@Getter
@Setter
public class UpdateMoneyReq implements Serializable {

   private static final long serialVersionUID = 1L;

   /**
    * 商品信息
    */
   private Object information;

   private String userId;

   private BigDecimal money;

   private String describe;
}
