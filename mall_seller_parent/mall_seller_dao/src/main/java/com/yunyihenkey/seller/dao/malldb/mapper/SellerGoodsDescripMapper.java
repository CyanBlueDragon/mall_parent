package com.yunyihenkey.seller.dao.malldb.mapper;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;

/**
 * @desc: 商品描述mapper
 * @author: zhouh
 * @date: 2018年5月18日 下午2:16:09
 */
public interface SellerGoodsDescripMapper {

    /**
     * @param goodsId 商品id
     * @return int
     * @desc: 删除商品描述
     * @author zhouh
     * @date: 2018年5月18日 下午2:16:43
     */
    int deleteByGoodsId(Long goodsId);

    /**
     * @param sellerGoodsDescrip
     * @return int
     * @desc: 修改商品描述
     * @author zhouh
     * @date: 2018年5月22日 下午5:08:31
     */
    int updateByGoodsIdSelective(SellerGoodsDescrip sellerGoodsDescrip);

}