package com.wx.auth.service.impl;

import com.wx.auth.dao.WxUserDao;
import com.wx.auth.entity.LoginParams;
import com.wx.auth.entity.WxPermission;
import com.wx.auth.entity.WxUser;
import com.wx.auth.service.WxUserService;
import com.wx.auth.tool.JwtTokenUtil;
import com.wx.common.commonresult.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public WxUser getUserByName(String name) {
        List<WxUser> users= wxUserDao.getUserByName(name);
        Assert.isTrue(users.size()==1,"您输入的账户不存在，或者有多个相同的账户");
        return users.get(0);
    }

    @Override
    public List<WxPermission> getPermissionsByUserId(Integer id) {
        return wxUserDao.getPermissionsByUserId(id);
    }

    @Override
    public String login(LoginParams loginParams) {
        String username = loginParams.getUsername();
        Assert.notNull(username,"账号必须不能为空");
        String password = loginParams.getPassword();
        Assert.notNull(password,"密码必须不能为空");
        // 根据用户查询用户实例
        WxUser userByName = getUserByName(username);
        // 验证密码
        boolean matches = passwordEncoder.matches(password, userByName.getPassword());
        if(matches){
            // 获取用户所有权限
            List<WxPermission> permissionsByUserId = getPermissionsByUserId(userByName.getId());
            // 将权限list 转化为set形式
            HashSet<WxPermission> wxPermissions = new HashSet<>(permissionsByUserId);
            // 将权限设置到用户实例中
            userByName.setAuthorities(wxPermissions);
            // 使用jwtTokenUtil 生成token
            return jwtTokenUtil.generateToken(userByName);
        }
        return null;
    }

    @Override
    public CommonResult checkAccessToUri(String uri, String username) {
        WxUser userByName = getUserByName(username);
        List<WxPermission> permissionsByUserId = getPermissionsByUserId(userByName.getId());
        boolean b = permissionsByUserId.stream().anyMatch(wxPermission -> StringUtils.equals(wxPermission.getUri(), uri));
        return b? CommonResult.success(true): CommonResult.forbidden(false);
    }
}