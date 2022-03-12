package com.wx.zuul.feign;

import com.wx.common.commonresult.CommonResult;
import com.wx.zuul.feign.fallback.SecurityFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth",fallback = SecurityFallBack.class)
public interface SecurityFeign {

    @GetMapping("/user/checkAccessToUri")
    CommonResult checkAccessToUri(@RequestParam("uri") String uri, @RequestParam("username") String username);
}