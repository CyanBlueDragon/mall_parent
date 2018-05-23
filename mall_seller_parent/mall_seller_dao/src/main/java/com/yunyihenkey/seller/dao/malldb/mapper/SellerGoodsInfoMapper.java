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
     * @param shopId 店铺id
     * @return List<SellerGoodsInfoVo>
     * @desc: 根据店铺分页查询商品详情页面数据
     * @author zhouh
     * @date: 2018年5月11日 下午3:18:07
     */
    List<SellerGoodsInfoResult> selectByShopIdWithPage(Long shopId);

    /**
     * @param map 传入分类id、店铺id、商品标题、商品状态
     * @return List<SellerGoodsInfoVo>
     * @desc: 条件查询
     * @author zhouh
     * @date: 2018年5月12日 下午5:41:56
     */
    List<SellerGoodsInfoResult> selectByCondition(Map<String, Object> map);

    /**
     * @param map 传入分销商商品id、店铺id
     * @return int 返回1表示该分类存在
     * @desc: 根据供应商商品id和店铺id查询商品信息
     * @author zhouh
     * @date: 2018年5月12日 下午3:53:39
     */
    SellerGoodsInfoResult selectBySupplierGoodsIdWithShopId(Map<String, Object> map);

    /**
     * @param map 出入商品id、店铺id
     * @return SellerGoodsInfoResult
     * @desc: 根据商品id和店铺id查询商品信息
     * @author zhouh
     * @date: 2018年5月23日 下午3:01:03
     */
    SellerGoodsInfoResult selectByPrimaryKeyWithShopId(Map<String, Object> map);

    /**
     * @param record
     * @return Long
     * @desc: 选择新增（返回主键id）
     * @author zhouh
     * @date: 2018年5月17日 下午3:51:49
     */
    int insertSelective(SellerGoodsInfo record);

    /**
     * @param map 传入id集合、状态
     * @return int
     * @desc: 批量修改状态
     * @author zhouh
     * @date: 2018年5月12日 上午9:57:02
     */
    int updateStatus(Map<String, Object> map);

    /**
     * @param map 传入id集合、分类id
     * @return int
     * @desc: 批量修改分类
     * @author zhouh
     * @date: 2018年5月12日 上午9:59:04
     */
    int updateCategory(Map<String, Object> map);

    /**
     * @param map 传入商品id、店铺id
     * @return int
     * @desc: 删除商品
     * @author zhouh
     * @date: 2018年5月14日 上午10:24:45
     */
    int deleteByPrimaryKeyWithShopId(Map<String, Object> map);

    /**
     * @param map 传入商品id、店铺id
     * @return int
     * @desc: 根据商品id和店铺id修改商品信息(选择修改)
     * @author zhouh
     * @date: 2018年5月17日 下午5:34:45
     */
    int updateByPrimaryKeyWithShopIdSelective(SellerGoodsInfo sellerGoodsInfo);

    /**
     * @param map 传入分类id、"其他"分类id、店铺id
     * @return int
     * @desc: 将商品移至默认分类
     * @author zhouh
     * @date: 2018年5月22日 下午2:23:53
     */
    int updateByCategoryId(Map<String, Object> map);

}