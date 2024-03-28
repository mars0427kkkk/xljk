package com.ruoyi.xljk.wxnpay.utils;


import lombok.Data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author LiJinLong
 * @description
 * @create 2023-10-17 21:41
 * @date 1.0
 */
@Data
public class CreateSignUtils {

    public static PrivateKey privateKey;
    public static String appId;

    public static void initParams(PrivateKey privateKey, String appId){
        CreateSignUtils.privateKey = privateKey;
        CreateSignUtils.appId = appId;
    }

    /**
     * 微信支付-前端唤起支付参数
     * prepay_id=wx201410272009395522657a690389285100
     * @param packageStr 预下单接口返回数据 预支付交易会话标识	prepay_id
     * @return
     */
    public static Map<String, String> createPayParams(String packageStr) {
        Map<String, String> resultMap = new HashMap<>();
        String nonceStr = UUID.randomUUID().toString().replace("-", "");
        // String nonceStr = StringUtil.getUUID();
        Long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(appId, timestamp, nonceStr, packageStr);
        String signature = null;
        try {
            signature = sign(message.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resultMap.put("timeStamp",timestamp.toString());
        resultMap.put("nonceStr",nonceStr);
        resultMap.put("package",packageStr);
        resultMap.put("signType","RSA");
        resultMap.put("paySign",signature);
        return resultMap;
    }
    /**
     * 微信支付-前端唤起支付参数-签名
     * @param message 签名数据
     * @return
     */
    public static String sign(byte[] message) {
        try{
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(message);
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 微信支付-前端唤起支付参数-构建签名参数
     * @param nonceStr 签名数据
     * @return
     */
    public static String buildMessage(String appId, long timestamp, String nonceStr, String packageStr) {
        return appId + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + packageStr + "\n";
    }


    /**
     * 微信支付-前端唤起支付参数-获取商户私钥
     *
     * @param filename 私钥文件路径  (required)
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");

        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

}
