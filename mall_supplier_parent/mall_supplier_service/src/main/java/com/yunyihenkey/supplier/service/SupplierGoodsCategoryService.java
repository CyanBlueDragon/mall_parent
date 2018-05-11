package com.yunyihenkey.supplier.service;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.vo.base.BaseService;

import java.util.List;

public interface SupplierGoodsCategoryService extends BaseService<SupplierGoodsCategory, Long> {

    /*
    *@Author: LuTong
    *@Description:查询所有商品分类（供货商）
    *@Date: 12:17 2018/5/11
    *@Param: []
    *@Return: java.util.List<com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory>
    **/
    List<SupplierGoodsCategory> selectAll();

   /*
   *@Author: tong
   *@Description:根据字段模糊查询商品分类详情（供货商）
   *@Date: 12:16 2018/5/11
   *@Param: [name]
   *@Return: java.util.List<com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory>
   **/
    List<SupplierGoodsCategory> selectByName(String name);

}
