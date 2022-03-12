package com.wx.product.service;


import com.wx.common.commonresult.CommonResult;
import com.wx.common.entity.ProductModel;


public interface ProductService extends BaseService<ProductModel>{


    CommonResult addText(ProductModel productModel);

    CommonResult findServiceType(ProductModel product);
}
