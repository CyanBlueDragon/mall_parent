package com.yunyihenkey.seller.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderAftersaleInfo;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.utils.excel.ExcelUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo;
import com.yunyihenkey.seller.dao.malldb.vo.param.aftersaleController.OrderAftersaleInfoParam;
import com.yunyihenkey.seller.service.ShoppingmallOrderAftersaleInfoService;

/**
 * @Author SunQ
 * @Date 2018/5/9 0009 14:30
 */
@RestController
@RequestMapping("/aftersale")
public class AftersaleController extends BaseController {

    @Autowired
    private ValidatorUtils validatorUtils;

    @Autowired
    private ShoppingmallOrderAftersaleInfoService shoppingmallOrderAftersaleInfoService;

    /**
     * 维权订单列表查询
     * @author SunQ
     * @Date 15:16 2018/5/9 0009
     * @Param [orderAftersaleInfoParam]
     * @return java.lang.Object
     */
    @PostMapping("/list")
    public Object list(@RequestBody OrderAftersaleInfoParam orderAftersaleInfoParam) throws Exception {
        // 验证必填项
        String errorInfo = validatorUtils.validateAndGetErrorInfo(orderAftersaleInfoParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        PageHelper.startPage(orderAftersaleInfoParam.getPageNum(), orderAftersaleInfoParam.getPageSize());
        List<ShoppingmallOrderAftersaleInfo> list =
                shoppingmallOrderAftersaleInfoService.selectAllByPage(orderAftersaleInfoParam.getMallId(), orderAftersaleInfoParam.getOrderCode(), orderAftersaleInfoParam.getMemberAccount(), orderAftersaleInfoParam.getAftersaleStatus());
        // 使用pagehelper的分页对象进行包装
        PageInfo<ShoppingmallOrderAftersaleInfo> pageInfo = new PageInfo<ShoppingmallOrderAftersaleInfo>(list);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 维权订单详情
     * @author SunQ
     * @Date 15:59 2018/5/9 0009
     * @Param [id]
     * @return java.lang.Object
     */
    @PostMapping("/get/{id}")
    public Object get(@PathVariable("id") String id) throws Exception {
        if(StringUtils.isBlank(id)){
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "id不能为空", null);
        }
        ShoppingmallOrderAftersaleInfo shoppingmallOrderAftersaleInfo = shoppingmallOrderAftersaleInfoService.selectByPrimaryKey(Long.parseLong(id));
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, shoppingmallOrderAftersaleInfo);
    }

    /**
     * 订单导出
     * @author SunQ
     * @Date 12:08 2018/5/9 0009
     * @Param [response, orderAftersaleInfoParam]
     * @return void
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody OrderAftersaleInfoParam orderAftersaleInfoParam) throws Exception {
        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName =
                new String((new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date(System.currentTimeMillis()))).getBytes(), "ISO8859_1");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
        List<OrderAftersaleExportVo> list =
                shoppingmallOrderAftersaleInfoService.selectExportVo(orderAftersaleInfoParam.getMallId(), orderAftersaleInfoParam.getOrderCode(), orderAftersaleInfoParam.getMemberAccount(), orderAftersaleInfoParam.getAftersaleStatus());
        ExcelUtils<OrderAftersaleExportVo> util = new ExcelUtils<OrderAftersaleExportVo>(OrderAftersaleExportVo.class);// 创建工具类.
        util.exportExcel(list, "维权订单信息", response.getOutputStream());// 导出
    }

    /**
     * 确认退款(审核通过)
     * @author SunQ
     * @Date 12:20 2018/5/11 0011
     * @Param [id]
     * @return java.lang.Object
     */
    @PostMapping("/adopt/{id}")
    public Object adopt(@PathVariable("id") String id) throws Exception {
        if(StringUtils.isBlank(id)){
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "id不能为空", null);
        }
        shoppingmallOrderAftersaleInfoService.adopt(Long.parseLong(id));
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }

    /**
     * 审核不通过
     * @author SunQ
     * @Date 12:20 2018/5/11 0011
     * @Param [id]
     * @return java.lang.Object
     */
    @PostMapping("/unadopt/{id}")
    public Object unadopt(@PathVariable("id") String id) throws Exception {
        if(StringUtils.isBlank(id)){
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "id不能为空", null);
        }
        shoppingmallOrderAftersaleInfoService.unadopt(Long.parseLong(id));
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }
}
