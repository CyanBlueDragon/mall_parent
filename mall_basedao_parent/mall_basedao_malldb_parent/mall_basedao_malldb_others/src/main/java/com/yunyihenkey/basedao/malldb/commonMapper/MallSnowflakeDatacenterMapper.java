package com.yunyihenkey.basedao.malldb.commonMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MallSnowflakeDatacenterMapper {

	Map<String, Object> selectByMacAddress(String macStr);

	List<Map<String, Object>> test();
}
