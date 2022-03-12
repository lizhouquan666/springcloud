package com.wx.es.mapper;

import com.wx.es.entity.UserEs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository extends ElasticsearchRepository<UserEs,Integer> {

}
