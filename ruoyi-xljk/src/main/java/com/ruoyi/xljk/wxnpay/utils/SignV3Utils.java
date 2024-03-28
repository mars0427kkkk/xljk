package com.ruoyi.xljk.wxnpay.utils;

import lombok.Data;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Data
public class SignV3Utils {
    /**
     * 小程序appid
     */
    private static String appId;

    /**
     * 用户私钥
     */
    private static String privateKey;
    private static String packages;

    public static void initParams(String appId, String privateKey, String packages) {
        SignV3Utils.appId = appId;
        SignV3Utils.privateKey = privateKey;
        SignV3Utils.packages = packages;
    }

    public static Map<String, String> getSignMap() throws Exception {
        String nonceStr = nonceString();
        long timestamp = System.currentTimeMillis() / 1000;

        String message = buildMessage(timestamp, nonceStr, SignV3Utils.appId, SignV3Utils.packages);
        String signature = sign(message.getBytes("utf-8"));

        Map<String, String> map = new HashMap<>();
        map.put("timeStamp", String.valueOf(timestamp));
        map.put("nonceStr", nonceStr);
        map.put("package", SignV3Utils.packages);
        map.put("signType", "RSA");
        map.put("paySign", signature);
        return map;
    }

    public static String sign(byte[] message) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(getPKCS8PrivateKey(privateKey));
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    public static String buildMessage(long timestamp, String nonceStr, String appId, String packages) {

        return appId+"\n"+timestamp+"\n"+nonceStr+"\n"+packages+"\n";
    }


    private static PrivateKey getPKCS8PrivateKey(String strPk) throws Exception {
        String realPK = strPk.replaceAll("-----END PRIVATE KEY-----", "")
                .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("\n", "");

        byte[] b1 = Base64.getDecoder().decode(realPK);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);

        KeyFactory kf = KeyFactory.getInstance("RSA");

        return kf.generatePrivate(spec);
    }

    public static String nonceString() {

        String currTime = String.format("%d", (long) System.currentTimeMillis() / 1000);

        String strTime = currTime.substring(8, currTime.length());

        Random random = new Random();
        int num = (int) (random.nextDouble() * (1000000 - 100000) + 100000);
        String code = String.format("%06d", num);

        String nonce_str = currTime.substring(2) + code;
        return nonce_str;

    }

}