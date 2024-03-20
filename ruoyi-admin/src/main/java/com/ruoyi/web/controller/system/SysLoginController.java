package com.ruoyi.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.model.WxLoginBody;
import com.ruoyi.common.core.domain.vo.WxLoginResultVo;
import com.ruoyi.common.utils.http.HttpUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.web.client.RestTemplate;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Slf4j
@RestController
public class SysLoginController
{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 登录方法
     *
     * @param wxLoginBody 登录信息
     * @return 结果
     */
    private final String APPID = "wxff4b11d1e465f30f";
    private final String APPSECRET = "c1727d63f74bb274ff4c8b5e8bfea453";
    @PostMapping("/wxLogin")
    public AjaxResult wxLogin(@RequestBody WxLoginBody wxLoginBody) {
        AjaxResult ajax = AjaxResult.success();
        //微信官方提供的微信小程序登录授权时使用的URL地址
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        /**
         * 拼接需要的参数
         * appid = AppID 你自己的微信小程序APPID
         * js_code = AppSecret 你自己的微信APP密钥
         * grant_type=authorization_code = code 微信官方提供的临时凭证
         */
        String params = StrUtil.format("appid={}&secret={}&js_code={}&grant_type=authorization_code", APPID, APPSECRET, wxLoginBody.getCode());
        //向微信服务器发送恳求获取用户信息
        String res = HttpUtils.sendGet(url, params);
//        log.info("wxLogin返回：{}", res);
        WxLoginResultVo wxLoginResultVo = JSON.parseObject(res, WxLoginResultVo.class);
        if (wxLoginResultVo.getErrcode() == 40029) {
            return AjaxResult.error("wxLoginCode无效");
        }
        if (wxLoginResultVo.getErrcode() == 45011) {
            return AjaxResult.error("API 调用太频繁，请稍候再试");
        }
        if (wxLoginResultVo.getErrcode() == 40226) {
            return AjaxResult.error("高风险等级用户，小程序登录拦截 。");
        }
        if (wxLoginResultVo.getErrcode() == -1) {
            return AjaxResult.error("系统繁忙，此时请开发者稍候再试");
        }
        String openid = wxLoginResultVo.getOpenid();
        if (StrUtil.isEmpty(openid)) {
            return AjaxResult.error("未获取到openid");
        }
        // 生成令牌
        String token = loginService.wxLogin(openid, wxLoginBody.getNickName(), wxLoginBody.getAvatarUrl());
        ajax.put(Constants.TOKEN, token);
        ajax.put(Constants.OPENID, openid);

        return ajax;
    }

    @PostMapping("/wxgetPhone")
    @ApiOperation("从微信服务器获取小程序用户手机号")
    public AjaxResult wxxgetPhone(@RequestBody JSONObject object) {
        String code = object.getString("code");
        String openid = object.getString("openId");
        // =============== 发送请求拿到accessToken ===============
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential" +
                "&appid=" + APPID +
                "&secret=" + APPSECRET;

        String tokenRes = restTemplate.getForObject(tokenUrl, String.class);
        JSONObject tokenJson = JSONObject.parseObject(tokenRes);
        String accessToken = tokenJson.getString("access_token");
        log.info("accessToken: "+accessToken);

        // =============== 发送请求拿到手机号 ===============
        // 设置请求头，包括Content-Type和AccessToken
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 创建请求体参数
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("code", code);

        // 创建HttpEntity对象，将请求体和请求头组合
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        // 发送POST请求
        String phoneUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+accessToken;
        ResponseEntity<String> response = restTemplate.postForEntity(phoneUrl, request, String.class);
        String phoneBody = response.getBody();
        String phoneInfo = JSONObject.parseObject(phoneBody).getString("phone_info");
        String phoneNumber = JSONObject.parseObject(phoneInfo).getString("purePhoneNumber");
        log.info("phoneNumber: "+phoneNumber);


        return loginService.updatePhoneByOpenid(phoneNumber, openid);
    }
    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
