package com.yunyihenkey.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.basedao.malldb.basemapper.SellerGoodsCategoryBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsCategory;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsCategoryMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerGoodsInfoMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsCategoryInfoResult;
import com.yunyihenkey.seller.service.SellerGoodsCategoryService;

/**
 * 
 * @desc: 分销商商品分类service实现类
 * @author: zhouh
 * @date: 2018年5月10日 上午9:29:00
 *
 */
@Service
public class SellerGoodsCategoryServiceImpl implements SellerGoodsCategoryService {

	@Autowired
	private SellerGoodsCategoryBaseMapper sellerGoodsCategoryBaseMapper;

	@Autowired
	private SellerGoodsCategoryMapper sellerGoodsCategoryMapper;

	@Autowired
	private SellerGoodsInfoMapper sellerGoodsInfoMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerGoodsCategoryBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerGoodsCategory record) {
		return sellerGoodsCategoryBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerGoodsCategory record) {
		LogUtils.getLogger().info("新增商品分类service.....");
		return sellerGoodsCategoryBaseMapper.insertSelective(record);
	}

	@Override
	public SellerGoodsCategory selectByPrimaryKey(Long id) {
		return sellerGoodsCategoryBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerGoodsCategory record) {
		return sellerGoodsCategoryBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerGoodsCategory record) {
		return sellerGoodsCategoryBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SellerGoodsCategoryInfoResult> selectAllByShopId(Long shopId) {
		LogUtils.getLogger().info("查询商品分类service.....");
		return sellerGoodsCategoryMapper.selectAllByShopId(shopId);
	}

	@Override
	public List<SellerGoodsCategoryInfoResult> selectByShopIdAndNameWithPage(int pageNum, int pageSize, Long shopId,
			String name) {
		LogUtils.getLogger().info("查询商品分类名称service.....");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("name", name);
		return sellerGoodsCategoryMapper.selectByShopIdAndNameWithPage(map);
	}

	@Transactional
	@Override
	public ResultInfo<Object> deleteByPrimaryKeyWithShopId(Long id, Long shopId) {
		LogUtils.getLogger().info("删除商品分类service......");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryId", id);
		map.put("shopId", shopId);

		// 验证商品分类是否存在
		LogUtils.getLogger().info("查询分类信息......");
		SellerGoodsCategoryInfoResult categoryInfo = sellerGoodsCategoryMapper.selectByIdWithShopId(map);
		if (categoryInfo == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该分类不存在！");
		}
		if (categoryInfo.getIsDefault() == 1) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "默认分类不能删除！");
		}

		// 查询默认分类
		LogUtils.getLogger().info("查询默认分类......");
		SellerGoodsCategoryInfoResult other = sellerGoodsCategoryMapper.selectIsDefaultByShopId(shopId);
		if (other == null) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "没有默认分类，不能删除！");
		}

		// 将该分类下所有商品移至默认分类
		LogUtils.getLogger().info("将该分类下所有商品移至默认分类......");
		map.put("otherCategoryId", other.getId());
		sellerGoodsInfoMapper.updateByCategoryId(map);

		// 删除商品分类
		LogUtils.getLogger().info("删除商品分类......");
		sellerGoodsCategoryMapper.deleteByPrimaryKeyWithShopId(map);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public ResultInfo<Object> updateByPrimaryKeyWithShopId(Long id, Long shopId, String userName, String name,
			Integer sortOrder) {
		LogUtils.getLogger().info("修改商品分类service......");

		SellerGoodsCategory sellerGoodsCategory = new SellerGoodsCategory();
		sellerGoodsCategory.setId(id);
		sellerGoodsCategory.setName(name);
		sellerGoodsCategory.setSortOrder(sortOrder);
		sellerGoodsCategory.setUpdateUser(userName);
		sellerGoodsCategory.setUpdateTime(DateUtil.getCurrentDate());
		sellerGoodsCategory.setShopId(shopId);
		sellerGoodsCategoryMapper.updateByPrimaryKeyWithShopId(sellerGoodsCategory);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	@Override
	public Object selectAllWithGoodsNumByShopId(Integer pageNum, Integer pageSize, Long shopId) {

		LogUtils.getLogger().info("关联查询商品分类service......");

		PageHelper.startPage(pageNum, pageSize);
		List<SellerGoodsCategoryInfoResult> categoryList = sellerGoodsCategoryMapper
				.selectAllWithGoodsNumByShopId(shopId);
		// 使用pagehelper的分页对象进行包装
		PageInfo<SellerGoodsCategoryInfoResult> pageInfo = new PageInfo<SellerGoodsCategoryInfoResult>(categoryList);
		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
	}

}
