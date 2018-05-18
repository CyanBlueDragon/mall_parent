package com.yunyihenkey.seller.web.controller.customer;

import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @desc
 * @author YangLiu
 * @date 2018/5/16 9:45
 */
@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Resource
    private JwtUtils jwtUtils;

    @GetMapping("getShopChildren")
    public ResultInfo getShopChildren(HttpServletRequest request){
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        if (sellerUser == null) {
            return null;
        }
        String id = sellerUser.getShopId();


        return null;
    }




}
