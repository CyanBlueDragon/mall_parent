package com.yunyihenkey.seller.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopMoneyCashBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyCash;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.DateUtil.GetDateStr;
import com.yunyihenkey.common.utils.DateUtil.SetZeroValue;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopMoneyCashMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartResult;
import com.yunyihenkey.seller.service.SellerShopMoneyCashService;

@Service
public class SellerShopMoneyCashServiceImpl implements SellerShopMoneyCashService {
    @Autowired
    private SellerShopMoneyCashBaseMapper sellerShopMoneyCashBaseMapper;
    @Autowired
    private SellerShopMoneyCashMapper sellerShopTakeCashLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sellerShopMoneyCashBaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerShopMoneyCash record) {
        return sellerShopMoneyCashBaseMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerShopMoneyCash record) {
        return sellerShopMoneyCashBaseMapper.insertSelective(record);
    }

    @Override
    public SellerShopMoneyCash selectByPrimaryKey(Long id) {
        return sellerShopMoneyCashBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerShopMoneyCash record) {
        return sellerShopMoneyCashBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerShopMoneyCash record) {
        return sellerShopMoneyCashBaseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SellerShopMoneyCash> getByShopId(Long shopId) {
        return sellerShopTakeCashLogMapper.getByShopId(shopId);
    }

    @Override
    public List<GetTakeCashLogChartResult> getTakeCashLogChart(Long shopId, Date startDate)
            throws InstantiationException, IllegalAccessException, ParseException {
        List<GetTakeCashLogChartResult> list = sellerShopTakeCashLogMapper.getTakeCashLogChart(shopId, startDate);

        DateUtil.fillZero(list, startDate, null, new SimpleDateFormat("yyyy-MM"), Calendar.MONTH,
                new GetDateStr<GetTakeCashLogChartResult>() {
                    @Override
                    public String callback(GetTakeCashLogChartResult e) {
                        return e.getTime();
                    }
                }, new SetZeroValue<GetTakeCashLogChartResult>() {
                    @Override
                    public void callback(GetTakeCashLogChartResult e, String newDateStr) {
                        e.setTime(newDateStr);
                        e.setMoney(0L);
                    }
                });

        return list;
    }

}
