package com.yunyihenkey.supplier.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsDescripBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip;

public interface SupplierGoodsDescripMapper extends SupplierGoodsDescripBaseMapper {

    /*
    *@Author: LuTong
    *@Description: 根据商品id查询商品描述
    *@Date: 11:26 2018/5/14
    *@Param: [id]
    *@Return: com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip
    **/
    SupplierGoodsDescrip selectByGoodsId(Long id);
}
