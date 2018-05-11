package com.yunyihenkey.seller.web.controller;

import com.github.pagehelper.PageHelper;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.utils.excel.ExcelUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo;
import com.yunyihenkey.seller.service.ShoppingmallOrderProductInfoService;
import com.yunyihenkey.seller.web.vo.param.deliverGoodsController.OrderProductInfoParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author SunQ
 * @Date 2018/5/8 0008 14:11
 */
@RestController
@RequestMapping("/deliverGoods")
public class DeliverGoodsController extends BaseController {

    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private ShoppingmallOrderProductInfoService shoppingmallOrderProductInfoService;

    /**
     * 发货订单列表查询
     * @author SunQ
     * @Date 14:57 2018/5/8 0008
     * @Param [orderProductInfoParam]
     * @return com.yunyihenkey.common.vo.resultinfo.ResultInfo
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultInfo list(@RequestBody OrderProductInfoParam orderProductInfoParam) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(orderProductInfoParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        PageHelper.startPage(orderProductInfoParam.getPageNum(), orderProductInfoParam.getPageSize());
        List<Map<String, Object>> list =
                shoppingmallOrderProductInfoService.selectAllByPage(orderProductInfoParam.getMallId(), orderProductInfoParam.getSupplierId(), orderProductInfoParam.getProductName(), orderProductInfoParam.getReceiverName());
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, list);
    }

    /**
     * 发货订单详情
     * @author SunQ
     * @Date 17:49 2018/5/8 0008
     * @Param [id]
     * @return com.yunyihenkey.common.vo.resultinfo.ResultInfo
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public ResultInfo get(@PathVariable("id") String id) {
        if(StringUtils.isBlank(id)){
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "id不能为空", null);
        }
        ShoppingmallOrderProductInfo shoppingmallOrderProductInfo = shoppingmallOrderProductInfoService.selectByPrimaryKey(Long.parseLong(id));
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, shoppingmallOrderProductInfo);
    }

    /**
     * 订单导出
     * @author SunQ
     * @Date 12:08 2018/5/9 0009
     * @Param [response, orderProductInfoParam]
     * @return void
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletResponse response, @RequestBody OrderProductInfoParam orderProductInfoParam) throws Exception {
        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName =
                new String((new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date(System.currentTimeMillis()))).getBytes(), "ISO8859_1");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
        List<OrderProductExportVo> list =
                shoppingmallOrderProductInfoService.selectExportVo(orderProductInfoParam.getMallId(), orderProductInfoParam.getSupplierId(), orderProductInfoParam.getProductName(), orderProductInfoParam.getReceiverName());
        ExcelUtils<OrderProductExportVo> util = new ExcelUtils<OrderProductExportVo>(OrderProductExportVo.class);// 创建工具类.
        util.exportExcel(list, "发货订单信息", response.getOutputStream());// 导出
    }
}
