package com.ruoyi.xljk.wxnpay.dto;

import lombok.Data;

/**
 * @author LiJinLong
 * @description
 * @create 2023-10-17 14:52
 * @date 1.0
 */
@Data
public class JsapiPayDto {
    /**
     * 微信用户标识
     */
    private String openid;
    /**
     * 订单金额
     */
    private Double money;
}
