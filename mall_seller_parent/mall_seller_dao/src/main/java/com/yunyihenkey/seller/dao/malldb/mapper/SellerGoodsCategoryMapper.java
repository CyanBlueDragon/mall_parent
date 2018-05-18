package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;
import java.util.Map;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsCategory;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsCategoryInfoResult;

/**
 * 
 * @desc: 分销商商品分类mapper
 * @author: zhouh
 * @date: 2018年5月11日 上午9:27:52
 *
 */
public interface SellerGoodsCategoryMapper {
	/**
	 * 
	 * @desc: 根据店铺id分页查询分类
	 * @author zhouh
	 * @param shopId
	 * @return List<SellerGoodsCategory>
	 * @date: 2018年5月11日 下午6:07:16
	 */
	List<SellerGoodsCategoryInfoResult> selectAllByShopId(Long shopId);

	/**
	 * 
	 * @desc: 关联查询商品分类
	 * @author zhouh
	 * @param shopId
	 * @return List<SellerGoodsCategoryInfoResult>
	 * @date: 2018年5月17日 上午11:15:13
	 */
	List<SellerGoodsCategoryInfoResult> selectAllWithGoodsNumByShopId(Long shopId);

	/**
	 * 
	 * @desc: 根据分类id和店铺id查询分类是否存在
	 * @author zhouh
	 * @param map
	 *            传入分类id、店铺id
	 * @return int 返回1表示该分类存在
	 * @date: 2018年5月12日 下午3:53:39
	 */
	int selectByIdWithShopId(Map<String, Object> map);

	/**
	 * 
	 * @desc:
	 * @author zhouh
	 * @param map
	 *            传入分类名称、店铺id
	 * @return List<SellerGoodsCategoryInfoVo>
	 * @date: 2018年5月14日 下午3:08:27
	 */
	List<SellerGoodsCategoryInfoResult> selectByShopIdAndNameWithPage(Map<String, Object> map);

	/**
	 * 
	 * @desc: 删除商品分类
	 * @author zhouh
	 * @param map
	 *            传入分类id、店铺id
	 * @return int
	 * @date: 2018年5月14日 下午3:37:25
	 */
	int deleteByPrimaryKeyWithShopId(Map<String, Long> map);

	/**
	 * 
	 * @desc: 修改商品分类
	 * @author zhouh
	 * @param sellerGoodsCategory
	 * @return int
	 * @date: 2018年5月15日 上午11:36:02
	 */
	int updateByPrimaryKeyWithShopId(SellerGoodsCategory sellerGoodsCategory);

}