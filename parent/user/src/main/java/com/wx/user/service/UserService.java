package com.wx.user.service;


import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.User;
import org.springframework.stereotype.Service;

public interface UserService extends BaseService<User>{

    CommonResult<String> login(User user);
    CommonResult enable(User user);

    CommonResult addText(User user);
}
