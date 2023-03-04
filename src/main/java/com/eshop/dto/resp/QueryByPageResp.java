package com.eshop.dto.resp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @From e-shop
 * @Author hykuang
 * @Date 2023/3/4
 * @Describe
 **/
@Setter
@Getter
public class QueryByPageResp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalCount;

    private T data;

}
