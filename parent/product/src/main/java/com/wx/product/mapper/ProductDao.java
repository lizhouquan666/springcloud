package com.wx.product.mapper;


import com.wx.common.entity.ProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao extends BaseDao<ProductModel> {
     int addText(ProductModel productModel);

    List<ProductModel> findServiceType(ProductModel product);
}
