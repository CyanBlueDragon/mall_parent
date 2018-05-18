package com.yunyihenkey.seller.web.controller;

import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.service.SellerPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HeXing
 * @desc 资源权限控制器
 * @date 2018/5/15 10:57
 */
@RestController
@RequestMapping("permi")
public class UserPermissionController {
    @Autowired
    private SellerPermissionService sellerPermissionService;
    /**
     * 查询全部权限树列表
     * @return
     */
    @GetMapping("queryTreeList")
    public Object queryTreeList() {
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, sellerPermissionService.queryTreeList());
    }
}
