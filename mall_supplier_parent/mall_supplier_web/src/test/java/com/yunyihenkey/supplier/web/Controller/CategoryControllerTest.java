package com.yunyihenkey.supplier.web.Controller;

import com.yunyihenkey.Application;
import com.yunyihenkey.basedao.malldb.basevo.SupplierGoodsCategory;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsCategoryMapper;
import com.yunyihenkey.supplier.dao.malldb.mapper.SupplierGoodsInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CategoryControllerTest {

    @Autowired(required = false)
    private SupplierGoodsCategoryMapper supplierGoodsCategoryMapper;

    @Autowired(required = false)
    private SupplierGoodsInfoMapper supplierGoodsInfoMapper;

    @Autowired
    private ValidatorUtils validatorUtils;


    @Test
    public void query(){
        String name = "家";
        List<SupplierGoodsCategory> list = supplierGoodsCategoryMapper.selectByName(name);
        System.out.println(list);
    }

    @Test
    public void addCategory(){
        SupplierGoodsCategory s = new SupplierGoodsCategory();
        s.setName("天下无双");

        String s1 = validatorUtils.validateAndGetErrorInfo(s, Default.class);

        if (StringUtils.isNotEmpty(s1)) {
            System.out.println(s1);
        }
//        supplierGoodsCategoryMapper.insertSelective(s);
    }

    @Test
    public void updateCategory(){
        SupplierGoodsCategory s = new SupplierGoodsCategory();
        s.setId(9L);
        s.setName("天青如水");
        s.setSortOrder(21);
        s.setUpdateUser("tong");
        s.setUpdateTime(new Date());
        supplierGoodsCategoryMapper.updateByPrimaryKeySelective(s);
    }
}