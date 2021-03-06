package com.yunyihenkey.seller.web.controller.pc;

import java.util.List;

import javax.validation.groups.Default;

import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.OrderInfoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.QueryOrderParam;
import com.yunyihenkey.seller.service.ShoppingmallOrderInfoService;

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
     *
     * @return java.lang.Object
     * @author SunQ
     * @Date 10:03 2018/5/8 0008
     * @Param [queryOrderParam]
     */
    @PostMapping("/list")
    public Object list(@RequestBody QueryOrderParam queryOrderParam) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(queryOrderParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        PageHelper.startPage(queryOrderParam.getPageNum(), queryOrderParam.getPageSize());
        List<OrderInfoResult> list =
                shoppingmallOrderInfoService.selectAllByPage(queryOrderParam.getMallId(), queryOrderParam.getOrderCode(), queryOrderParam.getMemberAccount(), queryOrderParam.getOrderStatus());
        // 使用pagehelper的分页对象进行包装
        PageInfo<OrderInfoResult> pageInfo = new PageInfo<OrderInfoResult>(list);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 订单号查询
     *
     * @return java.lang.Object
     * @author SunQ
     * @Date 11:43 2018/5/8 0008
     * @Param [shoppingmallOrderInfoParam]
     */
    @PostMapping("/get/{orderCode}")
    public Object get(@PathVariable("orderCode") String orderCode) throws Exception {
        if (StringUtils.isBlank(orderCode)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "orderCode不能为空", null);
        }
        OrderInfoResult orderInfo = shoppingmallOrderInfoService.selectByOrderCode(orderCode);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, orderInfo);
    }
}
