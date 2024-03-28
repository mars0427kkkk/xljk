package com.ruoyi.xljk.wxnpay.config;


import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * <p>WQPay 让支付触手可及，封装了微信支付、支付宝支付、银联支付常用的支付方式以及各种常用的接口。</p>
 *
 * <p>不依赖任何第三方 mvc 框架，仅仅作为工具使用简单快速完成支付模块的开发，可轻松嵌入到任何系统里。 </p>
 *
 * <p>WQPay jiumi</p>
 *
 * <p>Node.js 版: https://gitee.com/javen205/TNW</p>
 *
 * <p>微信支付常用配置</p>
 *
 * @author Javen
 */
@Slf4j
@Configuration
@PropertySource("classpath:wxpaynew.properties") //读取配置文件
@ConfigurationProperties(prefix="wxpaynew") //读取wxpay节点
//@Data //使用set方法将wxpay节点中的值填充到当前类的属性中
public class WxPayConfig implements Serializable {
    // 商户号
    private String mchId;

    // 商户API证书序列号
    private String mchSerialNo;

    // 商户私钥文件
    private String privateKeyPath;

    // APIv3密钥
    private String apiV3Key;

    // APPID
    private String appid;

    // 小程序APPID
    private String xcxAppid;

    // 微信服务器地址
    private String domain;

    // 接收结果通知地址
    private String notifyDomain;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchSerialNo() {
        return mchSerialNo;
    }

    public void setMchSerialNo(String mchSerialNo) {
        this.mchSerialNo = mchSerialNo;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getApiV3Key() {
        return apiV3Key;
    }

    public void setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getXcxAppid() {
        return xcxAppid;
    }

    public void setXcxAppid(String xcxAppid) {
        this.xcxAppid = xcxAppid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getNotifyDomain() {
        return notifyDomain;
    }

    public void setNotifyDomain(String notifyDomain) {
        this.notifyDomain = notifyDomain;
    }

    @Override
    public String toString() {
        return "WxPayConfig{" +
                "mchId='" + mchId + '\'' +
                ", mchSerialNo='" + mchSerialNo + '\'' +
                ", privateKeyPath='" + privateKeyPath + '\'' +
                ", apiV3Key='" + apiV3Key + '\'' +
                ", appid='" + appid + '\'' +
                ", xcxAppid='" + xcxAppid + '\'' +
                ", domain='" + domain + '\'' +
                ", notifyDomain='" + notifyDomain + '\'' +
                '}';
    }

    /**
     * 获取商户私钥文件
     * @return
     */
    public PrivateKey getPrivateKey(String filename) {
        PrivateKey privateKey = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(filename);
            InputStream inputStream = classPathResource.getInputStream();
            privateKey = PemUtil.loadPrivateKey(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("私钥文件不存在", e);
        } catch (IOException e) {
            throw new RuntimeException("私钥文件不存在", e);
        }
        return privateKey;
    }


    /**
     * 获取签名验证器
     * @return
     */
    @Bean
    public ScheduledUpdateCertificatesVerifier getVerifier() {
        log.info("获取签名验证器");
        // 获取商户私钥
        PrivateKey privateKey = getPrivateKey(privateKeyPath);

        // 私钥签名对象
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(mchSerialNo, privateKey);

        // 身份认证对象
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(mchId, privateKeySigner);

        // 使用定时更新的签名验证器，不需要传入证书
        ScheduledUpdateCertificatesVerifier verifier = new ScheduledUpdateCertificatesVerifier(
                wechatPay2Credentials,
                apiV3Key.getBytes(StandardCharsets.UTF_8));
        return verifier;
    }

    /**
     * 获取http请求对象
     * @param verifier
     * @return
     */
    @Bean
    public CloseableHttpClient getWxPayClient(ScheduledUpdateCertificatesVerifier verifier) {
        log.info("获取httpclient");
        // 获取商户私钥
        PrivateKey privateKey = getPrivateKey(privateKeyPath);
        //...
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, privateKey)
                .withValidator(new WechatPay2Validator(verifier));
        // ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient

        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
        CloseableHttpClient httpClient = builder.build();

        return httpClient;
    }

}
