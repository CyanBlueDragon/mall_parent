package com.yunyihenkey.supplier.service.impl;

import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.supplier.dao.malldb.utils.SolrUtils;
import com.yunyihenkey.supplier.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName SearchServiceImpl
 * @Description 通过solrutils进行站内搜索
 * @Author LuTong
 * @Date 2018/5/16 9:27
 * @Version 1.0
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired(required = false)
    private SolrUtils solrUtils;

    @Override
    public Map<String, Object> search(String queryString, int pageNum, int pageSize) {
        LogUtils.getLogger().info("分页搜索商品信息......");
        try {
            Map<String, Object> list = solrUtils.queryData(queryString, pageNum, pageSize);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
