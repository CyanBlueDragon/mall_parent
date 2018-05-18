package com.yunyihenkey.basedao.malldb.commonMapper;

import java.util.List;
import java.util.Map;

public interface MallSnowflakeDatacenterMapper {

	Map<String, Object> selectByMacAddress(String macStr);

	List<Map<String, Object>> test();
}
