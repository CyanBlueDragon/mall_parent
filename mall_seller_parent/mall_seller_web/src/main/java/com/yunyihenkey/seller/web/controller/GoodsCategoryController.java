package com.yunyihenkey.seller.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yunyihenkey.basedao.malldb.basevo.SellerGoodsCategory;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.page.PageParam;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.goodsController.SellerGoodsCategoryInfoResult;
import com.yunyihenkey.seller.service.SellerGoodsCategoryService;

/**
 * 
 * @desc: 分销商商品分类controller
 * @author: zhouh
 * @date: 2018年5月11日 下午12:09:55
 *
 */
@RestController
@RequestMapping("/goods_category")
public class GoodsCategoryController {

	@Autowired
	private ValidatorUtils validatorUtils;

	@Autowired
	private SellerGoodsCategoryService sellerGoodsCategoryService;

	// @Autowired
	// private JwtUtils jwtUtils;

	/**
	 * 
	 * @desc: 修改商品分类
	 * @author zhouh
	 * @param request
	 * @param name
	 *            分类名称
	 * @param sortOrder
	 *            排序
	 * @return Object
	 * @date: 2018年5月14日 下午4:19:43
	 */
	@PostMapping("/upd")
	public Object updCategory(HttpServletRequest request, String name, Long id,
			@RequestParam(name = "sortOrder", defaultValue = "1") Integer sortOrder) {

		// 校验参数
		if (StringUtils.isBlank(name)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "分类名称不能为空！");
		}
		if (sortOrder < 1 || sortOrder > 100) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "排序超出范围！");
		}
		if (id == null || id < 0) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "id不存在！");
		}

		// 从jwt中获取用户信息
		// AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
		// sellerUser.getShopId();
		// sellerUser.getUserName();
		Long shopId = 61302585l;
		String userName = "admin";

		return sellerGoodsCategoryService.updateByPrimaryKeyWithShopId(id, shopId, userName, name, sortOrder);
	}

	/**
	 * 
	 * @desc: 新增商品分类
	 * @author zhouh
	 * @param request
	 * @param name
	 *            分类名称
	 * @param sortOrder
	 *            排序
	 * @return Object
	 * @date: 2018年5月14日 下午3:59:55
	 */
	@PostMapping("/add")
	public Object addCategory(HttpServletRequest request, String name,
			@RequestParam(name = "sortOrder", defaultValue = "1") Integer sortOrder) {

		// 校验参数
		if (StringUtils.isBlank(name)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "分类名称不能为空！");
		}
		if (sortOrder < 1 || sortOrder > 100) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "排序超出范围！");
		}

		// 从jwt中获取用户信息
		// AuthSellerUser sellerUser = jwtUtils.getSellerUser(request);
		// sellerUser.getShopId();
		// sellerUser.getUserName();
		Long shopId = 61302585l;
		String userName = "admin";

		SellerGoodsCategory sellerGoodsCategory = new SellerGoodsCategory();
		sellerGoodsCategory.setName(name);
		sellerGoodsCategory.setSortOrder(sortOrder);
		sellerGoodsCategory.setCreateUser(userName);
		sellerGoodsCategory.setCreateTime(DateUtil.getCurrentDate());
		sellerGoodsCategory.setShopId(shopId);
		sellerGoodsCategoryService.insertSelective(sellerGoodsCategory);

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
	}

	/**
	 * 
	 * @desc: 删除商品分类
	 * @author zhouh
	 * @param request
	 * @param id
	 * @return Object
	 * @date: 2018年5月14日 下午3:59:39
	 */
	@PostMapping("/del")
	public Object delCategory(HttpServletRequest request, Long id) {

		// 校验参数
		if (id == null || id < 1) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "id不存在！");
		}

		// 从jwt中获取用户信息
		// Long shopId =
		// Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
		Long shopId = 61302585l;

		return sellerGoodsCategoryService.deleteByPrimaryKeyWithShopId(id, shopId);
	}

	/**
	 * 
	 * @desc: 条件查询
	 * @author zhouh
	 * @param request
	 * @param pageParam
	 * @param name
	 * @return Object
	 * @date: 2018年5月14日 下午3:31:22
	 */
	@PostMapping("/query")
	public Object queryCategory(HttpServletRequest request, PageParam pageParam, String name) {

		// 校验参数
		if (StringUtils.isBlank(name)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "分类名称不能为空！");
		}
		String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(pageParam, Default.class);
		if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
		}

		// 从jwt中获取用户信息
		// Long shopId =
		// Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
		Long shopId = 61302585l;

		// 查询商品分类
		List<SellerGoodsCategoryInfoResult> goodsCategoryList = sellerGoodsCategoryService
				.selectByShopIdAndNameWithPage(pageParam.getPageNum(), pageParam.getPageSize(), shopId, name);
		// 使用pagehelper的分页对象进行包装
		PageInfo<SellerGoodsCategoryInfoResult> pageInfo = new PageInfo<SellerGoodsCategoryInfoResult>(
				goodsCategoryList);

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
	}

	/**
	 * 
	 * @desc:分页查询商品分类
	 * @author zhouh
	 * @param request
	 * @param pageParam
	 * @return Object
	 * @date: 2018年5月14日 下午2:46:53
	 */
	@PostMapping("/list")
	public Object goodsCategoryInfo(HttpServletRequest request, PageParam pageParam) {

		// 校验参数
		String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(pageParam, Default.class);
		if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
			return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
		}

		// 从jwt中获取用户信息
		// Long shopId =
		// Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
		Long shopId = 61302585l;

		return sellerGoodsCategoryService.selectAllWithGoodsNumByShopId(pageParam.getPageNum(), pageParam.getPageSize(),
				shopId);

	}

	/**
	 * 
	 * @desc: 查询所有分类
	 * @author zhouh
	 * @param request
	 * @return Object
	 * @date: 2018年5月17日 上午9:07:15
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Object getAllgoodsCategoryInfo(HttpServletRequest request) {

		// 从jwt中获取用户信息
		// Long shopId =
		// Long.parseLong(jwtUtils.getSellerUser(request).getShopId());
		Long shopId = 61302585l;
		// 查询所有分类
		List<SellerGoodsCategoryInfoResult> categoryList = sellerGoodsCategoryService.selectAllByShopId(shopId);

		return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, categoryList);
	}

}
