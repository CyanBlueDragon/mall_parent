package com.yunyihenkey.seller.service;

import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.common.vo.base.BaseService;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryListParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.UpdateStatus;

import java.util.List;

/**
 * @author : 007
 * @date : 2018/5/4 15:50
 */
public interface SellerUserService extends BaseService<SellerUser, Long> {
    SellerUser queryUserName(String  userName);

    SellerUser query(SellerUser sellerUser);

    List<SellerUser> queryUserList(Long shopId,String userName);

    /**
     * 保存员工与角色关系
     * @param roleId
     * @param shopId
     * @param userName
     * @param password
     * @param nickName
     * @param code
     * @param reqSource
     * @return
     * @throws Exception
     */
    ResultInfo<String> save(Long roleId,Long shopId,String userName, String password, String nickName,String code,String reqSource) throws  Exception;

    List<SellerUser> queryUserRoleList(QueryListParam queryListParam);

    /**
     * 修改用户状态
     * @param updateStatus
     * @throws Exception
     */
    ResultInfo updateStatus(UpdateStatus updateStatus) throws Exception;

}
