package com.yunyihenkey.seller.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.common.mallconfig.seller.shopset.CommonSetEnum;
import com.yunyihenkey.common.utils.BeanMapUtils;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.shopSetController.ShopUpdateParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.shopSetController.UpdateCommonSetParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.shopSetController.UpdatePayParam;
import com.yunyihenkey.seller.service.SellerShopService;

/**
 * @author : 007
 * @date : 2018/5/8 11:57
 * 店铺设置
 */
@RestController
@RequestMapping(value = "/shopSet/")
public class ShopSetController extends BaseController {
    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private SellerShopService sellerShopService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 查询店铺信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("query")
    public Object query(HttpServletRequest request) throws Exception {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        SellerShop sellerShop = sellerShopService.selectByPrimaryKey(Long.parseLong(sellerUser.getShopId()));
        BeanMapUtils.beanToMap(sellerShop);
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS,  sellerShop);
    }

    /**
     * 修改店铺信息
     *
     * @param shopUpdateParam
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public Object update(@RequestBody ShopUpdateParam shopUpdateParam, HttpServletRequest request) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(shopUpdateParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        Long shopId = Long.parseLong(sellerUser.getShopId());
        SellerShop selectByPrimaryKey = sellerShopService.selectByPrimaryKey(shopId);
        if (selectByPrimaryKey == null) {
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.SELLER_NOT_SHOP);
        }
        SellerShop sellerShop = new SellerShop();
        sellerShop.setId(shopId);
        sellerShop.setName(shopUpdateParam.getName());
        sellerShop.setStatus(shopUpdateParam.getStatus());
        sellerShop.setLogoUrl(shopUpdateParam.getLogoUrl());
        sellerShop.setDepict(shopUpdateParam.getDepict());
        sellerShop.setOperator(shopUpdateParam.getOperator());
        sellerShop.setEmail(shopUpdateParam.getEmail());
        sellerShop.setPhone(shopUpdateParam.getPhone());
        sellerShop.setTxQq(shopUpdateParam.getTxQq());
        sellerShop.setTxWx(shopUpdateParam.getTxWx());
        sellerShopService.updateByPrimaryKeySelective(sellerShop);
        return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }

    /**
     * 关闭开启支付方式
     *
     * @param updatePayParam
     * @return
     * @throws Exception
     */
    @PostMapping("updatePay")
    public Object updatePay(@RequestBody UpdatePayParam updatePayParam,HttpServletRequest request) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(updatePayParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        Long shopId = Long.parseLong(sellerUser.getShopId());
        sellerShopService.updatePay(shopId, updatePayParam.getPayMethod(), updatePayParam.getIsEnable());
        return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }

    /**
     * 查询店铺信息通用设置
     *
     * @return
     * @throws Exception
     */
    @GetMapping("queryCommonSet")
    public Object queryCommonSet(HttpServletRequest request) throws Exception {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        SellerShop sellerShop = sellerShopService.selectByPrimaryKey(Long.parseLong(sellerUser.getShopId()));

        if (sellerShop.getUnpaidTime() == null) {
            sellerShop.setUnpaidTime(CommonSetEnum.UNPAIDTIME_TIME.getValue());
        }
        if (sellerShop.getConfirmOrderTime() == null) {
            sellerShop.setConfirmOrderTime(CommonSetEnum.CONFIRM_ORDER_TIME.getValue().intValue());
        }
        if (sellerShop.getRefundTime() == null) {
            sellerShop.setRefundTime( CommonSetEnum.REFUND_TIME.getValue().intValue());
        }
        if (sellerShop.getSellOutTime() == null) {
            sellerShop.setSellOutTime(CommonSetEnum.SELLER_OUT_TIME.getValue().intValue());
        }
        if (sellerShop.getReturnOrderTime() == null) {
            sellerShop.setReturnOrderTime(CommonSetEnum.RETURN_ORDER_TIME.getValue().intValue());
        }
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, sellerShop);
    }

    /**
     * 查询店铺信息通用设置
     *
     * @return
     * @throws Exception
     *
     */
    @PostMapping("updateCommonSet")
    public Object updateCommonSet(@RequestBody UpdateCommonSetParam updateCommonSetParam, HttpServletRequest request) throws Exception {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        sellerShopService.updateCommonSet(Long.parseLong(sellerUser.getShopId()),updateCommonSetParam.getUnpaidTime(),updateCommonSetParam.getConfirmOrderTime(),updateCommonSetParam.getRefundTime(),updateCommonSetParam.getSellOutTime(),updateCommonSetParam.getReturnOrderTime());
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);

    }
}
