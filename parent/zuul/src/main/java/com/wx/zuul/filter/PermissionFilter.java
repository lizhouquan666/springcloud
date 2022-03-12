package com.wx.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wx.common.commonresult.CommonResult;
import com.wx.zuul.feign.SecurityFeign;
import com.wx.zuul.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Component
public class PermissionFilter extends ZuulFilter {

    @Value("#{'${pathList}'.split(',')}")
    private List<String> pathList;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityFeign securityFeign;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        HttpServletRequest request = requestContext.getRequest();
        String uri = request.getRequestURI();


        //那些路径可以直接放行
        boolean a = pathList.stream().anyMatch(path -> StringUtils.contains(uri, path));
        if (a) {
            return null;

        }
        response.setContentType("application/json;charset=UTF-8");
//        PathMatcher

        String authorization = request.getHeader("Authorization"); // bearer jwtToken
        if (StringUtils.isBlank(authorization)) {
//            requestContext.setResponseBody("未获取到token");
            requestContext.setResponseBody(JSONObject.toJSONString(CommonResult.forbidden("未获取到token")));
            requestContext.setSendZuulResponse(false);
            return null;

        }
        String token = StringUtils.substring(authorization, "bearer".length()).trim();

        // todo  此处需要你们去完善这个功能 因为这个校验有个前提  就是所有的uri 都需要存入数据库中
        // todo 应该先判断用户正在访问的uri是否权限表中， 如果不在权限表中,只要token有效,就直接给该用户 放行
        // todo  如果在权限表中  就看当前用户的权限列表 Set<String> uriSet中是否包含正在访问的这个uri
        // 验证权限第一种方法：本地验证(简单的认证)
//        checkLocal(token,requestContext,uri);

        // 第二种方法：远程调用验证(复杂的认证)
        checkLongRange(token, requestContext, uri);

        return null;
    }

    // 验证权限第一种方法：本地验证
    private Object checkLocal(String token, RequestContext requestContext, String uri) {
        Set<String> auths = null; // 就是用户拥有的 uriList

        try {
            auths = jwtTokenUtil.getAuthsFromToken(token);
        } catch (Exception e) {
            // 处理token过期
            if (e instanceof ExpiredJwtException) {
                requestContext.setResponseBody("token 过期");
                requestContext.setSendZuulResponse(false);
                return null;
            }
            e.printStackTrace();
        }
        //验证权限
        boolean b = auths.stream().anyMatch(auth -> StringUtils.equals(auth, uri));
        if (false) {
            requestContext.setResponseBody("您没有权限");
            requestContext.setSendZuulResponse(false);
            return null;
        }
        return null;
    }

    // 第二种方法：远程调用验证(复杂的认证)
    private Object checkLongRange(String token, RequestContext requestContext, String uri) {
        try {
            boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
            if (tokenExpired) {
                requestContext.setResponseBody("token 过期");
                requestContext.setSendZuulResponse(false);
                return null;
            }
        } catch (Exception e) {
            // 处理token过期
            if (e instanceof ExpiredJwtException) {
                requestContext.setResponseBody("token 过期");
                requestContext.setSendZuulResponse(false);
                return null;
            }
            e.printStackTrace();
        }

        String url = uri.replaceAll("/api/./","/");

        CommonResult commonResult = securityFeign.checkAccessToUri(url, jwtTokenUtil.getUserNameFromToken(token));
        if (commonResult.getCode() == 200) {
            return null;
        } else if (commonResult.getCode() == 0) {
            return null;
        } else if (commonResult.getCode() == 403) {
            requestContext.setResponseBody(JSONObject.toJSONString(CommonResult.forbidden(null)));
            requestContext.setSendZuulResponse(false);
            return null;
        } else if (commonResult.getCode() == 500) {
            requestContext.setResponseBody(JSONObject.toJSONString(CommonResult.failed("未知异常")));
            requestContext.setSendZuulResponse(false);
            return null;

        } else {
            requestContext.setResponseBody("未知的远程调用状态码");
            requestContext.setSendZuulResponse(false);
            return null;
        }
    }

}