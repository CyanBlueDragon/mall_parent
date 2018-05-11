package com.yunyihenkey.common.vo.base;

public interface BaseService<Vo, IdType> {

	int deleteByPrimaryKey(IdType id);

	int insert(Vo record);

	int insertSelective(Vo record);

	Vo selectByPrimaryKey(IdType id);

	int updateByPrimaryKeySelective(Vo record);

	int updateByPrimaryKey(Vo record);

}