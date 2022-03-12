package com.wx.team.mapper;



import com.wx.common.entity.TeamModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamDao extends BaseDao<TeamModel> {
     int addText(TeamModel teamModel);

    List<TeamModel> findTeamId(TeamModel team);
}
