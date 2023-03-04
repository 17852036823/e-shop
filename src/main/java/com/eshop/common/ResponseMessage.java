package com.eshop.common;

import io.swagger.annotations.ResponseHeader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ResponseMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息体，具体类型通过泛型决定
     */
    @Nullable
    @Valid
    private T data;

    /**
     * 请求返回码
     */
    private String responseCode;

    /**
     * 返回响应信息
     */
    private String responseMessage;

    /**
     * 返回响应头
     */
    private ResponseHeader responseHeader;

    public ResponseMessage(){}

    public ResponseMessage(T data){
        this.data = data;
    }

    public ResponseMessage(ResponseHeader responseHeader, T data) {
        this.data = data;
        this.responseHeader = responseHeader;
    }

}
