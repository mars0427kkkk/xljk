package com.ruoyi.common.exception.wxmpsms;

/**
 * @author wut
 * @PackageName:com.szbt.common.exception.wxmpsms
 * @ClassName:MpsException
 * @Description: mps服务异常
 * @date 2023/6/8 19:18
 */
public class MpsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;


    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public MpsException() {
    }

    public MpsException(String message) {
        this.message = message;
    }

    public MpsException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public MpsException setMessage(String message) {
        this.message = message;
        return this;
    }

    public MpsException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
