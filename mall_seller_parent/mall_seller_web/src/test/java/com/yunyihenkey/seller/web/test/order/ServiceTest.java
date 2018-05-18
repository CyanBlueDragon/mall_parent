package com.yunyihenkey.seller.web.test.order;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import com.yunyihenkey.seller.dao.malldb.vo.param.deliverGoodsController.DeliveryParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.ShoppingmallOrderInfoResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.Application;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderAftersaleInfo;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderInfo;
import com.yunyihenkey.basedao.malldb.basevo.ShoppingmallOrderProductInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.ShoppingmallOrderInfo.OrderStatusEnum;
import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.utils.excel.ExcelUtils;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderAftersaleExportVo;
import com.yunyihenkey.seller.dao.malldb.exportVo.OrderProductExportVo;
import com.yunyihenkey.seller.dao.malldb.vo.param.aftersaleController.OrderAftersaleInfoParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.deliverGoodsController.OrderProductInfoParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.orderController.OrderInfoParam;
import com.yunyihenkey.seller.service.ShoppingmallOrderAftersaleInfoService;
import com.yunyihenkey.seller.service.ShoppingmallOrderInfoService;
import com.yunyihenkey.seller.service.ShoppingmallOrderProductInfoService;

/**
 * @Author SunQ
 * @Date 2018/5/7 0007 17:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ShoppingmallOrderInfoService shoppingmallOrderInfoService;

    @Autowired
    private ShoppingmallOrderProductInfoService shoppingmallOrderProductInfoService;

    @Autowired
    private ShoppingmallOrderAftersaleInfoService shoppingmallOrderAftersaleInfoService;

    @Test
    public void selectAllByPage() {
        OrderInfoParam param = new OrderInfoParam();
        param.setPageNum(1);
        param.setPageSize(10);
        param.setMallId("45899847553249281");
        param.setOrderStatus(new int[]{OrderStatusEnum.WAITPAY.getValue(), OrderStatusEnum.WAITSEND.getValue(), OrderStatusEnum.WAITRECEIVED.getValue()});
        List<ShoppingmallOrderInfoResult> list = shoppingmallOrderInfoService.selectAllByPage(param.getMallId(), param.getOrderCode(), param.getMemberAccount(), param.getOrderStatus());
        PageInfo<ShoppingmallOrderInfoResult> pageInfo = new PageInfo<ShoppingmallOrderInfoResult>(list);
        System.out.print(pageInfo);
    }

    @Test
    public void selectByPrimaryKey() {
        ShoppingmallOrderInfo  shoppingmallOrderInfo = shoppingmallOrderInfoService.selectByPrimaryKey(45899847553249280L);
        System.out.print(shoppingmallOrderInfo.getOrderStatus());
    }

    @Test
    public void selectByOrderCode() {
        ShoppingmallOrderInfoResult shoppingmallOrderInfo = shoppingmallOrderInfoService.selectByOrderCode("45899847553249282");
        System.out.print(shoppingmallOrderInfo);
    }

    @Test
    public void insertOrderProduct() {
        ShoppingmallOrderProductInfo shoppingmallOrderProductInfo = new ShoppingmallOrderProductInfo();
        shoppingmallOrderProductInfo.setId(idWorker.nextId());
        shoppingmallOrderProductInfo.setMallId(45899847553249281L);
        shoppingmallOrderProductInfo.setSupplierId(idWorker.nextId());
        shoppingmallOrderProductInfo.setOrderCode(45899847553249282L);
        shoppingmallOrderProductInfo.setProductId(idWorker.nextId());
        shoppingmallOrderProductInfo.setProductName("王老吉");
        shoppingmallOrderProductInfo.setProductPrice(50L);
        shoppingmallOrderProductInfo.setProductCount(1);
        shoppingmallOrderProductInfo.setPostageAmount(0L);
        shoppingmallOrderProductInfo.setPostageType(0);
        shoppingmallOrderProductInfo.setSendStatus(0);
        shoppingmallOrderProductInfo.setRefundSign(0);
        shoppingmallOrderProductInfo.setCreateTime(new Date());
        shoppingmallOrderProductInfoService.insertSelective(shoppingmallOrderProductInfo);
    }

    @Test
    public void selectAllByPage2() {
        OrderProductInfoParam param = new OrderProductInfoParam();
        param.setPageNum(1);
        param.setPageSize(10);
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ShoppingmallOrderProductInfo> list = shoppingmallOrderProductInfoService.selectAllByPage(param.getMallId(), param.getSupplierId(), param.getProductName(), param.getReceiverName());
        System.out.print(list);
    }

    @Test
    public void export() {
        OrderProductInfoParam param = new OrderProductInfoParam();
        List<OrderProductExportVo> list = shoppingmallOrderProductInfoService.selectExportVo(param.getMallId(), param.getSupplierId(), param.getProductName(), param.getReceiverName());

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("d:\\success.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelUtils<OrderProductExportVo> util = new ExcelUtils<OrderProductExportVo>(OrderProductExportVo.class);// 创建工具类.
        util.exportExcel(list, "订单信息", out);// 导出
    }

    @Test
    public void delivery() {
        DeliveryParam deliveryParam = new DeliveryParam();
        deliveryParam.setOrderProductId("46651047849881600");
        deliveryParam.setOrderCode("45899847553249282");
        deliveryParam.setExpressNumber("123");
        deliveryParam.setExpressCode("sf");
        deliveryParam.setExpressCompany("顺丰速运");
        System.out.print(shoppingmallOrderProductInfoService.delivery(deliveryParam));
    }

    @Test
    public void selectAllByPage3() {
        OrderAftersaleInfoParam param = new OrderAftersaleInfoParam();
        param.setPageNum(1);
        param.setPageSize(10);
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ShoppingmallOrderAftersaleInfo> list = shoppingmallOrderAftersaleInfoService.selectAllByPage(param.getMallId(), param.getOrderCode(), param.getMemberAccount(), param.getAftersaleStatus());
        System.out.print(list);
    }

    @Test
    public void export2() {
        OrderAftersaleInfoParam param = new OrderAftersaleInfoParam();
        List<OrderAftersaleExportVo> list = shoppingmallOrderAftersaleInfoService.selectExportVo(param.getMallId(), param.getOrderCode(), param.getMemberAccount(), param.getAftersaleStatus());

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("d:\\success2.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelUtils<OrderAftersaleExportVo> util = new ExcelUtils<OrderAftersaleExportVo>(OrderAftersaleExportVo.class);// 创建工具类.
        util.exportExcel(list, "维权信息", out);// 导出
    }
}
