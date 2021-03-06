package com.wx.news.controller;


import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.NewModel;
import com.wx.news.service.NewService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

//String[] allowedHeaders:
//        跨域请求中允许的请求头中的字段类型，
//        该值对应跨域预请求 Response 头中的 'Access-Control-Allow-Headers' 字段值。
//        不设置确切值默认支持所有的header字段
//        （Cache-Controller、Content-Language、Content-Type、Expires、Last-Modified、Pragma）跨域访问。
//String allowCredentials:
//        该值对应的是是跨域请求 Response 头中的 'Access-Control-Allow-Credentials' 字段值。
//        浏览器是否将本域名下的 cookie 信息携带至跨域服务器中。
//        默认携带至跨域服务器中，但要实现 cookie 共享还需要前端在 AJAX 请求中打开 withCredentials 属性。
//https://zhuanlan.zhihu.com/p/66789473
//@CrossOrigin(allowCredentials="true", allowedHeaders="*")

@RestController
@RequestMapping("news")
public class NewController {

    private NewService newService;

    private Jedis jedis = new Jedis();

    private NewController(NewService service) {
        this.newService = service;
    }

    @RequestMapping("findAll")
    public CommonResult findAll(NewModel model) {
        int count;
        CommonResult resultModel;
        //判断jedis中的count是否存在 减少sql查询
        if (jedis.get("count") == null) {
            count = newService.getCount(model).getCount();
            jedis.set("count", String.valueOf(count), "XX", "EX", 600);
        } else {
            count = Integer.valueOf(jedis.get("count"));
        }

        return newService.findAll(model);
    }


    @RequestMapping("enable")
    public CommonResult enable(NewModel model) {
        CommonResult commonResult = newService.enable(model);
        return commonResult;
    }

    @RequestMapping("findById")
    public CommonResult findById(NewModel model) {
        CommonResult commonResult = newService.findById(model);
        return commonResult;
    }

    @RequestMapping("delete")
    public CommonResult delete(NewModel model) {
        CommonResult commonResult = newService.del(model);
        return commonResult;
    }

    @RequestMapping("add")
    public CommonResult add(NewModel model) {
        int count;
        count = newService.getCount(model).getCount();
        jedis.set("count", String.valueOf(count));
        jedis.expire("count", 600);
        CommonResult commonResult = newService.add(model);
        return commonResult;
    }

    @RequestMapping("update")
    public CommonResult edit(NewModel model) {
        CommonResult commonResult = newService.update(model);
        return commonResult;
    }

    @RequestMapping("findNewId")
    public CommonResult findNewId(NewModel model) {
        CommonResult commonResult = newService.findNewId(model);
        return commonResult;
    }
}
