package com.wx.team.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.TeamModel;
import com.wx.team.mapper.TeamDao;
import com.wx.team.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("TeamService")
public class TeamServiceImpl implements TeamService {
    private final TeamDao dao;

    public TeamServiceImpl(TeamDao dao) {
        this.dao = dao;
    }

    @Override
    public CommonResult findAll(TeamModel teamModel) {
        //分页
        Page page= PageHelper.startPage(teamModel.getPage(), teamModel.getLimit());
        List<TeamModel> teamModels = dao.findAll(teamModel);
        PageInfo info =  new PageInfo<>(page.getResult());
        return CommonResult.success(teamModels, Math.toIntExact(info.getTotal()));
    }

    @Override
    public CommonResult del(TeamModel model) {
        int count = dao.del(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult add(TeamModel model) {
        int count = dao.add(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult findById(TeamModel teamModel) {
        TeamModel model = dao.findById(teamModel);
        return CommonResult.success(model);
    }

    @Override
    public CommonResult update(TeamModel model) {
        Date date = new Date();
        int count = dao.update(model);
        model.setUpdateTime(String.valueOf(date));
        return CommonResult.success(count);
    }

    @Override
    public CommonResult getCount(TeamModel model) {
        int count = dao.getCount(model);
        return CommonResult.success(count);
    }

    @Override
    public CommonResult enable(TeamModel teamModel) {
        int i = dao.enable (teamModel);
        return CommonResult.success (i);
    }

    @Override
    public CommonResult addText(TeamModel teamModel) {
        return CommonResult.success (dao.addText (teamModel));
    }

    @Override
    public CommonResult findTeamId(TeamModel teamModel) {
        List<TeamModel> teamModels = dao.findTeamId(teamModel);
        return CommonResult.success(teamModels);
    }
}
