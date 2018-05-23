package com.yunyihenkey.supplier.web.Controller;
import java.util.Date;

import com.yunyihenkey.Application;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsInfo;
import com.yunyihenkey.supplier.dao.malldb.vo.param.GoodsInfoController.SupplierGoodsAddParam;
import com.yunyihenkey.supplier.service.SupplierGoodsInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName GoodsInfoControllerTest
 * @Description
 * @Author LuTong
 * @Date 2018/5/14 14:11
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GoodsInfoControllerTest {

    @Autowired
    private SupplierGoodsInfoService supplierGoodsInfoService;

    @Test
    public void list(){
        List<SupplierGoodsInfo> infos = supplierGoodsInfoService.selectAll(null);
        System.out.println(infos);
    }

    @Test
    public void query(){
        List list = supplierGoodsInfoService.selectByGoodsId(1L);
        System.out.println(list);
    }

    @Test
    public void add(){
        SupplierGoodsAddParam supplierGoodsAddParam = new SupplierGoodsAddParam();
        supplierGoodsAddParam.setCategoryId(1L);
        supplierGoodsAddParam.setSupplierId(1111L);
        supplierGoodsAddParam.setGoodsCode("154321535");
        supplierGoodsAddParam.setGoodsName("DOTA2小绿本10000级");
        supplierGoodsAddParam.setCatId(1);
        supplierGoodsAddParam.setStock(1000);
        supplierGoodsAddParam.setSellPoint("买就是了");
        supplierGoodsAddParam.setDeliveryTemplateName("包邮");
        supplierGoodsAddParam.setStatus(1);
        supplierGoodsAddParam.setSupplyPrice(10000L);
        supplierGoodsAddParam.setCreateUser("loversheartless");
        supplierGoodsAddParam.setCreateTime(new Date());
        supplierGoodsAddParam.setPicUrl("http://baidu.com");
        supplierGoodsInfoService.supplierInsertGoods(supplierGoodsAddParam);
        System.out.println(supplierGoodsAddParam.getId());
    }

    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            String s = (System.currentTimeMillis()+"").substring(1,5)+((System.nanoTime())+"").substring(7,10);
            System.out.println(s);
        }

    }

}
