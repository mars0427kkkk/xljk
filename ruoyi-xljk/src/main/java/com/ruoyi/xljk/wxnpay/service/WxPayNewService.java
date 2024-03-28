package com.ruoyi.xljk.wxnpay.service;

import com.ruoyi.xljk.wxnpay.dto.JsapiPayDto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * @author LiJinLong
 * @description
 * @create 2023-10-12 22:24
 * @date 1.0
 */
public interface WxPayNewService {
//    void nativePay(Long baseOrderPayInfoId) throws IOException;
//
//    void processOrder(Map<String, Object> bodyMap) throws GeneralSecurityException;

    Map<String, String> jsapiWxxPay(JsapiPayDto payDto) throws Exception;

    void processWxxOrder(Map<String, Object> bodyMap) throws GeneralSecurityException;

}
