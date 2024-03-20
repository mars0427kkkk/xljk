package com.ruoyi.common.core.domain.model;

/**
 * @author wut
 * @PackageName:com.szbt.common.core.domain.model
 * @ClassName:WxLoginBody
 * @Description: 微信用户登录对象
 * @date 2023/5/31 18:22
 */public class WxLoginBody {

    /**
     * 暂时登陆凭据 code 只能运用一次
     */
    private String code;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatarUrl;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
