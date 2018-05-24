package com.yunyihenkey.supplier.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.supplier.dao.malldb.vo.exportVo.SupplierGoodsExportParam;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.MSupplierGoodsParam;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.SupplierGoodsAddParam;

import java.util.List;

public interface SupplierGoodsInfoMapper extends SupplierGoodsInfoBaseMapper {

    int getCount(Long id);

    void updateCategory(Long id);

    List<SupplierGoodsInfo> selectAll(Long id);

    int supplierInsertGoods(SupplierGoodsAddParam goodsAddParam);

    int addGoodsToShop(Long id, Integer stock, Long version);

    List<SupplierGoodsExportParam> checkAll(Long id);

    MSupplierGoodsParam selectWithDesc(Long id);
}
