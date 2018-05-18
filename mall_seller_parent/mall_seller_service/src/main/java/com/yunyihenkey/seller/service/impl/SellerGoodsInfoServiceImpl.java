package com.yunyihenkey.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerGoodsDescripBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerGoodsInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsInfo;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsCategoryMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsInfoMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.AddGoodsParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsInfoResult;
import com.yunyihenkey.seller.service.SellerGoodsInfoService;

/**
 * 
 * @desc: 分销商商品表service实现类
 * @author: zhouh
 * @date: 2018年5月10日 上午9:26:31
 *
 */
@Service
public class SellerGoodsInfoServiceImpl implements SellerGoodsInfoService {

	@Autowired
	private SellerGoodsInfoBaseMapper sellerGoodsInfoBaseMapper;

	@Autowired
	private SellerGoodsInfoMapper sellerGoodsInfoMapper;

	@Autowired
	private SellerGoodsCategoryMapper sellerGoodsCategoryMapper;

	@Autowired
	private SupplierGoodsInfoBaseMapper supplierGoodsInfoBaseMapper;

	@Autowired
	private SellerGoodsDescripBaseMapper sellerGoodsDescripBaseMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerGoodsInfoBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerGoodsInfo record) {
		return sellerGoodsInfoBaseMapper.insert(record);
	}

	@Override
	public Object insertSelective(String userName, Long shopId, AddGoodsParam record) {
		LogUtils.getLogger().info("新增商品......");

		// 验证商品分类是否存在
		ResultInfo<Object> check = this.checkCategory(shopId, record.getCategoryId());
		if (CodeEnum.ERROR.getValue() == check.getStatusCode()) {
			return check;
		}

		// 供应商上传的商品信息
		SupplierGoodsInfo supp = supplierGoodsInfoBaseMapper.selectByPrimaryKey(record.getGoodsId());
		// 验证商品售价是否超过建议价格
		if (supp.getMaxRetailPrice() < record.getPrice()) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
					LogUtils.getString("商品价格不能高于", supp.getMaxRetailPrice(), "！"));
		}
		if (supp.getMinRetailPrice() > record.getPrice()) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
					LogUtils.getString("商品价格不能高于", supp.getMinRetailPrice(), "！"));
		}

		System.out.println("商品库存=" + supp.getStock());

		// 限制添加商品数量
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("goodsId", record.getGoodsId());
		goodsMap.put("shopId", shopId);
		SellerGoodsInfoResult goodsInfo = sellerGoodsInfoMapper.selectBySupplierGoodsIdWithShopId(goodsMap);
		System.out.println("供应商商品信息=" + goodsInfo.toString());
		SellerGoodsInfo sellerGoodsInfo = new SellerGoodsInfo();
		if (true) {
			return "测试完毕！";
		}

		int totalStock = supp.getStock(); // 供应商总库存
		// 如果商品存在则修改商品库存等信息
		if (goodsInfo != null) {

			int goodsStock = goodsInfo.getStock(); // 分销商现有库存
			int addCount = goodsInfo.getAddCount(); // 添加次数

			if (addCount > 2) {
				// 只能添加三次商品
				return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "添加商品次数超过上限！");
			}
			if (record.getStock() > totalStock) {
				// 只能添加三次商品
				return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "库存不足！");
			}

			// 修改商品信息
			sellerGoodsInfo.setId(goodsInfo.getId());
			sellerGoodsInfo.setShopId(shopId);
			sellerGoodsInfo.setCategoryId(record.getCategoryId());
			sellerGoodsInfo.setPrice(record.getPrice());
			sellerGoodsInfo.setStock(goodsStock + record.getStock());
			sellerGoodsInfo.setDeliveryTemplateName(record.getDeliveryTemplateName());
			sellerGoodsInfo.setUpdateUser(userName);
			sellerGoodsInfo.setUpdateTime(DateUtil.getCurrentDate());
			sellerGoodsInfo.setAddCount(addCount++);
			sellerGoodsInfoMapper.updateByPrimaryKeyWithShopIdSelective(sellerGoodsInfo);

		}

		// 添加商品
		sellerGoodsInfo.setCategoryId(record.getCategoryId());
		sellerGoodsInfo.setDeliveryTemplateName(record.getDeliveryTemplateName());
		sellerGoodsInfo.setGoodsCode(record.getGoodsCode());
		sellerGoodsInfo.setGoodsId(record.getGoodsId());
		sellerGoodsInfo.setGoodsTitle(record.getGoodsTitle());
		sellerGoodsInfo.setPicUrl(record.getPicUrl());
		sellerGoodsInfo.setPrice(record.getPrice());
		sellerGoodsInfo.setStatus(record.getStatus());
		sellerGoodsInfo.setStock(record.getStock());
		sellerGoodsInfo.setSupplyPrice(record.getSupplyPrice());
		sellerGoodsInfo.setCreateTime(DateUtil.getCurrentDate());
		sellerGoodsInfo.setCreateUser(userName);
		sellerGoodsInfo.setShopId(shopId);
		int result = sellerGoodsInfoMapper.insertSelective(sellerGoodsInfo);
		if (result < 1) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "新增失败！");
		}

		System.out.println(" 新增的主键=" + sellerGoodsInfo.getId());
		// 添加商品描述
		SellerGoodsDescrip sellerGoodsDescrip = new SellerGoodsDescrip();
		sellerGoodsDescrip.setGoodsId(sellerGoodsInfo.getId());
		sellerGoodsDescrip.setDescription(record.getDescription());
		sellerGoodsDescrip.setCreateUser(userName);
		sellerGoodsDescrip.setCreateTime(DateUtil.getCurrentDate());
		// sellerGoodsDescripBaseMapper.insertSelective(sellerGoodsDescrip);

		// 减库存

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public SellerGoodsInfo selectByPrimaryKey(Long id) {
		return sellerGoodsInfoBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerGoodsInfo record) {
		return sellerGoodsInfoBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerGoodsInfo record) {
		return sellerGoodsInfoBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SellerGoodsInfoResult> selectByShopIdWithPage(int pageNum, int pageSize, Long shopId) {
		LogUtils.getLogger().info("分页查询商品详情......");
		PageHelper.startPage(pageNum, pageSize);
		return sellerGoodsInfoMapper.selectByShopIdWithPage(shopId);
	}

	@Override
	public ResultInfo<Object> updateStatus(Long[] ids, Integer status, Long shopId) {

		LogUtils.getLogger().info("批量修改商品状态......");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("status", status);
		map.put("shopId", shopId);
		sellerGoodsInfoMapper.updateStatus(map);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public ResultInfo<Object> updateCategory(Long[] ids, Long categoryId, Long shopId) {

		LogUtils.getLogger().info("批量修改商品分类......");

		// 验证商品分类是否存在
		ResultInfo<Object> check = this.checkCategory(shopId, categoryId);
		if (CodeEnum.ERROR.getValue() == check.getStatusCode()) {
			return check;
		}

		// 修改商品状态
		Map<String, Object> hasMap = new HashMap<String, Object>();
		hasMap.put("ids", ids);
		hasMap.put("categoryId", categoryId);
		hasMap.put("shopId", shopId);
		sellerGoodsInfoMapper.updateCategory(hasMap);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	/**
	 * 
	 * @desc: 验证商品分类是否存在
	 * @author zhouh
	 * @param shopId
	 *            店铺id
	 * @param categoryId
	 *            商品分类id
	 * @return Object
	 * @date: 2018年5月17日 下午4:53:10
	 */
	private ResultInfo<Object> checkCategory(Long shopId, Long categoryId) {
		LogUtils.getLogger().info("检查商品分类是否存在......");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", categoryId);
		map.put("shopId", shopId);
		int count = sellerGoodsCategoryMapper.selectByIdWithShopId(map);
		if (count != 1) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该分类不存在！");
		}
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public List<SellerGoodsInfoResult> selectByCondition(Long categoryId, Long shopId, String goodsTitle,
			Integer goodsStatus, Integer pageNum, Integer pageSize) {
		LogUtils.getLogger().info("条件查询商品信息......");

		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		map.put("shopId", shopId);
		map.put("goodsTitle", goodsTitle);
		map.put("goodsStatus", goodsStatus);

		return sellerGoodsInfoMapper.selectByCondition(map);
	}

	@Override
	public ResultInfo<Object> deleteByPrimaryKeyWithShopId(Long id, Long shopId) {
		LogUtils.getLogger().info("删除商品......");
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", id);
		map.put("shopId", shopId);
		sellerGoodsInfoMapper.deleteByPrimaryKeyWithShopId(map);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public int insertSelective(SellerGoodsInfo record) {
		return sellerGoodsInfoBaseMapper.insert(record);
	}

}
