package com.yunyihenkey.seller.web.controller.pc;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.*;
import com.yunyihenkey.seller.service.SellerRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @author HeXing
 * @desc 用户角色控制器
 * @date 2018/5/15 10:55
 */
@RestController
@RequestMapping("role")
public class UserRoleController extends BaseController {
    @Autowired
    private SellerRoleService sellerRoleService;
    @Autowired
    private ValidatorUtils validatorUtils;

    /**
     * 角色列表
     *
     * @param queryRoleListParam
     * @param request
     * @return
     */
    @PostMapping("queryList")
    public Object queryList(@RequestBody QueryRoleListParam queryRoleListParam, HttpServletRequest request) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(queryRoleListParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        PageHelper.startPage(queryRoleListParam.getPageNum(), queryRoleListParam.getPageSize());
        List<QueryRoleListResult> roleListResults = sellerRoleService.queryRoleList(queryRoleListParam.getName());
        PageInfo<QueryRoleListResult> pageInfo = new PageInfo<QueryRoleListResult>(roleListResults);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 保存角色
     *
     * @param saveRoleParam
     * @return
     */
    @PostMapping("save")
    public Object save(@RequestBody SaveRoleParam saveRoleParam) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(saveRoleParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        return sellerRoleService.save(saveRoleParam);
    }

    /**
     * 角色删除
     *
     * @param roleDeleteParam
     * @return
     * @throws Exception
     */
    @PostMapping("del")
    public Object del(@RequestBody RoleDeleteParam roleDeleteParam) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(roleDeleteParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        return sellerRoleService.delete(roleDeleteParam.getId());
    }

    /**
     * 角色修改
     *
     * @param updateRoleParam
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public Object update(@RequestBody UpdateRoleParam updateRoleParam) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(updateRoleParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        return sellerRoleService.updateBatch(updateRoleParam);
    }

    /**
     * 查询角色
     *
     * @param queryRole
     * @return
     * @throws Exception
     */
    @PostMapping("query")
    public Object query(@RequestBody QueryRole queryRole) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(queryRole, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        return sellerRoleService.query(queryRole.getId());
    }

}
