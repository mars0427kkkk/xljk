package com.ruoyi.xljk.wxnpay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WxNotifyType {

	/**
	 * 支付通知
	 */
	NATIVE_NOTIFY("/api/wx-pay/native/notify"),

	/**
	 * 无心小程序支付通知  /api/wxx-pay/native/notify
	 */
	WXX_JSAPI_NOTIFY("/xljk/wxx-pay/native/notify"),


	/**
	 * 支付通知
	 */
	NATIVE_NOTIFY_V2("/api/wx-pay-v2/native/notify"),


	/**
	 * 退款结果通知
	 */
	REFUND_NOTIFY("/api/wx-pay/refunds/notify");

	/**
	 * 类型
	 */
	private final String type;
}
