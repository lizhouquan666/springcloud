package com.wx.news.service;


import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.NewModel;

public interface NewService extends BaseService<NewModel>{


    CommonResult addText(NewModel newModel);

    CommonResult findNewId(NewModel newModel);
}
