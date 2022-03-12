package com.wx.auth.dao;

import com.wx.auth.entity.WxPermission;
import com.wx.auth.entity.WxUser;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface WxUserDao {
    List<WxUser> getUserByName(String name);

    List<WxPermission> getPermissionsByUserId(Integer userId);
}