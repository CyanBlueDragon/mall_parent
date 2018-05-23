package com.yunyihenkey.seller.web.test.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunyihenkey.Application;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsCategory;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsDescrip;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsInfo;
import com.yunyihenkey.basedao.malldb.basevoEnum.SellerGoodsInfo.StatusEnum;
import com.yunyihenkey.common.vo.page.PageParam;
import com.yunyihenkey.seller.service.PictureService;
import com.yunyihenkey.seller.service.SellerGoodsCategoryService;
import com.yunyihenkey.seller.service.SellerGoodsDescripService;
import com.yunyihenkey.seller.service.SellerGoodsInfoService;

/**
 * @Author SunQ
 * @Date 2018/5/7 0007 17:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

	@Autowired
	private SellerGoodsInfoService sellerGoodsInfoService;

	@Autowired
	private SellerGoodsCategoryService sellerGoodsCategoryService;

	@Autowired
	private SellerGoodsDescripService sellerGoodsDescripService;

//	@Autowired
//	private PictureService pictureService;

    @Value("${FTP.FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP.FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP.FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP.FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP.FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${FTP.IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;
    @Value("${spring.servlet.multipart.max-file-size}")
    private String txt;
    @Value("${FTP.IMAGE_MAX_SIZE}")
    private int size;
    @Value("${url.inventory_reduction_url}")
    private String inventory_reduction_url;
	
	
	@Test
	public void testImg() {

        System.out.println("FTP_ADDRESS=" + FTP_ADDRESS);
        System.out.println("FTP_PORT=" + FTP_PORT);
        System.out.println("FTP_USERNAME=" + FTP_USERNAME);
        System.out.println("FTP_PASSWORD=" + FTP_PASSWORD);
        System.out.println("FTP_BASE_PATH=" + FTP_BASE_PATH);
        System.out.println("IMAGE_BASE_URL=" + IMAGE_BASE_URL);
        System.out.println("spring.servlet.multipart.max-file-size=" + txt);
        System.out.println("FTP.IMAGE_MAX_SIZE=" + size);
		
	}

	@Test
	public void selectAllByPage() {

		PageParam pageParam = new PageParam();
		pageParam.setPageNum(1);
		pageParam.setPageSize(10);

		Long shopId = 61302585l;

		// 分销商商品表
		// List<SellerGoodsInfoVo> selectByShopIdWithPage =
		// sellerGoodsInfoService.selectByShopIdWithPage(pageParam, shopId);
		// System.out.println("分页查询分销商商品表：" + selectByShopIdWithPage.size());
		// for (SellerGoodsInfoVo sellerGoodsInfoVo : selectByShopIdWithPage) {
		// System.out.println(sellerGoodsInfoVo.toString());
		// }

		// List<SellerGoodsCategory> selectAllByPage2 =
		// sellerGoodsCategoryService.selectAllByPage(pageParam);
		// System.out.println("分页查询分销商商分类表：" + selectAllByPage2.size());
		// for (SellerGoodsCategory sellerGoodsCategory : selectAllByPage2) {
		// System.out.println(sellerGoodsCategory.toString());
		// }
		// Date createDate = selectAllByPage2.get(1).getCreateDate();
		// System.out.println(createDate);
		// String format = DateUtil.format(createDate, DateUtil.DEFAULT);
		// System.out.println(format);

		// // 测试
		// List<SellerGoodsInfoVo> selectTest =
		// sellerGoodsInfoService.selectTest(pageParam);
		// for (SellerGoodsInfoVo sellerGoodsInfoVo : selectTest) {
		// System.out.println(sellerGoodsInfoVo.toString());
		// }

	}

	@Test
	public void selectByPrimaryKey() {
		SellerGoodsInfo selectByPrimaryKey = sellerGoodsInfoService.selectByPrimaryKey(20l);
		SellerGoodsCategory selectByPrimaryKey2 = sellerGoodsCategoryService.selectByPrimaryKey(3l);
		SellerGoodsDescrip selectByPrimaryKey3 = sellerGoodsDescripService.selectByPrimaryKey(1l);
		System.out.println("测试..................");
		System.out.print(selectByPrimaryKey2.toString() + " \n" + selectByPrimaryKey.toString() + " \n"
				+ selectByPrimaryKey3.toString());
		System.out.println("测试完成..................");

	}

	@Test
	public void insertOrderProduct() {
		// ShoppingmallOrderProductInfo shoppingmallOrderProductInfo = new
		// ShoppingmallOrderProductInfo();
		// shoppingmallOrderProductInfo.setId(idWorker.nextId());
		// shoppingmallOrderProductInfo.setMallId(45899847553249281L);
		// shoppingmallOrderProductInfo.setSupplierId(idWorker.nextId());
		// shoppingmallOrderProductInfo.setOrderCode(45899847553249282L);
		// shoppingmallOrderProductInfo.setProductId(idWorker.nextId());
		// shoppingmallOrderProductInfo.setProductName("王老吉");
		// shoppingmallOrderProductInfo.setProductPrice(50L);
		// shoppingmallOrderProductInfo.setProductCount(1);
		// shoppingmallOrderProductInfo.setPostageAmount(0L);
		// shoppingmallOrderProductInfo.setPostageType(0);
		// shoppingmallOrderProductInfo.setSendStatus(0);
		// shoppingmallOrderProductInfo.setRefundSign(0);
		// shoppingmallOrderProductInfo.setCreateTime(new Date());
		// shoppingmallOrderProductInfoService.insertSelective(shoppingmallOrderProductInfo);
	}

	@Test
	public void test() {
		// System.out.println(SendStatusEnum.getByValue(1).getText());

		List<Object> goodsStatusList = new ArrayList<Object>();
		StatusEnum[] values = StatusEnum.values();
		for (int i = 0; i < values.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("code", values[i].getValue());
			map.put("text", values[i].getText());
			goodsStatusList.add(map);
		}

		for (int i = 0; i < goodsStatusList.size(); i++) {
			System.out.println(goodsStatusList.get(i));
		}

	}

}
