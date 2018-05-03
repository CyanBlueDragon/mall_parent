package com.yunyihenkey.auth.dao.mapper;

import com.yunyihenkey.auth.dao.vo.MallSysSellerUser;

public interface MallSysSellerUserMapper {
    /**
     * 查询用户角色
     * @param id
     * @return
     */
    MallSysSellerUser selectUserRole(Long id);


}