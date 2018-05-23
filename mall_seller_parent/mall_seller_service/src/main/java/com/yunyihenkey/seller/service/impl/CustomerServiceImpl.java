package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerShopMembersBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopMembers;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerShopMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerShopMembersMapper;
import com.yunyihenkey.seller.dao.malldb.mapper.AuthSellerSurveyStatisticsMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.GetChildrenShopParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.customerController.MembersParam;
import com.yunyihenkey.seller.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiarYang
 * @date 2018/5/17 10:18
 * @desc
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private AuthSellerSurveyStatisticsMapper authSellerSurveyStatisticsMapper;

    @Resource
    private AuthSellerShopMapper authSellerShopMapper;

    @Resource
    private AuthSellerShopMembersMapper authSellerShopMembersMapper;

    @Resource
    private SellerShopBaseMapper sellerShopBaseMapper;

    @Resource
    private SellerShopMembersBaseMapper sellerShopMembersBaseMapper;

    @Override
    public List getChildrenShop(String sellerGrade, String shopId) {
        Map<String, Long> map = new HashMap<>();
        map.put("shopId", Long.valueOf(shopId));
        if (sellerGrade.equals("0")) {
            return authSellerShopMapper.selectParentIdByShopId(map);
        } else if (sellerGrade.equals("1")) {
            return authSellerShopMapper.selectParentIdByShopId(map);
        }
        return null;
    }

    @Override
    public List getChildrenShop(String sellerGrade, String shopId, GetChildrenShopParam getChildrenShopParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        map.put("id", getChildrenShopParam.getId());
        map.put("shopName", getChildrenShopParam.getShopName());
        map.put("status", getChildrenShopParam.getStatus());
        if (sellerGrade.equals("0")) {
            return authSellerShopMapper.selectParentIdByShopId(map);
        } else if (sellerGrade.equals("1")) {
            return authSellerShopMapper.getCByShopId(map);
        }
        return null;
    }

    @Override
    public SellerShop getShopInfoByShopId(Long shopId) {
        return sellerShopBaseMapper.selectByPrimaryKey(shopId);
    }

    @Override
    public List getMembers(String shopId) {
        return authSellerShopMembersMapper.selectByShopId(Long.valueOf(shopId));
    }

    @Override
    public List getMembers(String shopId, MembersParam membersParam) {
        Map<String, Object> map = new HashMap<>();
        map.put("shopId", Long.valueOf(shopId));
        map.put("membersName", membersParam.getMembersName());
        map.put("membersAccount", membersParam.getMembersAccount());
        map.put("state", membersParam.getState());
        return authSellerShopMembersMapper.getMembers(map);
    }


    @Override
    public void batchOperation(Long[] ids, Integer operation) {
        if (ids.length == 1) {
            SellerShopMembers sellerShopMembers = new SellerShopMembers();
            sellerShopMembers.setId(ids[0]);
            sellerShopMembers.setState(operation);
            sellerShopMembersBaseMapper.updateByPrimaryKeySelective(sellerShopMembers);
        } else {
            List<SellerShopMembers> list = new ArrayList<>();
            for (Long id : ids) {
                SellerShopMembers sellerShopMembers = new SellerShopMembers();
                sellerShopMembers.setId(id);
                sellerShopMembers.setState(operation);
                list.add(sellerShopMembers);
            }
            authSellerShopMembersMapper.updateBatch(list);
        }
    }
}
