package com.wx.zuul.feign.fallback;

import com.wx.common.commonresult.CommonResult;
import com.wx.zuul.feign.SecurityFeign;
import org.springframework.stereotype.Component;

@Component
public class SecurityFallBack implements SecurityFeign {
    @Override
    public CommonResult checkAccessToUri(String uri, String username) {
        return CommonResult.failed();
    }
}