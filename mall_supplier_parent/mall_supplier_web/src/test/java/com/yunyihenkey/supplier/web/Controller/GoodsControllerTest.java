package com.yunyihenkey.supplier.web.Controller;

import com.yunyihenkey.Application;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import com.yunyihenkey.supplier.dao.malldb.vo.exportVo.SupplierGoodsExportParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName GoodsControllerTest
 * @Description TODO
 * @Author LuTong
 * @Date 2018/5/22 17:38
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GoodsControllerTest {

    @Autowired(required = false)
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;

    @Test
    public void qualified() {
        List<SupplierGoodsExportParam> list = supplierGoodsInfoMapper.checkAll(1L);
        for (SupplierGoodsExportParam supplierGoodsExportParam : list) {
            System.out.println(supplierGoodsExportParam);
        }

    }
}
