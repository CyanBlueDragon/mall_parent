package com.yunyihenkey.seller.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunyihenkey.basedao.malldb.basemapper.SellerShopMoneyCashBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerShopMoneyCash;
import com.yunyihenkey.common.utils.ChartUtils;
import com.yunyihenkey.common.utils.ChartUtils.GetDateStr;
import com.yunyihenkey.common.utils.ChartUtils.SetZeroValue;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerShopMoneyCashMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetMoneyBusinessChartParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetMoneyBusinessChartParam.GetMoneyBusinessChartEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetMoneyBusinessChartResult;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartParam.GetTakeCashLogChartEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.accountController.GetTakeCashLogChartResult;
import com.yunyihenkey.seller.service.SellerShopMoneyCashService;

@Service
public class SellerShopMoneyCashServiceImpl implements SellerShopMoneyCashService {
	@Autowired
	private SellerShopMoneyCashBaseMapper sellerShopMoneyCashBaseMapper;
	@Autowired
	private SellerShopMoneyCashMapper sellerShopTakeCashLogMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sellerShopMoneyCashBaseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SellerShopMoneyCash record) {
		return sellerShopMoneyCashBaseMapper.insert(record);
	}

	@Override
	public int insertSelective(SellerShopMoneyCash record) {
		return sellerShopMoneyCashBaseMapper.insertSelective(record);
	}

	@Override
	public SellerShopMoneyCash selectByPrimaryKey(Long id) {
		return sellerShopMoneyCashBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SellerShopMoneyCash record) {
		return sellerShopMoneyCashBaseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SellerShopMoneyCash record) {
		return sellerShopMoneyCashBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SellerShopMoneyCash> getByShopId(Long shopId) {
		return sellerShopTakeCashLogMapper.getByShopId(shopId);
	}

	/** 回调函数单例，避免每次实例化 */
	public static final GetDateStr<GetTakeCashLogChartResult> getDateStr = new GetDateStr<GetTakeCashLogChartResult>() {
		@Override
		public String callback(GetTakeCashLogChartResult e) {
			return e.getTime();
		}
	};
	/** 回调函数单例，避免每次实例化 */
	public static final SetZeroValue<GetTakeCashLogChartResult> setZeroValue = new SetZeroValue<GetTakeCashLogChartResult>() {
		@Override
		public void callback(GetTakeCashLogChartResult e, String newDateStr) {
			e.setTime(newDateStr);
			e.setMoney(0L);
		}
	};

	@Override
	public List<GetTakeCashLogChartResult> getTakeCashLogChart(Long shopId, GetTakeCashLogChartParam param)
			throws InstantiationException, IllegalAccessException, ParseException {
		// 参数枚举
		GetTakeCashLogChartEnum byValue = GetTakeCashLogChartEnum.getByValue(param.getTime());

		// 开始时间
		Date startDate = DateUtil.addMonths(DateUtil.getCurrentDayZero(), -byValue.getMonth());

		// 查询数据
		List<GetTakeCashLogChartResult> list = sellerShopTakeCashLogMapper.getTakeCashLogChart(shopId, startDate);

		// 填充0数据
		ChartUtils.fillZero(list, startDate, null, new SimpleDateFormat("yyyy-MM"), Calendar.MONTH, getDateStr,
				setZeroValue);

		return list;
	}

	/** 回调函数单例，避免每次实例化 */
	public static final GetDateStr<GetMoneyBusinessChartResult> getDateStr2 = new GetDateStr<GetMoneyBusinessChartResult>() {
		@Override
		public String callback(GetMoneyBusinessChartResult e) {
			return e.getTime();
		}
	};
	/** 回调函数单例，避免每次实例化 */
	public static final SetZeroValue<GetMoneyBusinessChartResult> setZeroValue2 = new SetZeroValue<GetMoneyBusinessChartResult>() {
		@Override
		public void callback(GetMoneyBusinessChartResult e, String newDateStr) {
			e.setTime(newDateStr);
			e.setMoney(0L);
		}
	};

	@Override
	public List<GetMoneyBusinessChartResult> getMoneyBusinessChart(Long shopId, GetMoneyBusinessChartParam param)
			throws InstantiationException, IllegalAccessException, ParseException {

		// 参数枚举
		GetMoneyBusinessChartEnum en = GetMoneyBusinessChartEnum.getByValue(param.getTime());

		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(en.getJavaDateType(), -en.getJavaDateValue());// 负数

		// 开始时间
		Date startDate = c.getTime();

		// 查询数据
		List<GetMoneyBusinessChartResult> list = sellerShopTakeCashLogMapper.getMoneyBusinessChart(shopId, startDate,
				en.getMysqlDateFormat());

		// 填充0数据
		ChartUtils.fillZero(list, startDate, null, new SimpleDateFormat(en.getJavaDateFormat()),
				en.getJavaDateFormatMinUnit(), getDateStr2, setZeroValue2);

		return list;
	}

}
