package com.eshop.service.impl;

import com.eshop.dto.req.QueryWalletDetailsByUserIdReq;
import com.eshop.dto.resp.QueryByPageResp;
import com.eshop.dto.resp.WalletDetail;
import com.eshop.dto.resp.WalletDetailsResp;
import com.eshop.mapper.WalletDetailsMapper;
import com.eshop.pojo.WalletDetails;
import com.eshop.service.WalletDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/3
 * @Describe
 **/
@Service
public class WalletDetailsServiceImpl implements WalletDetailsService {


    @Autowired
    private WalletDetailsMapper walletDetailsMapper;

    @Override
    public QueryByPageResp<List<WalletDetail>> selectByUserId(QueryWalletDetailsByUserIdReq req) {
        QueryByPageResp<List<WalletDetail>> resp = new QueryByPageResp<>();
        //创建集合用于保存查询到的钱包余额变化信息
        List<WalletDetailsResp> list = null;

        //判断是否需要进行分页查询
        if(!ObjectUtils.isEmpty(req.getCurrentPage()) && !ObjectUtils.isEmpty(req.getPageSize())){
            PageInfo<WalletDetailsResp> pageInfo = PageHelper.startPage(req.getCurrentPage(),req.getPageSize()).doSelectPageInfo(() ->{
                walletDetailsMapper.selectByUserId(req);
            });
            list = pageInfo.getList();
            resp.setCurrentPage(pageInfo.getPageNum());
            resp.setPageSize(pageInfo.getPageSize());
            resp.setTotalCount(pageInfo.getTotal());
        }else{
            list = walletDetailsMapper.selectByUserId(req);
            resp.setTotalCount(((Integer) list.size()).longValue());
        }
        //设置key值降序排序
        Map<String,List<WalletDetailsResp>> map = new TreeMap<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        List<WalletDetailsResp> li = null;
        //根据年月分组
        for (WalletDetailsResp detail : list) {
            if(ObjectUtils.isEmpty(map.get(detail.getYear_month()))){
                 li = new ArrayList<>();
                 li.add(detail);
                 map.put(detail.getYear_month(),li);
            }else{
                li = map.get(detail.getYear_month());
                li.add(detail);
                map.put(detail.getYear_month(),li);
            }
        }

        List<WalletDetail> result = new ArrayList<>();
        WalletDetail detail = null;
        for (String key : map.keySet()) {
            detail = new WalletDetail();
            detail.setYear_month(key);
            detail.setList(map.get(key));
            result.add(detail);
        }
        resp.setData(result);
        return resp;
    }

    @Override
    public void insertWalletDetails(WalletDetails walletDetails) {
        //生成wdId
        String wdId = UUID.randomUUID().toString();
        walletDetails.setWdId(wdId);
        walletDetailsMapper.insertWalletDetails(walletDetails);
    }
}
