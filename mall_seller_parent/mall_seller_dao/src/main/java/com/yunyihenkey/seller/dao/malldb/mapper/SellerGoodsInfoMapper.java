package com.yunyihenkey.seller.dao.malldb.mapper;

import java.util.List;
import java.util.Map;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsInfo;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsInfoResult;

/**
 * 
 * @desc: 分销商商品mapper
 * @author: zhouh
 * @date: 2018年5月11日 上午9:25:35
 * 
 */
public interface SellerGoodsInfoMapper {

	/**
	 * 
	 * @desc: 根据店铺分页查询商品详情页面数据
	 * @author zhouh
	 * @param shopId
	 *            店铺id
	 * @return List<SellerGoodsInfoVo>
	 * @date: 2018年5月11日 下午3:18:07
	 */
	List<SellerGoodsInfoResult> selectByShopIdWithPage(Long shopId);

	/**
	 * 
	 * @desc: 条件查询
	 * @author zhouh
	 * @param map
	 *            传入分类id、店铺id、商品标题、商品状态
	 * @return List<SellerGoodsInfoVo>
	 * @date: 2018年5月12日 下午5:41:56
	 */
	List<SellerGoodsInfoResult> selectByCondition(Map<String, Object> map);

	/**
	 * 
	 * @desc: 根据分销商商品id和店铺id查询商品信息
	 * @author zhouh
	 * @param map
	 *            传入分销商商品id、店铺id
	 * @return int 返回1表示该分类存在
	 * @date: 2018年5月12日 下午3:53:39
	 */
	SellerGoodsInfoResult selectBySupplierGoodsIdWithShopId(Map<String, Object> map);

	/**
	 * 
	 * @desc: 选择新增（返回主键id）
	 * @author zhouh
	 * @param record
	 * @return Long
	 * @date: 2018年5月17日 下午3:51:49
	 */
	int insertSelective(SellerGoodsInfo record);

	/**
	 * 
	 * @desc: 批量修改状态
	 * @author zhouh
	 * @param map
	 *            传入id集合、状态
	 * @return int
	 * @date: 2018年5月12日 上午9:57:02
	 */
	int updateStatus(Map<String, Object> map);

	/**
	 * 
	 * @desc: 批量修改分类
	 * @author zhouh
	 * @param map
	 *            传入id集合、分类id
	 * @return int
	 * @date: 2018年5月12日 上午9:59:04
	 */
	int updateCategory(Map<String, Object> map);

	/**
	 * 
	 * @desc: 删除商品
	 * @author zhouh
	 * @param map
	 *            传入商品id、店铺id
	 * @return int
	 * @date: 2018年5月14日 上午10:24:45
	 */
	int deleteByPrimaryKeyWithShopId(Map<String, Long> map);

	/**
	 * 
	 * @desc: 根据商品id和店铺id修改商品信息
	 * @author zhouh
	 * @param map
	 *            传入商品id、店铺id
	 * @return int
	 * @date: 2018年5月17日 下午5:34:45
	 */
	int updateByPrimaryKeyWithShopIdSelective(SellerGoodsInfo sellerGoodsInfo);

}