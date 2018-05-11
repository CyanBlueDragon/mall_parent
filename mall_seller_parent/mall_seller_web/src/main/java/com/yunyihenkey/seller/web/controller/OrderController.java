package com.yunyihenkey.seller.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.ShoppingmallOrderInfoVo;
import com.yunyihenkey.seller.service.ShoppingmallOrderInfoService;
import com.yunyihenkey.seller.web.vo.param.orderController.OrderInfoParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

/**
 * @Author SunQ
 * @Date 2018/5/7 0007 18:08
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private ShoppingmallOrderInfoService shoppingmallOrderInfoService;

    /**
     * 订单列表查询
     * @author SunQ
     * @Date 10:03 2018/5/8 0008
     * @Param [orderInfoParam]
     * @return com.yunyihenkey.common.vo.resultinfo.ResultInfo
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultInfo list(@RequestBody OrderInfoParam orderInfoParam) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(orderInfoParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        PageHelper.startPage(orderInfoParam.getPageNum(), orderInfoParam.getPageSize());
        List<ShoppingmallOrderInfoVo> list =
                shoppingmallOrderInfoService.selectAllByPage(orderInfoParam.getMallId(), orderInfoParam.getOrderCode(), orderInfoParam.getMemberAccount(), orderInfoParam.getOrderStatus());
        // 使用pagehelper的分页对象进行包装
        PageInfo<ShoppingmallOrderInfoVo> pageInfo = new PageInfo<ShoppingmallOrderInfoVo>(list);
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 订单号查询
     * @author SunQ
     * @Date 11:43 2018/5/8 0008
     * @Param [shoppingmallOrderInfoParam]
     * @return com.yunyihenkey.common.vo.resultinfo.ResultInfo
     */
    @RequestMapping(value = "/get/{orderCode}", method = RequestMethod.POST)
    public ResultInfo get(@PathVariable("orderCode") String orderCode) throws Exception {
        if(StringUtils.isBlank(orderCode)){
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "orderCode不能为空", null);
        }
        ShoppingmallOrderInfoVo orderInfo = shoppingmallOrderInfoService.selectByOrderCode(orderCode);
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, orderInfo);
    }
}
