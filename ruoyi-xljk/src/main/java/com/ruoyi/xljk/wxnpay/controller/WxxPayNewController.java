package com.ruoyi.xljk.wxnpay.controller;

import com.google.gson.Gson;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.xljk.wxnpay.dto.JsapiPayDto ;
import com.ruoyi.xljk.wxnpay.service.WxPayNewService;
import com.ruoyi.xljk.wxnpay.utils.HttpUtils;
import com.ruoyi.xljk.wxnpay.utils.WechatPay2ValidatorForRequest;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiJinLong
 * @description
 * @create 2023-10-17 14:45
 * @date 1.0
 */

@CrossOrigin
@RequestMapping("api/wxx-pay/")
@Slf4j
@RestController
@Api(tags = "微信小程序支付")
public class WxxPayNewController {

    @Autowired
    private WxPayNewService wxPayService;

    @Autowired
    private Verifier verifier;

    @ApiOperation("创建订单返回微信服务器预定单")
    @PostMapping("jsapiPay")
    public AjaxResult jsapiPay(@RequestBody JsapiPayDto payDto) throws Exception {
        Map<String, String> map = wxPayService.jsapiWxxPay(payDto);
        return AjaxResult.success(map);
    }


    @ApiOperation("接收微信回调通知")
    @PostMapping("native/notify")
    @Log(title = "接收微信回调通知", businessType = BusinessType.UPDATE)
    public String nativeNotify(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> map = new HashMap<>();
        Gson gson = new Gson();
        try {
            String body = HttpUtils.readData(request);

            Map<String, Object> bodyMap = gson.fromJson(body, HashMap.class);
            String requestId = (String) bodyMap.get("id");
            log.info("回调通知的id:{}", requestId);
            log.info("回调通知的body:{}", body);

            // 签名的验证
            WechatPay2ValidatorForRequest validator = new WechatPay2ValidatorForRequest(verifier, requestId, body);
            if (!validator.validate(request)) {

                log.info("通知验签失败");

                response.setStatus(500);
                map.put("code", "ERROR");
                map.put("message", "通知验签失败");
                return gson.toJson(map);
            }
            log.info("验签成功");

            // 处理订单
            wxPayService.processWxxOrder(bodyMap);

            response.setStatus(200);
            map.put("code", "SUCCESS");
            map.put("message", "成功");
            return gson.toJson(map);

        } catch (Exception e) {
            response.setStatus(500);
            map.put("code", "ERROR");
            map.put("message", "失败");
            return gson.toJson(map);
        }
    }

}
