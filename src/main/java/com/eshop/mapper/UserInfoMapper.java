package com.eshop.mapper;

import com.eshop.dto.req.UpdateMoneyReq;
import com.eshop.pojo.UserInfo;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
public interface UserInfoMapper {

    public UserInfo selectUserInfoById(String userId);

    public void updateMoneyByUserId(UpdateMoneyReq req);
}
