package com.wx.news.mapper;



import com.wx.common.entity.NewModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewDao extends BaseDao<NewModel> {
     int addText(NewModel newModel);

    List<NewModel> findNewId(NewModel newModel);
}
