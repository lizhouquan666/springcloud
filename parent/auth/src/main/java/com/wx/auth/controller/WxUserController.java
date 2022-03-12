package com.wx.auth.controller;

import com.wx.auth.entity.LoginParams;
import com.wx.auth.service.WxUserService;
import com.wx.common.commonresult.CommonResult;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class WxUserController {
    @Value("${jwt.tokenHead}")
    String tokenHead;

    @Autowired
    private WxUserService wxUserService;

    /**
     * 登录测试
     * @param loginParams
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginParams loginParams){
        HashMap<String, String> data = new HashMap<>();
        String token = wxUserService.login(loginParams);
        Assert.notNull(token,"用户名或密码不正确！");
        data.put("tokenHead",tokenHead);
        data.put("access_token",token);
        return CommonResult.success(data);
        //验证码校验
//        if (!checkCode.equals("")) {
//            if (!checkCode.equalsIgnoreCase(user.getCode())) {
//                commonresult.setData("codeErr");
//            }
//        }else{
//            commonresult.setMessage("codeNull");
//        }

//        return commonResult;
    }

    /**
     * 远程权限验证
     * @param uri
     * @param username
     * @return
     */
    @GetMapping("/checkAccessToUri")
    public CommonResult checkAccessToUri(@RequestParam String uri, @RequestParam String username){
        return wxUserService.checkAccessToUri(uri,username);
    }

}