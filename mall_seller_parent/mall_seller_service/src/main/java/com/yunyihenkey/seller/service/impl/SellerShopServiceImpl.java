
package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShop;
import com.yunyihenkey.seller.service.SellerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerShopServiceImpl implements SellerShopService {
    @Autowired
    private SellerShopBaseMapper sellerShopBaseMapper;

    @Override
    public void updatePay(Long id,Integer payMethod,Integer isEnable) throws Exception {
        SellerShop sellerShop = new SellerShop();
        switch (payMethod) {
            case 0: //支付宝
                sellerShop.setPayAli(isEnable);
                break;
            case 1: //微信
                sellerShop.setPayWx(isEnable);
                break;
            case 2: //银联
                sellerShop.setPayUnion(isEnable);
                break;
        }
        sellerShop.setId(id);
        updateByPrimaryKeySelective(sellerShop);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sellerShopBaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerShop record) {
        return sellerShopBaseMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerShop record) {
        return sellerShopBaseMapper.insertSelective(record);
    }

    @Override
    public SellerShop selectByPrimaryKey(Long id) {
        return sellerShopBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerShop record) {
        return sellerShopBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerShop record) {
        return sellerShopBaseMapper.updateByPrimaryKey(record);
    }

}

