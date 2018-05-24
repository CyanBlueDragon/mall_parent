package com.yunyihenkey.supplier.dao.malldb.utils;

import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName SolrUtils
 * @Description 索引的增刪改查
 * @Author LuTong
 * @Date 2018/5/15 10:25
 * @Version 1.0
 */
public class SolrUtils {

    private static final String URL = "http://192.168.0.196:9090/solr/new_core";

    private static HttpSolrClient server = new HttpSolrClient.Builder(URL).build();

    /**
     * 添加、更改索引
     *
     * @param info
     * @throws Exception
     */
    public static void addData(SupplierGoodsInfo info) throws Exception {

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", info.getId());
        doc.addField("goodsCode", info.getGoodsCode().toString());
        doc.addField("goods_name", info.getGoodsName().toString());
        doc.addField("categoryId", info.getCategoryId().toString());
        doc.addField("stock", info.getStock().toString());
        doc.addField("supplyPrice", info.getSupplyPrice().toString());
        doc.addField("deliveryTemplateName", info.getDeliveryTemplateName());
        doc.addField("minRetailPrice", info.getMinRetailPrice().toString());
        doc.addField("maxRetailPrice", info.getMaxRetailPrice().toString());
        doc.addField("picUrl", info.getPicUrl());
        doc.addField("maxProfit", info.getMaxProfit());
        server.add(doc);
        server.commit();

    }

    /**
     * 查询
     *
     * @throws Exception
     */
    public static Map<String, Object> queryData(String queryString, int pageNum, int pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        SolrQuery query = new SolrQuery();
        String param = "goods_name:" + queryString;
        query.setQuery(param);
        query.setStart((pageNum - 1) * pageSize);
        query.setRows(pageSize);
        query.set("df", "_Atest");
        query.setHighlight(true); // 开启高亮显示
        query.addHighlightField("goods_name"); // 添加高亮显示的域
        query.setHighlightSimplePre("<font style=\"color:red\">"); // 设置高亮显示的前缀
        query.setHighlightSimplePost("</font>"); // 设置高亮显示的后缀
        QueryResponse response = server.query(query);
        SolrDocumentList solrDocumentList = response.getResults();
        for (SolrDocument solrDocument : solrDocumentList) {
            Object itemName = null;
            // 取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("goods_name");
            if (list != null && list.size() > 0) {
                itemName = list.get(0); // 将高亮后的结果取出来
            } else {
                itemName = solrDocument.get("goods_name");
            }
            solrDocument.setField("goodsName", itemName);
        }
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("total", solrDocumentList.getNumFound());
        map.put("list", solrDocumentList);
        return map;
    }

    public static ArrayList turn(Object o) {
        return (ArrayList) o;
    }

    /**
     * 根据商品id删除索引
     *
     * @param id
     * @throws Exception
     */
    public static void deleteById(String id) throws Exception {

        server.deleteById(id);
        server.commit();

    }


}
