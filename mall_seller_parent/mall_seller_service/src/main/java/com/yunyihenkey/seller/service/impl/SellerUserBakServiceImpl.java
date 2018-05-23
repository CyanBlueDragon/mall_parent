package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.seller.dao.malldb.mapper.SellerUserBakMapper;
import com.yunyihenkey.seller.service.SellerUserBakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HeXing
 * @desc
 * @date 2018/5/21 17:49
 */
@Service
public class SellerUserBakServiceImpl implements SellerUserBakService {
    @Autowired
    private SellerUserBakMapper sellerUserBakMapper;

    @Override
    public void save(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }
        sellerUserBakMapper.save(id);
    }
}
