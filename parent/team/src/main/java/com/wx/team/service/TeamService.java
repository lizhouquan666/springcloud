package com.wx.team.service;


import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.TeamModel;

public interface TeamService extends BaseService<TeamModel>{


    CommonResult addText(TeamModel teamModel);

    CommonResult findTeamId(TeamModel team);
}
