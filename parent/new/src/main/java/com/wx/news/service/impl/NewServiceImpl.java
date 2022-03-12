package com.wx.news.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.NewModel;
import com.wx.news.mapper.NewDao;
import com.wx.news.service.NewService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("NewService")
public class NewServiceImpl implements NewService {
    private final NewDao newdao;

    public NewServiceImpl(NewDao dao) {
        this.newdao = dao;
    }

    @Override
    public CommonResult findAll(NewModel newModel) {
        //分页
        Page page= PageHelper.startPage(newModel.getPage(), newModel.getLimit());
        List<NewModel> newModels = newdao.findAll(newModel);
        PageInfo info =  new PageInfo<>(page.getResult());
        return CommonResult.success(newModels, Math.toIntExact(info.getTotal()));
//        List<NewModel> newModels = newdao.findAll(newModel);
//        return CommonResult.success(newModels);
    }

    @Override
    public CommonResult del(NewModel model) {
        int count = newdao.del(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult add(NewModel model) {
        int count = newdao.add(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult findById(NewModel newModel) {
        NewModel model = newdao.findById(newModel);
        return CommonResult.success(model);
    }

    @Override
    public CommonResult update(NewModel model) {
        Date date = new Date();
        int count = newdao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return CommonResult.success(count);
    }

    @Override
    public CommonResult getCount(NewModel model) {
        int count = newdao.getCount(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult enable(NewModel newModel) {
        int i = newdao.enable (newModel);
        return CommonResult.success (i);
    }

    @Override
    public CommonResult addText(NewModel newModel) {
        return CommonResult.success (newdao.addText (newModel));
    }

    @Override
    public CommonResult findNewId(NewModel newModel) {
        List<NewModel> newModels = newdao.findNewId(newModel);
        return CommonResult.success(newModels);
    }
}
