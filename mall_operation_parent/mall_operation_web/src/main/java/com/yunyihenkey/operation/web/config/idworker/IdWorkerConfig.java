package com.yunyihenkey.operation.web.config.idworker;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yunyihenkey.basedao.malldb.commonMapper.MallSnowflakeDatacenterMapper;
import com.yunyihenkey.common.idworker.IdWorker;
import com.yunyihenkey.common.idworker.SnowflakeIdWorker;
import com.yunyihenkey.common.utils.LogUtils;

@Configuration
public class IdWorkerConfig {

	@Autowired
	private MallSnowflakeDatacenterMapper mallSnowflakeDatacenterMapper;

	@Bean
	public IdWorker configIdWorker() {

		long workerId;
		long datacenterId;
		try {
			InetAddress ia = InetAddress.getLocalHost();
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			String macStr = DatatypeConverter.printHexBinary(mac);

			Map<String, Object> map = mallSnowflakeDatacenterMapper.selectByMacAddress(macStr);

			if (map != null && !map.isEmpty()) {
				workerId = Long.valueOf(map.get("worker_id").toString());
				datacenterId = Long.valueOf(map.get("datacenter_id").toString());

				LogUtils.getLogger().warn(LogUtils.getString("!!!!!!!!!!!id生成器!!!!!!!!!!!将使用workerId=", workerId,
						",datacenterId=", datacenterId));
			} else {
				throw new IllegalArgumentException("未绑定到数据中心!!!!!!!!!!!!");
			}

		} catch (Exception e) {
			LogUtils.getLogger().error("！！！！！！！！数据中心读取绑定异常！！！！！！！！", e);

			workerId = IdWorker.maxWorkerId;
			long ranD = new Random().nextInt(((int) IdWorker.maxDatacenterId) + 1);
			datacenterId = ranD;

			for (int i = 0; i < 50; i++) {
				LogUtils.getLogger().warn(LogUtils.getString("!!!!!!!!!!!id生成器未绑定到数据中心!!!!!!!!!!!将使用workerId=",
						workerId, ",datacenterId=", ranD));
			}

		}

		return new SnowflakeIdWorker(workerId, datacenterId);
	}

}
