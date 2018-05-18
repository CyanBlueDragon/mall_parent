package com.yunyihenkey.seller.service;

import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsCategory;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsCategoryInfoResult;

/**
 * 
 * @desc: 分销商商品分类service
 * @author: zhouh
 * @date: 2018年5月10日 上午9:24:31
 *
 */
public interface SellerGoodsCategoryService extends BaseService<SellerGoodsCategory, Long> {

	/**
	 * 
	 * @desc: 根据店铺id查询所有商品分类
	 * @author zhouh
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示数量
	 * @param shopId
	 *            店铺id
	 * @return List<SellerGoodsCategory>
	 * @date: 2018年5月11日 下午6:08:15
	 */
	List<SellerGoodsCategoryInfoResult> selectAllByShopId(Long shopId);
	
	/**
	 * 
	 * @desc: 关联查询商品分类
	 * @author zhouh
	 * @param shopId
	 * @return List<SellerGoodsCategoryInfoResult>
	 * @date: 2018年5月17日 上午11:13:02
	 */
	Object selectAllWithGoodsNumByShopId(Integer pageNum, Integer pageSize, Long shopId);

	/**
	 * 
	 * @desc:查询商品分类名称
	 * @author zhouh
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示数量
	 * @param shopId
	 *            店铺id
	 * @param name
	 *            分类名称
	 * @return List<SellerGoodsCategoryInfoVo>
	 * @date: 2018年5月14日 下午3:12:56
	 */
	List<SellerGoodsCategoryInfoResult> selectByShopIdAndNameWithPage(int pageNum, int pageSize, Long shopId, String name);

	/**
	 * 
	 * @desc: 删除商品分类
	 * @author zhouh
	 * @param id
	 *            分类id
	 * @param shopId
	 *            店铺id
	 * @return ResultInfo<Object>
	 * @date: 2018年5月14日 下午3:34:50
	 */
	ResultInfo<Object> deleteByPrimaryKeyWithShopId(Long id, Long shopId);

	/**
	 * 
	 * @desc: 修改商品分类
	 * @author zhouh
	 * @param name
	 * @param sortOrder
	 * @return ResultInfo<Object>
	 * @date: 2018年5月14日 下午4:30:52
	 */
	ResultInfo<Object> updateByPrimaryKeyWithShopId(Long id, Long shopId, String userName, String name,
			Integer sortOrder);

}