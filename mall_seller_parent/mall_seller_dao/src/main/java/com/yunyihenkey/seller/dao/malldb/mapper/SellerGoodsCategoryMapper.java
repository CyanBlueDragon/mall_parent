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
     * @desc: 根据分类id和店铺id查询商品分类
	 * @author zhouh
	 * @param map
	 *            传入分类id、店铺id
     * @return
	 * @date: 2018年5月12日 下午3:53:39
	 */
    SellerGoodsCategoryInfoResult selectByIdWithShopId(Map<String, Object> map);

	/**
     *
     * @desc:查询分类名称（模糊查询）
	 * @author zhouh
	 * @param map
	 *            传入分类名称、店铺id
	 * @return List<SellerGoodsCategoryInfoVo>
	 * @date: 2018年5月14日 下午3:08:27
	 */
	List<SellerGoodsCategoryInfoResult> selectByShopIdAndNameWithPage(Map<String, Object> map);

    /**
     * @param map 店铺id
     * @return SellerGoodsCategoryInfoResult
     * @desc: 查询分类默认分类
     * @author zhouh
     * @date: 2018年5月22日 下午2:41:37
     */
    SellerGoodsCategoryInfoResult selectIsDefaultByShopId(Map<String, Object> map);

	/**
	 * 
	 * @desc: 删除商品分类
	 * @author zhouh
	 * @param map
	 *            传入分类id、店铺id
	 * @return int
	 * @date: 2018年5月14日 下午3:37:25
	 */
    int deleteByPrimaryKeyWithShopId(Map<String, Object> map);

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