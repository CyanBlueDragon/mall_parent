package com.yunyihenkey.seller.web.controller.pc.customer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopMembers;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.page.PageParam;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.BatchOperationParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.GetChildrenShopParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.MembersParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.getShopInfoParam;
import com.yunyihenkey.seller.dao.malldb.vo.result.user.ChildrenIdResult;
import com.yunyihenkey.seller.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YangLiu
 * @desc
 * @date 2018/5/16 9:45
 */
@RestController
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private CustomerService customerService;

    /**
     * 获取下级店铺信息
     *
     * @param getChildrenShopParam
     * @param request
     * @return
     */
    @PostMapping("getShopChildren")
    public ResultInfo<PageInfo<ChildrenIdResult>> getShopChildren(@RequestBody GetChildrenShopParam getChildrenShopParam, HttpServletRequest request) {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        if (!sellerUser.getSellerGrade().equals("0") || !sellerUser.getSellerGrade().equals("1")) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "该用户没有下级代理!");
        }
        PageHelper.startPage(getChildrenShopParam.getPageNum(), getChildrenShopParam.getPageSize());
        String id = sellerUser.getShopId();
        String sellerGrade = sellerUser.getSellerGrade();
        /*String id = "61302561";
        String sellerGrade ="0";*/
        List childrenShop = customerService.getChildrenShop(sellerGrade, id);
        PageInfo<ChildrenIdResult> pageInfo = new PageInfo<ChildrenIdResult>(childrenShop);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 根据指定字段查询下级代理
     *
     * @param getChildrenShopParam
     * @param request
     * @return
     */
    @PostMapping("specifiedQuery")
    public ResultInfo<PageInfo<ChildrenIdResult>> specifiedQuery(@RequestBody GetChildrenShopParam getChildrenShopParam, HttpServletRequest request) {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        if (!sellerUser.getSellerGrade().equals("0") || !sellerUser.getSellerGrade().equals("1")) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "该用户没有下级代理!");
        }
        PageHelper.startPage(getChildrenShopParam.getPageNum(), getChildrenShopParam.getPageSize());
        List childrenShop = customerService.getChildrenShop(sellerUser.getSellerGrade(), sellerUser.getId(), getChildrenShopParam);
        PageInfo<ChildrenIdResult> pageInfo = new PageInfo<ChildrenIdResult>(childrenShop);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 根据店铺Id查询店铺
     *
     * @param getShopInfoParam
     * @return
     */
    @PostMapping("getShopInfoByShopId")
    public Object getShopInfoByShopId(@RequestBody getShopInfoParam getShopInfoParam) {
        SellerShop shop = customerService.getShopInfoByShopId(getShopInfoParam.getShopId());
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, shop);
    }


    /**
     * 查询店铺会员
     *
     * @param pageParam
     * @param request
     * @return
     */
    @PostMapping("getMembers")
    public ResultInfo getMembers(@RequestBody PageParam pageParam, HttpServletRequest request) {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List members = customerService.getMembers(sellerUser.getShopId());
        PageInfo<SellerShopMembers> pageInfo = new PageInfo<SellerShopMembers>(members);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 根据指定字段查询店铺会员
     *
     * @param membersParam
     * @param request
     * @return
     */
    @PostMapping("membersSpecifiedQuery")
    public ResultInfo<Object> membersSpecifiedQuery(@RequestBody MembersParam membersParam, HttpServletRequest request) {
        AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
        PageHelper.startPage(membersParam.getPageNum(), membersParam.getPageSize());
        List members = customerService.getMembers(sellerUser.getShopId(), membersParam);
        PageInfo<SellerShopMembers> pageInfo = new PageInfo<SellerShopMembers>(members);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 会员操作
     *
     * @param batchOperationParam
     * @return
     */
    @PostMapping("batchOperation")
    public Object batchOperation(@RequestBody BatchOperationParam batchOperationParam) {
        Long[] ids = batchOperationParam.getIds();
        if (ids.length < 0) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR_PARAM, "参数有误!");
        }
        customerService.batchOperation(ids, batchOperationParam.getOperation());
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }

}
