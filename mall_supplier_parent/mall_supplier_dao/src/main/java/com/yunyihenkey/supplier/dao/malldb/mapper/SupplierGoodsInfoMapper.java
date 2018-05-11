package com.yunyihenkey.supplier.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsInfoBaseMapper;

public interface SupplierGoodsInfoMapper extends SupplierGoodsInfoBaseMapper {

    /*
    *@Author: LuTong
    *@Description: 根据category的id查询商品表中该分类的数目
    *@Date: 14:52 2018/5/11
    *@Param: [id]
    *@Return: int
    **/
    int getCount(Long id);

}
