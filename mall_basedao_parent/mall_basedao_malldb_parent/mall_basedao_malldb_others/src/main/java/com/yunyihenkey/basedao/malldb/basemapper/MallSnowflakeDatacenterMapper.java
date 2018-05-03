package com.yunyihenkey.basedao.malldb.basemapper;

import java.util.Map;

public interface MallSnowflakeDatacenterMapper {

	Map<String, Object> selectByMacAddress(String macStr);

}
