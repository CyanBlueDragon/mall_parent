package com.yunyihenkey.supplier.web.Controller;

import com.yunyihenkey.basedao.malldb.basevoEnum.SellerGoodsInfo.StatusEnum;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Review.GoodsQualifiedParam;
import com.yunyihenkey.supplier.service.SupplierGoodsInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

/**
 * @ClassName GoodsController
 * @Description 运营管理后台对商品进行审核、上、下架等管理操作
 * @Author LuTong
 * @Date 2018/5/22 16:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/goods/operating")
public class GoodsController extends BaseController {

    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private SupplierGoodsInfoService supplierGoodsInfoService;

    @PostMapping("qualified")
    public ResultInfo isQualified(@RequestBody GoodsQualifiedParam qualifiedParam) throws Exception {
        String errorInfo = validatorUtils.validateAndGetErrorInfo(qualifiedParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo);
        }

        return null;
    }
}
