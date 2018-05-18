package com.yunyihenkey.seller.service;

import java.util.List;

import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsInfo;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.AddGoodsParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsInfoResult;

/**
 * 
 * @desc: 分销商商品表service
 * @author: zhouh
 * @date: 2018年5月10日 上午9:18:11
 *
 */
public interface SellerGoodsInfoService extends BaseService<SellerGoodsInfo, Long> {

	/**
	 * 
	 * @desc: 根据店铺分页查询商品详情数据
	 * @author zhouh
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示数量
	 * @param shopId
	 *            店铺id
	 * @return List<SellerGoodsInfoVo>
	 * @date: 2018年5月11日 下午5:55:52
	 */
	List<SellerGoodsInfoResult> selectByShopIdWithPage(int pageNum, int pageSize, Long shopId);

	/**
	 * 
	 * @desc: 条件查询
	 * @author zhouh
	 * @param categoryId
	 *            分类id
	 * @param shopId
	 *            分类id
	 * @param goodsName
	 *            商品标题
	 * @param goodsStatus
	 *            商品状态
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @return List<SellerGoodsInfoVo>
	 * @date: 2018年5月12日 下午5:39:25
	 */
	List<SellerGoodsInfoResult> selectByCondition(Long categoryId, Long shopId, String goodsTitle, Integer goodsStatus,
			Integer pageNum, Integer pageSize);

	/**
	 * 
	 * @desc: 批量修改状态
	 * @author zhouh
	 * @param ids
	 *            商品主键集合
	 * @param status
	 *            状态
	 * @param shopId
	 *            店铺id
	 * @return ResultInfo<Object>
	 * @date: 2018年5月12日 上午10:05:52
	 */
	ResultInfo<Object> updateStatus(Long[] ids, Integer status, Long shopId);

	/**
	 * 
	 * @desc: 批量修改分类
	 * @author zhouh
	 * @param ids
	 *            商品主键集合
	 * @param categoryId
	 *            分类id
	 * @param shopId
	 *            店铺id
	 * @return ResultInfo<Object>
	 * @date: 2018年5月12日 上午11:50:45
	 */
	ResultInfo<Object> updateCategory(Long[] ids, Long categoryId, Long shopId);

	/**
	 * 
	 * @desc:
	 * @author zhouh
	 * @param id
	 *            主键
	 * @param shopId
	 *            店铺id
	 * @return ResultInfo<Object>
	 * @date: 2018年5月14日 上午10:24:00
	 */
	ResultInfo<Object> deleteByPrimaryKeyWithShopId(Long id, Long shopId);

	/**
	 * 
	 * @desc: 新增商品（选择新增）
	 * @author zhouh
	 * @param sellerUser
	 * @param record
	 * @return Object
	 * @date: 2018年5月17日 上午11:36:03
	 */
	Object insertSelective(String userName, Long shopId, AddGoodsParam record);

}