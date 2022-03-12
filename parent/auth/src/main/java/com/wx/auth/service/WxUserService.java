package com.wx.auth.service;

import com.wx.auth.entity.LoginParams;
import com.wx.auth.entity.WxPermission;
import com.wx.auth.entity.WxUser;
import com.wx.common.commonresult.CommonResult;

import java.util.List;

public interface WxUserService {

    WxUser getUserByName(String name);

    List<WxPermission> getPermissionsByUserId(Integer id);

    String login(LoginParams loginParams);

    CommonResult checkAccessToUri(String uri, String username);
}