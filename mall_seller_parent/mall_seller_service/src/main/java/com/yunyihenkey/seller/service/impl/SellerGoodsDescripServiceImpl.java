package com.yunyihenkey.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerGoodsDescripBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsDescripMapper;
import com.yunyihenkey.seller.service.SellerGoodsDescripService;

/**
 * 
 * @desc: 分销商商品描述service实现类
 * @author: zhouh
 * @date: 2018年5月10日 上午9:29:56
 *
 */
@Service
public class SellerGoodsDescripServiceImpl implements SellerGoodsDescripService {

    @Autowired
    private SellerGoodsDescripBaseMapper sellerGoodsDescripBaseMapper;

    @Autowired
    private SellerGoodsDescripMapper sellerGoodsDescripMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
        return sellerGoodsDescripBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerGoodsDescrip record) {
        return sellerGoodsDescripBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerGoodsDescrip record) {
        LogUtils.getLogger().info("新增商品描述.....");
        return sellerGoodsDescripBaseMapper.insertSelective(record);
	}

	@Override
	public SellerGoodsDescrip selectByPrimaryKey(Long id) {
        return sellerGoodsDescripBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerGoodsDescrip record) {
        return sellerGoodsDescripBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerGoodsDescrip record) {
        return sellerGoodsDescripBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(SellerGoodsDescrip record) {
        return sellerGoodsDescripBaseMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Object deleteByGoodsId(Long goodsId) {
        LogUtils.getLogger().info("删除商品描述.....");
        sellerGoodsDescripMapper.deleteByGoodsId(goodsId);
        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

}
