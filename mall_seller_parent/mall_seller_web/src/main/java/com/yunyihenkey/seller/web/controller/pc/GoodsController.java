package com.yunyihenkey.seller.web.controller.pc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.SellerGoodsInfo.StatusEnum;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.page.PageParam;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.AddGoodsParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.DelGoodsParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.GoodsInfoQueryParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsCategoryInfoResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsInfoResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.UpdGoodsParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.UpdateCategoryParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.UpdateStatusParam;
import com.yunyihenkey.seller.service.SellerGoodsCategoryService;
import com.yunyihenkey.seller.service.SellerGoodsInfoService;

/**
 * @desc: 分销商商品controller
 * @author: zhouh
 * @date: 2018年5月11日 下午12:09:55
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private SellerGoodsInfoService sellerGoodsInfoService;

    @Autowired
    private SellerGoodsCategoryService sellerGoodsCategoryService;

    // @Autowired
    // private JwtUtils jwtUtils;

    @PostMapping("/upd")
    public Object updGoods(HttpServletRequest request, @RequestBody UpdGoodsParam updGoodsParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(updGoodsParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 从jwt中获取用户信息
        // AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        // sellerUser.getShopId();
        // sellerUser.getUserName();
        Long shopId = 61302585l;
        String userName = "admin";

        return sellerGoodsInfoService.updateByPrimaryKeyWithShopIdSelective(userName, shopId, updGoodsParam);
    }

    /**
     * @param request
     * @param addGoodsParam
     * @return Object
     * @desc: 新增商品
     * @author zhouh
     * @date: 2018年5月14日 上午11:38:18
     */
    @PostMapping("/add")
    public Object addGoods(HttpServletRequest request, @RequestBody AddGoodsParam addGoodsParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(addGoodsParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 校验枚举类(添加时商品状态只有两种=发布上架/加入仓库)
        Integer status = addGoodsParam.getStatus();
        if (StatusEnum.warehouse.getValue() != status && StatusEnum.selling.getValue() != status) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "发布上架/加入仓库失败");
        }

        // 从jwt中获取用户信息
        // AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        // sellerUser.getShopId();
        // sellerUser.getUserName();
        Long shopId = 61302585l;
        String userName = "admin";
        Object result = null;
        try {
            result = sellerGoodsInfoService.insertSelective(userName, shopId, addGoodsParam);
        } catch (Exception e) {
            LogUtils.getLogger().error("新增商品失败！==>", e);
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
        }
        return result;
    }

    /**
     * @param request
     * @param delGoodsParam
     * @return Object
     * @desc: 删除商品
     * @author zhouh
     * @date: 2018年5月21日 上午10:41:52
     */
    @PostMapping("/del")
    public Object delGoods(HttpServletRequest request, @RequestBody DelGoodsParam delGoodsParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(delGoodsParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 从jwt中获取用户信息
        // Long shopId =
        // Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
        Long shopId = 61302585l;

        return sellerGoodsInfoService.deleteByPrimaryKeyWithShopId(delGoodsParam.getId(), shopId);
    }

    /**
     * @param request
     * @param goodsInfoQueryParam
     * @return Object
     * @desc: 条件查询
     * @author zhouh
     * @date: 2018年5月14日 上午10:15:11
     */
    @PostMapping("/query")
    public Object queryGoods(HttpServletRequest request, @RequestBody GoodsInfoQueryParam goodsInfoQueryParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(goodsInfoQueryParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 校验枚举类(状态不为空的时候校验)
        if (goodsInfoQueryParam.getStatus() != null) {
            if (StatusEnum.getByValue(goodsInfoQueryParam.getStatus()) == null) {
                return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该状态不存在！");
            }
        }

        // 从jwt中获取用户信息
        // Long shopId =
        // Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
        Long shopId = 61302585l;

        List<SellerGoodsInfoResult> goodsList = sellerGoodsInfoService.selectByCondition(
                goodsInfoQueryParam.getCategoryId(), shopId, goodsInfoQueryParam.getGoodsTitle(),
                goodsInfoQueryParam.getStatus(), goodsInfoQueryParam.getPageNum(), goodsInfoQueryParam.getPageSize());

        // 使用pagehelper的分页对象进行包装
        PageInfo<SellerGoodsInfoResult> pageInfo = new PageInfo<SellerGoodsInfoResult>(goodsList);
        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * @param request
     * @param updateStatusParam
     * @return Object
     * @desc: 批量修改商品状态
     * @author zhouh
     * @date: 2018年5月21日 上午10:42:12
     */
    @PostMapping("/updateStatus")
    public Object updateStatus(HttpServletRequest request, @RequestBody UpdateStatusParam updateStatusParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(updateStatusParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 校验枚举类
        if (StatusEnum.getByValue(updateStatusParam.getStatus()) == null) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该商品状态不存在！");
        }

        // 从jwt中获取用户信息
        // Long shopId =
        // Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
        Long shopId = 61302585l;

        return sellerGoodsInfoService.updateStatus(updateStatusParam.getIds(), updateStatusParam.getStatus(), shopId);
    }

    /**
     * @param request
     * @param updateCategoryParam
     * @return Object
     * @desc: 批量修改商品分类
     * @author zhouh
     * @date: 2018年5月21日 上午10:42:26
     */
    @PostMapping("/updateCategory")
    public Object updateCategory(HttpServletRequest request, @RequestBody UpdateCategoryParam updateCategoryParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(updateCategoryParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 从jwt中获取用户信息
        // Long shopId =
        // Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
        Long shopId = 61302585l;

        return sellerGoodsInfoService.updateCategory(updateCategoryParam.getIds(), updateCategoryParam.getCategoryId(),
                shopId);
    }

    /**
     * @param request
     * @param pageParam
     * @return Object
     * @desc: 查询商品详情
     * @author zhouh
     * @date: 2018年5月14日 上午10:16:10
     */
    @PostMapping("/list")
    public Object goodsInfo(HttpServletRequest request, @RequestBody PageParam pageParam) {

        // 校验参数
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(pageParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }

        // 从jwt中获取用户信息
        // Long shopId =
        // Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
        Long shopId = 61302585l;

        Map<String, Object> result = new HashMap<String, Object>();
        // 查询所有商品分类
        List<SellerGoodsCategoryInfoResult> goodsCategoryList = sellerGoodsCategoryService.selectAllByShopId(shopId);
        result.put("goods_category", goodsCategoryList);

        // 分页查询商品详情
        List<SellerGoodsInfoResult> goodsList = sellerGoodsInfoService.selectByShopIdWithPage(pageParam.getPageNum(),
                pageParam.getPageSize(), shopId);

        // 获取商品分类名称
        for (SellerGoodsInfoResult goodsInfo : goodsList) {
            for (SellerGoodsCategoryInfoResult categoryInfo : goodsCategoryList) {
                if (categoryInfo.getId().equals(goodsInfo.getCategoryId())) {
                    goodsInfo.setCategoryName(categoryInfo.getName());
                    continue;
                }
            }
        }

        // 使用pagehelper的分页对象进行包装
        PageInfo<SellerGoodsInfoResult> pageInfo = new PageInfo<SellerGoodsInfoResult>(goodsList);
        result.put("goods_page_info", pageInfo);

        return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, result);

    }

}
