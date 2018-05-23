package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.GetChildrenShopParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.MembersParam;

import java.util.List;

/**
 * @author LiarYang
 * @date 2018/5/17 10:16
 * @desc
 */
public interface CustomerService {
    /**
     * 查询全部下级代理
     *
     * @param sellerGrade
     * @param shopId
     * @return
     */
    List getChildrenShop(String sellerGrade, String shopId);

    /**
     * 根据指定条件插叙
     *
     * @param sellerGrade
     * @param shopId
     * @param getChildrenShopParam
     * @return
     */
    List getChildrenShop(String sellerGrade, String shopId, GetChildrenShopParam getChildrenShopParam);

    /**
     * 根据shopId查询对应shop详情
     *
     * @param shopId
     * @return
     */
    SellerShop getShopInfoByShopId(Long shopId);

    /**
     * 查询会员信息
     *
     * @param shopId
     * @return
     */
    List getMembers(String shopId);

    /**
     * 根据指定字段插叙会员信息
     *
     * @param shopId
     * @param membersParam
     * @return
     */
    List getMembers(String shopId, MembersParam membersParam);


    void batchOperation(Long[] ids, Integer operation);
}
