package com.yunyihenkey.seller.web.vo.param.accountController;

import javax.validation.constraints.NotEmpty;

import com.yunyihenkey.common.vo.base.BaseVo;

/**
 * @author : 007
 * @date : 2018/5/4 14:37
 */
public class GetShopInfoParam extends BaseVo {
	@NotEmpty
	private String userName;

}
