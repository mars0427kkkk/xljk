package com.ruoyi.xljk.wxnpay.service.impl;


import com.google.gson.Gson;
//import com.ruoyi.order.domain.BaseOrderInfo;
//import com.ruoyi.order.domain.BaseOrderPayInfo;
//import com.ruoyi.order.service.IBaseOrderInfoService;
//import com.ruoyi.order.service.IBaseOrderPayInfoService;
//
import com.ruoyi.xljk.domain.AnswerLocalhost;
import com.ruoyi.xljk.service.IAnswerLocalhostService ;
//import com.ruoyi.service.user.OrderInformService;
import com.ruoyi.xljk.domain.OrderInform;
import com.ruoyi.xljk.wxnpay.config.WxPayConfig;
import com.ruoyi.xljk.wxnpay.dto.JsapiPayDto;
import com.ruoyi.xljk.wxnpay.enums.WxApiType;
import com.ruoyi.xljk.wxnpay.enums.WxNotifyType;
import com.ruoyi.xljk.wxnpay.service.OrderInformService;
import com.ruoyi.xljk.wxnpay.service.WxPayNewService;
import com.ruoyi.xljk.wxnpay.utils.CreateSignUtils;
import com.ruoyi.xljk.wxnpay.utils.OrderNoUtils;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LiJinLong
 * @description
 * @create 2023-10-12 22:24
 * @date 1.0
 */
@Slf4j
@Service
public class WxPayNewServiceImpl implements WxPayNewService {

//    @Autowired
//    private IBaseOrderPayInfoService baseOrderPayInfoService;
//
//    @Autowired
//    private IBaseOrderInfoService baseOrderInfoService;

    @Autowired
    private CloseableHttpClient wxPayClient;

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private OrderInformService orderInformService;

    @Autowired
    private IAnswerLocalhostService answerLocalhostService;
//
//    @Autowired
//    private VIPEndTimeMapper vipEndTimeMapper;

    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

//    @Override
//    public void nativePay(Long baseOrderPayInfoId) throws IOException {
//        BaseOrderPayInfo orderInfo = baseOrderPayInfoService.selectBaseOrderPayInfoById(baseOrderPayInfoId);
//        log.info("生成订单");
//
//        String codeUrl = orderInfo.getPayInfo();
//        if (orderInfo != null && !StringUtils.isEmpty(codeUrl)) {
//            return ;
//        }
//
//        // native下单
//        //请求URL
//        log.info("调用统一下单API");
//        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.NATIVE_PAY.getType()));
//
//        BaseOrderInfo baseOrderInfo = baseOrderInfoService.selectBaseOrderInfoById(orderInfo.getOrderId());
//
//        // 请求body参数
//        Gson gson = new Gson();
//        Map<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("appid", wxPayConfig.getAppid());
//        paramsMap.put("mchid", wxPayConfig.getMchId());
//        paramsMap.put("description", baseOrderInfo.getMealName());
//        paramsMap.put("out_trade_no", baseOrderInfo.getOrderNumber());
//        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.NATIVE_NOTIFY.getType()));
//        // 封装订单金额
//        Map<String, Object> amountMap = new HashMap<>();
//        amountMap.put("total", (int)(orderInfo.getRealAmount().floatValue() * 100));
//        amountMap.put("currency", "CNY");
//        paramsMap.put("amount", amountMap);
//
//        String jsonParams = gson.toJson(paramsMap);
//        log.info("native请求参数{}", jsonParams);
//
//        StringEntity entity = new StringEntity(jsonParams,"utf-8");
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//
//        //完成签名并执行请求
//        CloseableHttpResponse response = wxPayClient.execute(httpPost);
//        try {
//            int statusCode = response.getStatusLine().getStatusCode();
//            String bodyAsString = EntityUtils.toString(response.getEntity());
//            if (statusCode == 200) {
//                log.info("成功, 返回二维码响应体 = " + bodyAsString);
//            } else if (statusCode == 204) {
//                log.info("成功，无返回体");
//            } else {
//                log.info("Native下单失败, 状态码 = " + statusCode+ ", 响应体 = " + bodyAsString);
//                throw new IOException("request failed");
//            }
//
//            Map<String, String> resultMap = gson.fromJson(bodyAsString, Map.class);
//
//
//            // native支付二维码
//            codeUrl = resultMap.get("code_url");
//
//            // 保存生成的二维码
//            orderInfo.setPayInfo(codeUrl);
//            baseOrderPayInfoService.updateBaseOrderPayInfo(orderInfo);
//
//        } finally {
//            response.close();
//        }
//
//    }
//
//    @Override
//    public void processOrder(Map<String, Object> bodyMap) throws GeneralSecurityException {
//        // 支付回调通知明文
//        String plainText = decryptFromResource(bodyMap);
//
//        Gson gson = new Gson();
//        HashMap<String, Object> map = gson.fromJson(plainText, HashMap.class);
//        String orderNo = (String) map.get("out_trade_no");
//
//        // 进行并发控制，使接口具有幂等性
//        // 获取不到锁立即返回，synchronized是拿不到锁就阻塞等待
//        if (lock1.tryLock()) {
//            try {
//                // 查询订单状态避免重复更新已支付订单和记录订单日志
//                BaseOrderPayInfo baseOrderPayInfo = baseOrderPayInfoService.getOneByOrderNumber(orderNo);
//                // 非未支付订单直接结束
//                if (baseOrderPayInfo != null && "1".equals(baseOrderPayInfo.getPaymentStatus())) {
//                    return;
//                }
//
//                // 更新订单状态
//                baseOrderPayInfo.setPaymentStatus("1");
//                baseOrderPayInfo.setPayTime(new Date());
//                baseOrderPayInfo.setPayAmount(baseOrderPayInfo.getRealAmount());
//                baseOrderPayInfoService.updateBaseOrderPayInfo(baseOrderPayInfo);
//
//                // 记录订单日志
//                BaseOrderInfo orderInfo = baseOrderInfoService.selectBaseOrderInfoById(baseOrderPayInfo.getOrderId());
//                orderInfo.setOrderStatus("1");
//                orderInfo.setPayTime(new Date());
//                baseOrderInfoService.updateBaseOrderInfo(orderInfo);
//            } finally {
//                lock1.unlock();
//            }
//        }
//    }

    @Override
    public Map<String, String> jsapiWxxPay(JsapiPayDto payDto) throws Exception {
        String openid = payDto.getOpenid();

        OrderInform orderInform = orderInformService.getOrderInformByOpenId(openid);
        // 已经生成预订单号的直接返回
        if (orderInform != null) {
            Date curDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(orderInform.getCreateTime());
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            Date pastAddOneTime = calendar.getTime();
            if (orderInform.getFlags()) {
                Map<String, String> map = new HashMap<>();
                map.put("flag", "已支付");
                return map;
            }else if (curDate.after(pastAddOneTime) || !payDto.getMoney().equals(orderInform.getMoney())) {
                orderInformService.deleteOrderInformById(orderInform.getId());
            } else if (!orderInform.getFlags() && orderInform.getPrepayId() != null) {
                // 生成签名提供给小程序使用
                String xcxAppid = wxPayConfig.getXcxAppid();
                String packages = "prepay_id="+orderInform.getPrepayId();

                CreateSignUtils.initParams(wxPayConfig.getPrivateKey(wxPayConfig.getPrivateKeyPath()), xcxAppid);
                Map<String, String> signMap = CreateSignUtils.createPayParams(packages);

                signMap.put("prepay_id", orderInform.getPrepayId());
                signMap.put("flag", "未支付");

                return signMap;
            }
        }

        log.info("生成订单，用户");
        orderInform = new OrderInform();
        orderInform.setOpenId(openid);
        orderInform.setOrderId(OrderNoUtils.getOrderNo());
        orderInform.setFlags(false);
        orderInform.setDescribeInfo("vip卡");
        orderInform.setMoney(payDto.getMoney());
        orderInform.setCreateTime(new Date());
        orderInform.setUpdateTime(new Date());
        orderInform = orderInformService.createNewOrderInform(orderInform);


        // 拼接请求
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.WXX_JSAPI_PAY.getType()));

        Gson gson = new Gson();
        Map<String, Object> paramsMap = new HashMap<>();

        paramsMap.put("appid", wxPayConfig.getXcxAppid());
        paramsMap.put("mchid", wxPayConfig.getMchId());
        paramsMap.put("description", orderInform.getDescribeInfo());
        paramsMap.put("out_trade_no", orderInform.getOrderId());
        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.WXX_JSAPI_NOTIFY.getType()));

        // 封装订单金额
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("total", (int)(orderInform.getMoney() * 100));
        amountMap.put("currency", "CNY");
        paramsMap.put("amount", amountMap);

        // 封装支付者
        Map<String, Object> payerMap = new HashMap<>();
        payerMap.put("openid", openid);
        paramsMap.put("payer", payerMap);

        String jsonParams = gson.toJson(paramsMap);
        log.info("wxx_jsapi_pay请求参数{}", jsonParams);

        StringEntity entity = new StringEntity(jsonParams,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = wxPayClient.execute(httpPost);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            String bodyAsString = EntityUtils.toString(response.getEntity());
            if (statusCode == 200) {
                log.info("成功, 返回二维码响应体 = " + bodyAsString);
            } else if (statusCode == 204) {
                log.info("成功，无返回体");
            } else {
                log.info("Native下单失败, 状态码 = " + statusCode+ ", 响应体 = " + bodyAsString);
                throw new IOException("request failed");
            }

            Map<String, String> resultMap = gson.fromJson(bodyAsString, Map.class);


            // 获取微信平台的预定单编号
            String prepayId = resultMap.get("prepay_id");

            // 保存生成的预定单编号
            orderInform.setPrepayId(prepayId);
            orderInform.setUpdateTime(new Date());
            orderInformService.updateOrderInformOfPrepayId(orderInform);

            // 生成签名提供给小程序使用
            String xcxAppid = wxPayConfig.getXcxAppid();
            String packages = "prepay_id="+orderInform.getPrepayId();

            CreateSignUtils.initParams(wxPayConfig.getPrivateKey(wxPayConfig.getPrivateKeyPath()), xcxAppid);
            Map<String, String> signMap = CreateSignUtils.createPayParams(packages);

            signMap.put("prepay_id", prepayId);
            signMap.put("flag", "未支付");

            return signMap;
        } finally {
            response.close();
        }


    }


    /**
     * 微信小程序处理回调通知
     * @param bodyMap
     * @throws GeneralSecurityException
     */
    @Override
    public void processWxxOrder(Map<String, Object> bodyMap) throws GeneralSecurityException {
        // 支付回调通知明文
        String plainText = decryptFromResource(bodyMap);

        Gson gson = new Gson();
        HashMap<String, Object> map = gson.fromJson(plainText, HashMap.class);
        String orderNo = (String) map.get("out_trade_no");

        // 进行并发控制，使接口具有幂等性
        // 获取不到锁立即返回，synchronized是拿不到锁就阻塞等待
        if (lock2.tryLock()) {
            try {
                // 查询订单状态避免重复更新已支付订单和记录订单日志
                OrderInform orderInform = orderInformService.getOrderInformByOrderId(orderNo);
                // 已支付订单直接结束
                if (orderInform.getFlags()) {
                    return;
                }

                // 更新订单vip状态
                orderInform.setFlags(true);
                orderInform.setUpdateTime(new Date());
                orderInformService.updateOrderInformOfFlags(orderInform);
                // 更新报告vip状态
                AnswerLocalhost answerLocalhost = answerLocalhostService.findByOpenid(orderInform.getOpenId());
                answerLocalhost.setStatus(1L);

                answerLocalhostService.updateAnswerLocalhost(answerLocalhost);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock2.unlock();
            }
        }
    }

    private String decryptFromResource(Map<String, Object> bodyMap) throws GeneralSecurityException {
        // 支付回调通知
        Map<String, String> resourceMap = (Map<String, String>)bodyMap.get("resource");
        // Base64编码后的开启/停用结果数据密文
        String ciphertext = resourceMap.get("ciphertext");
        // 加密使用的随机串
        String nonce = resourceMap.get("nonce");
        // 附加数据
        String associatedData = resourceMap.get("associated_data");

        log.info("密文 ===> {}", ciphertext);
        AesUtil aesUtil = new AesUtil(wxPayConfig.getApiV3Key().getBytes(StandardCharsets.UTF_8));
        String plainText = aesUtil.decryptToString(associatedData.getBytes(StandardCharsets.UTF_8), nonce.getBytes(StandardCharsets.UTF_8), ciphertext);
        log.info("明文 ===> {}", plainText);

        return plainText;
    }
}
