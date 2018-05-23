package com.yunyihenkey.supplier.web.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.SupplierGoodsInfo.StatusEnum;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.SupplierGoodsAddParam;
import com.yunyihenkey.supplier.service.SearchService;
import com.yunyihenkey.supplier.service.SupplierGoodsInfoService;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.SearchParam;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.SupplierGoodsInfoParam;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.SupplierGoodsParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName GoodsInfoController
 * @Description 商品列表、详情查询
 * @Author LuTong
 * @Date 2018/5/12 14:51
 * @Version 1.0
 */

@RestController
@RequestMapping("/goods/")
public class GoodsInfoController extends BaseController {

    @Autowired(required = false)
    private SupplierGoodsInfoService supplierGoodsInfoService;

    @Autowired(required = false)
    private ValidatorUtils validatorUtils;

    @Autowired(required = false)
    private SearchService searchService;


    /*
     *@Author: LuTong
     *@Description:状态为上架中，且未删除商品查询
     *@Date: 15:42 2018/5/17
     *@Return: com.yunyihenkey.common.vo.resultinfo.ResultInfo
     **/
    @PostMapping("list")
    public ResultInfo getAll(@RequestBody SupplierGoodsInfoParam param) throws Exception {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<SupplierGoodsInfo> infos = supplierGoodsInfoService.selectAll(param.getId());
        PageInfo<SupplierGoodsInfo> pageInfo = new PageInfo<>(infos);
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, pageInfo);
    }

    /*
     *@Author: LuTong
     *@Description: 根据商品id查询商品详情
     *@Date: 12:38 2018/5/14
     *@Param: [id]
     *@Return: com.yunyihenkey.common.vo.resultinfo.ResultInfo
     **/
    @PostMapping("query/{id}")
    public ResultInfo queryByGoodsId(@PathVariable("id") Long id) {
        List list = supplierGoodsInfoService.selectByGoodsId(id);
        SupplierGoodsParam supplierGoodsParam = new SupplierGoodsParam();
        supplierGoodsParam.setSupplierGoodsInfo((SupplierGoodsInfo) list.get(0));
        supplierGoodsParam.setSupplierGoodsDescrip((SupplierGoodsDescrip) list.get(1));
        if (supplierGoodsParam.getSupplierGoodsInfo() == null || supplierGoodsParam.getSupplierGoodsDescrip() == null) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR, null);
        }
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, supplierGoodsParam);
    }

    /*
     *@Author: LuTong
     *@Description: 站内搜索，分页查询
     *@Date: 9:52 2018/5/18
     *@Param: [searchParam]
     *@Return: com.yunyihenkey.common.vo.resultinfo.ResultInfo
     **/
    @PostMapping("search")
    public ResultInfo searchBySolr(@RequestBody SearchParam searchParam) throws Exception {
        String errorInfo = validatorUtils.validateAndGetErrorInfo(searchParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo, "务必填入关键字");
        }
        Map<String, Object> list = searchService.search(searchParam.getQueryString(), searchParam.getPageNum(), searchParam.getPageSize());

        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, list);
    }

    /*
     *@Author: LuTong
     *@Description: 供货商上传商品信息，等待审核人员审核 TODO
     *@Date: 15:49 2018/5/18
     *@Param: [goodsAddParam, request]
     *@Return: com.yunyihenkey.common.vo.resultinfo.ResultInfo
     **/
    @PostMapping("add")
    public ResultInfo addGoods(@RequestBody SupplierGoodsAddParam goodsAddParam, HttpServletRequest request) throws Exception {
        String errorInfo = validatorUtils.validateAndGetErrorInfo(goodsAddParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo);
        }
        //获得当前用户信息,传入商品描述进行添加上传
        goodsAddParam.setSupplierId(10924678L);
        goodsAddParam.setGoodsCode(UUID.randomUUID().toString().replace("-", ""));
        goodsAddParam.setStatus(StatusEnum.PENDING.getValue());
        goodsAddParam.setCreateUser("thisUser");

        goodsAddParam.setCreateTime(new Date());
        supplierGoodsInfoService.supplierInsertGoods(goodsAddParam);
        if (goodsAddParam.getId() == null) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR, "添加失败！");
        }
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, "添加成功！");
    }

    @PostMapping("addtoshop")
    public String addGoodsToShop(Long id, Integer stock, Long version, HttpServletRequest request) throws Exception {
        int i = supplierGoodsInfoService.addGoodsToShop(id, stock, version);
        if (i != 1) {
            return "failed";
        }
        return "success";
    }
}
