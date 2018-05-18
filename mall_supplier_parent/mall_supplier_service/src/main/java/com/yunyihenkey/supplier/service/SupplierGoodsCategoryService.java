package com.yunyihenkey.supplier.service;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.vo.base.BaseService;

import java.util.List;

public interface SupplierGoodsCategoryService extends BaseService<SupplierGoodsCategory, Long> {

   /*
   *@Author: tong
   *@Description:根据字段模糊查询商品分类详情（供货商）
   *@Date: 12:16 2018/5/11
   *@Param: [name]
   *@Return: java.util.List<com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory>
   **/
    List<SupplierGoodsCategory> selectByName(String name);



}
