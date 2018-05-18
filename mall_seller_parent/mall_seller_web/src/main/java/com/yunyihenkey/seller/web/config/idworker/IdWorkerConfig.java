package com.yunyihenkey.seller.web.config.idworker;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunyihenkey.basedao.malldb.commonMapper.MallSnowflakeDatacenterMapper;
import com.yunyihenkey.common.constant.MallConstants;
import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.idworker.SnowflakeIdWorker;
import com.yunyihenkey.common.utils.LogUtils;

@Configuration
public class IdWorkerConfig {

	@Autowired
	private MallSnowflakeDatacenterMapper mallSnowflakeDatacenterMapper;

	public @Value("${spring.profiles.active}") String active;

	@Bean
	public IdWorker configIdWorker() throws Exception {

		Long workerId = null;
		Long datacenterId = null;
		LinkedHashSet<String> localMacAddrIPv4Set = getLocalMacAddrIPv4();
		try {
			for (String macStr : localMacAddrIPv4Set) {
				Map<String, Object> map = mallSnowflakeDatacenterMapper.selectByMacAddress(macStr);

				if (map != null && !map.isEmpty()) {
					workerId = Long.valueOf(map.get("worker_id").toString());
					datacenterId = Long.valueOf(map.get("datacenter_id").toString());

					LogUtils.getLogger()
							.warn(LogUtils.getString("******************************************id生成器已经绑定mac地址=",
									macStr, "******************************************将使用workerId=", workerId,
									",datacenterId=", datacenterId));
					break;
				}
			}
			if (workerId == null || datacenterId == null) {
				throw new IllegalArgumentException("未绑定到数据中心!!!!!!!!!!!!");
			}
		} catch (Exception e) {
			LogUtils.getLogger().error("！！！！！！！！数据中心读取绑定异常！！！！！！！！", e);

			if (MallConstants.ACTIVE_PRD.equals(active)) {
				throw new IllegalArgumentException("生产环境必须绑定到id生成器");
			}

			workerId = IdWorker.maxWorkerId;
			long ranD = new Random().nextInt(((int) IdWorker.maxDatacenterId) + 1);
			datacenterId = ranD;

			for (int i = 0; i < 20; i++) {
				LogUtils.getLogger().warn(LogUtils.getString("!!!!!!!!!!!id生成器未绑定到数据中心!!!!!!!!!!!将使用workerId=",
						workerId, ",datacenterId=", ranD));
			}

		}

		return new SnowflakeIdWorker(workerId, datacenterId);
	}

	/**
	 * 
	 * @desc 获取本机所有IPV4的mac地址
	 * @auth wulm
	 * @date 2018年5月14日 下午2:34:10
	 * @return
	 * @throws Exception
	 */
	public static LinkedHashSet<String> getLocalMacAddrIPv4() throws Exception {
		LinkedHashSet<String> macSet = new LinkedHashSet<>();
		LinkedHashSet<String> ipSet = new LinkedHashSet<>();

		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface networkInterface = networkInterfaces.nextElement();
			Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			byte[] mac = networkInterface.getHardwareAddress();

			if (mac != null && mac.length > 0) {
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					if (inetAddress != null && inetAddress instanceof Inet4Address) {// IPV4
						macSet.add(DatatypeConverter.printHexBinary(mac));
						ipSet.add(inetAddress.getHostAddress());
						break;
					}
				}
			}

		}
		LogUtils.getLogger().warn(LogUtils.getString("******************************************本机所有IPV4网卡mac地址为",
				macSet, "******************************************mac地址对应ip地址为", ipSet));
		return macSet;
	}

}
