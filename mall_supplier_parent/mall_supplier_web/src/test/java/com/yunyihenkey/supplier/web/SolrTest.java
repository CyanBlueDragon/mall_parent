package com.yunyihenkey.supplier.web;


import com.yunyihenkey.Application;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import com.yunyihenkey.supplier.dao.malldb.utils.SolrUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName SolrTest
 * @Description TODO
 * @Author LuTong
 * @Date 2018/5/15 9:28
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SolrTest {

    @Autowired(required = false)
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;


    /**
     * 添加文档
     */
    @Test
    public void addDoc() {
        try {
            List<SupplierGoodsInfo> infos = supplierGoodsInfoMapper.selectAll(null);

            for (SupplierGoodsInfo info : infos) {
                SolrUtils.addData(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryData() {
        try {
            SolrUtils.queryData(null,1,10);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteData(){
        try {
            SolrUtils.deleteById("2048");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stringTest(){
        String s = "19.8";
        System.out.println(s.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?"));
    }

    @Test
    public void  updateTest(){
        SupplierGoodsInfo info = new SupplierGoodsInfo();
        info.setId(512L);
        info.setGoodsName("sjadghkjaakjdshkjashdsa");
        info.setSupplyPrice((long) 199);
        info.setPicUrl("http://asdaskdhiausdkjas.onion");
        info.setMaxProfit("30%");
        try {
            SolrUtils.addData(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
