package com.yunyihenkey.supplier.web.Controller;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.supplier.service.SupplierGoodsCategoryService;
import com.yunyihenkey.supplier.dao.malldb.vo.param.Controller.SupplierGoodsCategoryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController extends BaseController {

    @Autowired(required = false)
    private SupplierGoodsCategoryService supplierGoodsCategoryService;

    @Autowired
    private ValidatorUtils validatorUtils;

    /**
     * 根据传入的name查询分类详细信息
     * @param name
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("query")
    public ResultInfo query(@RequestParam(value="name",required = false)String name, HttpServletRequest request) throws Exception {
        List<SupplierGoodsCategory> categoryList = supplierGoodsCategoryService.selectByName(name);
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, categoryList);
    }

    /**
     * 添加商品分类
     * @param supplierGoodsCategory
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ResultInfo addCategory(@RequestBody SupplierGoodsCategoryParam supplierGoodsCategory, HttpServletRequest request) throws Exception {
        if(supplierGoodsCategory.getId() != null){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, "添加分类不允许有id");
        }
        String errorInfo = validatorUtils.validateAndGetErrorInfo(supplierGoodsCategory, Default.class);
        if(StringUtils.isNotEmpty(errorInfo)){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        SupplierGoodsCategory category = new SupplierGoodsCategory();
        category.setId(null);
        category.setName(supplierGoodsCategory.getName());
        category.setSortOrder(supplierGoodsCategory.getSortOrder());
        supplierGoodsCategoryService.insertSelective(category);
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.SUCCESS, "添加成功");
    }

    /**
     * 删除商品分类，被删除分类下所有商品被转移至“其他”分类
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("delete/{id}")
    public ResultInfo delCategory(@PathVariable("id")Long id, HttpServletRequest request) throws Exception{
        if(id == 1L){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER,CodeEnum.ERROR,"此分类不可删除!");
        }
        int i = supplierGoodsCategoryService.deleteByPrimaryKey(id);
        if(i == 0){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER,CodeEnum.ERROR,"并无此分类!");
        }
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER,CodeEnum.SUCCESS,"删除成功!");
    }

    /**
     * 对商品分类信息进行修改
     * @param supplierGoodsCategoryParam
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public ResultInfo updateCategory(@RequestBody SupplierGoodsCategoryParam supplierGoodsCategoryParam,HttpServletRequest request) throws Exception{
        if(supplierGoodsCategoryParam.getId() == null){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, "id不允许为空");
        }
        String errorInfo = validatorUtils.validateAndGetErrorInfo(supplierGoodsCategoryParam, Default.class);
        if(StringUtils.isNotEmpty(errorInfo)){
            return new ResultInfo<>(SystemCodeEnum.SUPPLIER, CodeEnum.ERROR_PARAM, errorInfo, null);
        }
        SupplierGoodsCategory category = new SupplierGoodsCategory();
        category.setId(supplierGoodsCategoryParam.getId());
        category.setName(supplierGoodsCategoryParam.getName());
        category.setSortOrder(supplierGoodsCategoryParam.getSortOrder());
        category.setUpdateUser(null);
        category.setUpdateTime(new Date());
        supplierGoodsCategoryService.updateByPrimaryKeySelective(category);
        return new ResultInfo<>(SystemCodeEnum.SUPPLIER,CodeEnum.SUCCESS,"修改成功!");
    }
}
