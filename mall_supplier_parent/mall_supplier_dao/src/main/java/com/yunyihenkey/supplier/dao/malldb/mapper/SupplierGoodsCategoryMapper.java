package com.yunyihenkey.supplier.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsCategoryBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;

import java.util.List;


public interface SupplierGoodsCategoryMapper extends SupplierGoodsCategoryBaseMapper {


    /*
    *@Author: tong
    *@Description: 根据分类名称查询详情
    *@Date: 11:44 2018/5/11
    *@Param: String(name)
    *@Return: com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory
    **/
    List<SupplierGoodsCategory> selectByName(String name);
}
