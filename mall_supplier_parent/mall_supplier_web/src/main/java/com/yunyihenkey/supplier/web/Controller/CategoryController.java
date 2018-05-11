package com.yunyihenkey.supplier.web.Controller;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.supplier.service.SupplierGoodsCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private SupplierGoodsCategoryService supplierGoodsCategoryService;

    @Autowired
    private ValidatorUtils validatorUtils;

    @GetMapping("/list")
    public ResultInfo list() throws Exception {
        List<SupplierGoodsCategory> categoryList = supplierGoodsCategoryService.selectAll();
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, categoryList);
    }

    @PostMapping("/query/{name}")
    public ResultInfo query(@PathVariable("name") String name) throws Exception {
        if (StringUtils.isBlank(name)) {
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, "name不能为空", null);
        }
        List<SupplierGoodsCategory> categoryList = supplierGoodsCategoryService.selectByName(name);
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, categoryList);
    }

    @PostMapping("/add")
    public ResultInfo addCategory(@RequestBody SupplierGoodsCategory supplierGoodsCategory, HttpServletRequest request) throws Exception {
        String errorInfo = validatorUtils.validateAndGetErrorInfo(supplierGoodsCategory, Default.class);
        if(StringUtils.isNotEmpty(errorInfo)){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        supplierGoodsCategoryService.insertSelective(supplierGoodsCategory);
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, "添加成功");
    }
}
