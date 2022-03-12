package com.wx.auth.service.impl;

import com.wx.auth.entity.WxPermission;
import com.wx.auth.entity.WxUser;
import com.wx.auth.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private WxUserService wxUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WxUser user= wxUserService.getUserByName(username);
        List<WxPermission> permissionList= wxUserService.getPermissionsByUserId(user.getId());
        HashSet<WxPermission> permissions = new HashSet<>(permissionList);
        user.setAuthorities(permissions);
        return user;
    }
}