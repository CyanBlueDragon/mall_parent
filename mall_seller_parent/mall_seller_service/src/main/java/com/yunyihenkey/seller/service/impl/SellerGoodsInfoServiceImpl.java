package com.yunyihenkey.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerGoodsDescripBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerGoodsInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsDescripBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SupplierGoodsInfoBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsInfo;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.httpclient.invoke.HttpClientUtil;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsCategoryMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsInfoMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.AddGoodsParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsCategoryInfoResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsInfoResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.UpdGoodsParam;
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
	private SellerGoodsDescripBaseMapper sellerGoodsDescripBaseMapper;

	@Autowired
	private SupplierGoodsInfoBaseMapper supplierGoodsInfoBaseMapper;

	@Autowired
	private SupplierGoodsDescripBaseMapper supplierGoodsDescripBaseMapper;

	// 减供应商库存接口
	@Value("${url.supplier_inventory_reduction_url}")
	private String supplier_inventory_reduction_url;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerGoodsInfoBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerGoodsInfo record) {
		return sellerGoodsInfoBaseMapper.insert(record);
	}

	@Transactional
	@Override
	public Object insertSelective(String userName, Long shopId, AddGoodsParam record) {
		LogUtils.getLogger().info("新增商品service......");

		// 验证商品分类是否存在
		if (this.selectByIdWithShopId(shopId, record.getCategoryId()) == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该分类不存在！");
		}

		// 获取供应商上传的商品信息
		SupplierGoodsInfo supplierGoodsInfo = supplierGoodsInfoBaseMapper.selectByPrimaryKey(record.getGoodsId());

		// 商品总库存
		int totalStock = supplierGoodsInfo.getStock();
		LogUtils.getLogger().info(LogUtils.getString("当前库存数量=", totalStock));

		// 商品数量不能超过库存
		if (record.getStock() > totalStock) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "库存不足！");
		}
		// 验证商品售价是否超过建议价格
		if (record.getPrice() > supplierGoodsInfo.getMaxRetailPrice()) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
					LogUtils.getString("商品价格不能高于", supplierGoodsInfo.getMaxRetailPrice(), "！"));
		}
		if (record.getPrice() < supplierGoodsInfo.getMinRetailPrice()) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
					LogUtils.getString("商品价格不能底于", supplierGoodsInfo.getMinRetailPrice(), "！"));
		}

		// 限制添加商品数量
		LogUtils.getLogger().info("查询商品信息......");
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("goodsId", record.getGoodsId());
		goodsMap.put("shopId", shopId);
		SellerGoodsInfoResult goodsInfo = sellerGoodsInfoMapper.selectBySupplierGoodsIdWithShopId(goodsMap);
		SellerGoodsInfo sellerGoodsInfo = new SellerGoodsInfo();
		// 如果商品不存在则新增
		if (goodsInfo == null) {

			// 添加商品
			LogUtils.getLogger().info("新增商品......");
			sellerGoodsInfo.setCategoryId(record.getCategoryId());
			sellerGoodsInfo.setDeliveryTemplateName(record.getDeliveryTemplateName());
			sellerGoodsInfo.setGoodsCode(supplierGoodsInfo.getGoodsCode());
			sellerGoodsInfo.setGoodsId(record.getGoodsId());
			sellerGoodsInfo.setGoodsTitle(supplierGoodsInfo.getGoodsName());
			sellerGoodsInfo.setPicUrl(supplierGoodsInfo.getPicUrl());
			sellerGoodsInfo.setPrice(record.getPrice());
			sellerGoodsInfo.setStatus(record.getStatus());
			sellerGoodsInfo.setStock(record.getStock());
			sellerGoodsInfo.setSupplyPrice(supplierGoodsInfo.getSupplyPrice());
			sellerGoodsInfo.setCreateTime(DateUtil.getCurrentDate());
			sellerGoodsInfo.setCreateUser(userName);
			sellerGoodsInfo.setShopId(shopId);
			int addResult = sellerGoodsInfoMapper.insertSelective(sellerGoodsInfo);
			if (addResult < 1) {
				return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "新增失败！");
			}

			// 获取商品描述信息
			LogUtils.getLogger().info(LogUtils.getString("查询供应商商品描述......"));
			SupplierGoodsDescrip supplierGoodsDescrip = supplierGoodsDescripBaseMapper
					.selectByPrimaryKey(record.getGoodsId());

			// 添加商品描述
			LogUtils.getLogger().info(LogUtils.getString("新增商品描述...... 商品id=", sellerGoodsInfo.getId()));
			SellerGoodsDescrip sellerGoodsDescrip = new SellerGoodsDescrip();
			sellerGoodsDescrip.setId(sellerGoodsInfo.getId());
			sellerGoodsDescrip.setDescription(supplierGoodsDescrip.getDescription());
			sellerGoodsDescrip.setCreateUser(userName);
			sellerGoodsDescrip.setCreateTime(DateUtil.getCurrentDate());
			sellerGoodsDescripBaseMapper.insertSelective(sellerGoodsDescrip);

		} else {

			// 只能添加三次商品库存
			if (goodsInfo.getAddCount() > 2) {
				return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "添加商品次数超过上限！");
			}

			LogUtils.getLogger().info("修改商品库存信息......");
			// 修改商品信息
			sellerGoodsInfo.setId(goodsInfo.getId());
			sellerGoodsInfo.setShopId(shopId);
			sellerGoodsInfo.setCategoryId(record.getCategoryId());
			sellerGoodsInfo.setPrice(record.getPrice());
			sellerGoodsInfo.setStock(goodsInfo.getStock() + record.getStock());
			sellerGoodsInfo.setDeliveryTemplateName(record.getDeliveryTemplateName());
			sellerGoodsInfo.setUpdateUser(userName);
			sellerGoodsInfo.setUpdateTime(DateUtil.getCurrentDate());
			sellerGoodsInfo.setAddCount(goodsInfo.getAddCount() + 1);
			sellerGoodsInfoMapper.updateByPrimaryKeyWithShopIdSelective(sellerGoodsInfo);
		}

		// 减库存
		LogUtils.getLogger().info("调用供应商减库存接口......");
		String stockResult = HttpClientUtil.getInstance().sendHttpPost(supplier_inventory_reduction_url,
				LogUtils.getString("id=", record.getGoodsId(), "&stock=", totalStock - record.getStock(), "&version=",
						supplierGoodsInfo.getVersion()));
		if (!"success".equals(stockResult)) {
			throw new RuntimeException("减库存失败！");
		}

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
		LogUtils.getLogger().info("分页查询商品详情service......");
		PageHelper.startPage(pageNum, pageSize);
		return sellerGoodsInfoMapper.selectByShopIdWithPage(shopId);
	}

	@Override
	public ResultInfo<Object> updateStatus(Long[] ids, Integer status, Long shopId) {

		LogUtils.getLogger().info("批量修改商品状态service......");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("status", status);
		map.put("shopId", shopId);
		sellerGoodsInfoMapper.updateStatus(map);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public ResultInfo<Object> updateCategory(Long[] ids, Long categoryId, Long shopId) {

		LogUtils.getLogger().info("批量修改商品分类service......");

		// 验证商品分类是否存在
		if (this.selectByIdWithShopId(shopId, categoryId) == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该分类不存在！");
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
	private SellerGoodsCategoryInfoResult selectByIdWithShopId(Long shopId, Long categoryId) {
		LogUtils.getLogger().info("查询商品分类service......");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", categoryId);
		map.put("shopId", shopId);
		return sellerGoodsCategoryMapper.selectByIdWithShopId(map);
	}

	@Override
	public List<SellerGoodsInfoResult> selectByCondition(Long categoryId, Long shopId, String goodsTitle,
			Integer goodsStatus, Integer pageNum, Integer pageSize) {
		LogUtils.getLogger().info("条件查询商品信息service......");

		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		map.put("shopId", shopId);
		map.put("goodsTitle", goodsTitle);
		map.put("goodsStatus", goodsStatus);

		return sellerGoodsInfoMapper.selectByCondition(map);
	}

	@Transactional
	@Override
	public ResultInfo<Object> deleteByPrimaryKeyWithShopId(Long id, Long shopId) {
		LogUtils.getLogger().info("删除商品service......");

		// 删除商品
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shopId", shopId);
		int delResult = sellerGoodsInfoMapper.deleteByPrimaryKeyWithShopId(map);
		if (delResult == 0) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "该商品不存在！");
		}

		// 删除商品描述
		LogUtils.getLogger().info("删除商品描述......");
		sellerGoodsDescripBaseMapper.deleteByPrimaryKey(id);

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public int insertSelective(SellerGoodsInfo record) {
		return sellerGoodsInfoBaseMapper.insert(record);
	}

	@Transactional
	@Override
	public Object updateByPrimaryKeyWithShopIdSelective(String userName, Long shopId, UpdGoodsParam record) {
		LogUtils.getLogger().info("修改商品service......");

		// 验证商品分类是否存在
		if (this.selectByIdWithShopId(shopId, record.getCategoryId()) == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该分类不存在！");
		}

		// 查询供货商商品id
		LogUtils.getLogger().info("查询供货商商品id......");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", record.getId());
		map.put("shopId", shopId);
		SellerGoodsInfoResult goods_info = sellerGoodsInfoMapper.selectByPrimaryKeyWithShopId(map);
		if (goods_info == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该商品不存在！");
		}

		// 查询供应商上传的商品信息
		LogUtils.getLogger().info("查询供货商商品信息......");
		SupplierGoodsInfo supp = supplierGoodsInfoBaseMapper.selectByPrimaryKey(goods_info.getGoodsId());
		if (supp == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该商品供应商已下架！");
		}
		// 验证商品售价是否超过建议价格
		if (record.getPrice() > supp.getMaxRetailPrice()) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
					LogUtils.getString("商品价格不能高于", supp.getMaxRetailPrice(), "！"));
		}
		if (record.getPrice() < supp.getMinRetailPrice()) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR,
					LogUtils.getString("商品价格不能底于", supp.getMinRetailPrice(), "！"));
		}

		// 修改商品
		LogUtils.getLogger().info("修改商品......");
		SellerGoodsInfo sellerGoodsInfo = new SellerGoodsInfo();
		sellerGoodsInfo.setId(record.getId());
		sellerGoodsInfo.setShopId(shopId);
		sellerGoodsInfo.setPicUrl(record.getPicUrl());
		sellerGoodsInfo.setGoodsTitle(record.getGoodsTitle());
		sellerGoodsInfo.setCategoryId(record.getCategoryId());
		sellerGoodsInfo.setPrice(record.getPrice());
		sellerGoodsInfo.setDeliveryTemplateName(record.getDeliveryTemplateName());
		sellerGoodsInfo.setUpdateUser(userName);
		sellerGoodsInfo.setUpdateTime(DateUtil.getCurrentDate());
		int updGoods = sellerGoodsInfoMapper.updateByPrimaryKeyWithShopIdSelective(sellerGoodsInfo);
		if (updGoods == 0) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "该商品不存在！");
		}

		// 修改商品详情
		LogUtils.getLogger().info("修改商品详情......");
		SellerGoodsDescrip descrip = new SellerGoodsDescrip();
		descrip.setId(record.getId());
		descrip.setDescription(record.getDescription());
		descrip.setUpdateUser(userName);
		descrip.setUpdateTime(DateUtil.getCurrentDate());
		sellerGoodsDescripBaseMapper.updateByPrimaryKeySelective(descrip);

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

}
