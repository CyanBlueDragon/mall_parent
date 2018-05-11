package com.yunyihenkey.seller.web.controller;

import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.service.SellerShopService;
import com.yunyihenkey.seller.web.vo.param.shopSetController.ShopUpdateParam;
import com.yunyihenkey.seller.web.vo.param.shopSetController.UpdatePayParam;

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

    /**
     * 查询店铺信息
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("query/{id}")
    public ResultInfo query(@PathVariable(value = "id", required = true) Long id) throws Exception {
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, null, sellerShopService.selectByPrimaryKey(id));
    }

    /**
     * 修改店铺信息
     * @param shopUpdateParam
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public ResultInfo update(@RequestBody ShopUpdateParam shopUpdateParam) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(shopUpdateParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        SellerShop selectByPrimaryKey = sellerShopService.selectByPrimaryKey(shopUpdateParam.getId());
        if (selectByPrimaryKey == null) {
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.SELLER_NOT_SHOP);
        }
        SellerShop sellerShop = new SellerShop();
        sellerShop.setId(shopUpdateParam.getId());
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
     * @param updatePayParam
     * @return
     * @throws Exception
     */
    @PostMapping("updatePay")
    public ResultInfo updatePay(@RequestBody UpdatePayParam  updatePayParam) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(updatePayParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        sellerShopService.updatePay( updatePayParam.getId(), updatePayParam.getPayMethod(), updatePayParam.getIsEnable());
        return new ResultInfo<String>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }


}
