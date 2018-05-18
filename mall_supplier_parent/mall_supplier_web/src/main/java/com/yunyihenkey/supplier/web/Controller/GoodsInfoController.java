package com.yunyihenkey.supplier.web.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.common.idworker.SnowflakeIdWorker;
import com.yunyihenkey.common.utils.JacksonUtils;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SupplierGoodsAddParam;
import com.yunyihenkey.supplier.service.SearchService;
import com.yunyihenkey.supplier.service.SupplierGoodsInfoService;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SearchParam;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SupplierGoodsInfoParam;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SupplierGoodsParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName GoodsInfoController
 * @Description 商品查询
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
     *@Description:兼容ie9从request中获取分页数据，进行商品查询
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
     *@Param: [request]
     *@Return: com.yunyihenkey.common.vo.resultinfo.ResultInfo
     **/
    @PostMapping("query/{id}")
    public ResultInfo queryByGoodsId(@PathVariable("id") Long id, HttpServletRequest request) {
        if (id == null || id == 0) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, "没有此商品");
        }
        List list = supplierGoodsInfoService.selectByGoodsId(id);
        SupplierGoodsParam supplierGoodsParam = new SupplierGoodsParam();
        supplierGoodsParam.setSupplierGoodsInfo((SupplierGoodsInfo) list.get(0));
        supplierGoodsParam.setSupplierGoodsDescrip((SupplierGoodsDescrip) list.get(1));
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

    @PostMapping("add")
    public ResultInfo addGoods(@RequestBody SupplierGoodsAddParam goodsAddParam, HttpServletRequest request) throws Exception {
        String errorInfo = validatorUtils.validateAndGetErrorInfo(goodsAddParam, Default.class);
        if (StringUtils.isNotEmpty(errorInfo)) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo);
        }
        //获得当前用户信息
        goodsAddParam.setSupplierId(10924678L);
        goodsAddParam.setGoodsCode(UUID.randomUUID().toString().replace("-", ""));
        goodsAddParam.setStatus(5);
        goodsAddParam.setCreateUser("thisUser");
        goodsAddParam.setCreateTime(new Date());
        supplierGoodsInfoService.supplierInsertGoods(goodsAddParam);
        if (goodsAddParam.getId() == null) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR, "添加失败！");
        }
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, "添加成功！");
    }

}
