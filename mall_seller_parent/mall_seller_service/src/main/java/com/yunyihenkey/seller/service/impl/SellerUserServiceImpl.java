package com.yunyihenkey.seller.service.impl;

import com.yunyihenkey.auth.service.util.PasswordUtil;
import com.yunyihenkey.basedao.malldb.basemapper.SellerRoleBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basemapper.SellerUserRoleBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.SellerRole;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerUserRole;
import com.yunyihenkey.basedao.malldb.basevoEnum.SellerUser.UserTypeEnum;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.idworker.SnowflakeIdWorker;
import com.yunyihenkey.common.utils.DateUtil;
import com.yunyihenkey.common.utils.LogUtils;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.mapper.SellerUserMapper;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryListParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.DeleteUserParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.UpdateUserParam;
import com.yunyihenkey.seller.service.SellerUserBakService;
import com.yunyihenkey.seller.service.SellerUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : 007
 * @date : 2018/5/4 15:55
 */
@Service
public class SellerUserServiceImpl implements SellerUserService {
    @Autowired
    private SellerUserBaseMapper sellerUserBaseMapper;
    @Autowired
    private SellerUserMapper sellerUserMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SellerRoleBaseMapper sellerRoleBaseMapper;
    @Autowired
    private SellerUserRoleBaseMapper sellerUserRoleBaseMapper;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private SellerUserBakService sellerUserBakService;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return sellerUserBaseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SellerUser record) {
        return sellerUserBaseMapper.insert(record);
    }

    @Override
    public int insertSelective(SellerUser record) {
        return sellerUserBaseMapper.insertSelective(record);
    }

    @Override
    public SellerUser selectByPrimaryKey(Long id) {
        return sellerUserBaseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SellerUser record) {
        return sellerUserBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SellerUser record) {
        return sellerUserBaseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SellerUser> queryUserList(Long shopId, String userName) {
        SellerUser sellerUser = new SellerUser();
        sellerUser.setShopId(shopId);
        sellerUser.setUserName(userName);
        return sellerUserMapper.queryUserList(sellerUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResultInfo<String> save(Long pId, Long roleId, Long shopId, String userName, String password, String nickName, String code, String reqSource) throws Exception {
        String key = RedisConstant.REDIS_REGISTER_STAFF_CODE + userName;
        String rCode = (String) redisUtil.get(key);
        if (StringUtils.isBlank(rCode)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码失效,请重试", null);
        }
        if (!StringUtils.equalsAny(code, rCode)) {
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "验证码不正确", null);
        }
        if (queryUserName(userName) != null) {
            LogUtils.getLogger().info("用户已经注册" + userName);
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "用户已经注册", null);
        }
        SellerRole sellerRole = sellerRoleBaseMapper.selectByPrimaryKey(roleId);
        if (sellerRole == null) {
            LogUtils.getLogger().info("用户角色ID不存在" + roleId);
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR, "用户角色ID不存在", null);
        }

        SellerUser sellerUser = new SellerUser();
        sellerUser.setUserName(userName);
        sellerUser.setPassword(PasswordUtil.enCode(userName, password));
        sellerUser.setNickName(nickName);
        sellerUser.setShopId(shopId);
        Long id = snowflakeIdWorker.nextId();
        sellerUser.setId(id);
        sellerUser.setMobile(userName);
        sellerUser.setRegisterSource(reqSource);
        sellerUser.setCreateTime(DateUtil.getCurrentDate());
        sellerUser.setParentUserId(pId);
        sellerUser.setUserType(UserTypeEnum.STAFF.getValue()); //员工
        sellerUserBaseMapper.insertSelective(sellerUser);

        SellerUserRole sellerUserRole = new SellerUserRole();
        sellerUserRole.setRoleId(roleId);
        sellerUserRole.setUserId(id);
        sellerUserRoleBaseMapper.insertSelective(sellerUserRole);

        redisUtil.del(key);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }

    @Override
    public List<SellerUser> queryUserRoleList(QueryListParam queryListParam) {
        return sellerUserMapper.queryUserRoleList(queryListParam);
    }

    @Override
    public SellerUser query(SellerUser sellerUser) {
        List<SellerUser> list = sellerUserMapper.queryUserList(sellerUser);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public SellerUser queryUserName(String userName) {
        SellerUser sellerUser = new SellerUser();
        sellerUser.setUserName(userName);
        return query(sellerUser);
    }

   /* @Override
    public ResultInfo updateStatus(Updatestat updateStatus) throws Exception {
        Integer status = updateStatus.getStatus();
        Long id = updateStatus.getId();
        SellerUser sellerUser = sellerUserBaseMapper.selectByPrimaryKey(id);
        if (sellerUser == null) {
            return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "用户不存在");
        }
        if (sellerUser.getStatus() == updateStatus.getStatus()) {
            LogUtils.getLogger().info("用户的状态不需要修改");
            return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
        }
        SellerUser sellerUserUp = new SellerUser();
        sellerUserUp.setId(id);
        sellerUserUp.setStatus(status);
        sellerUserBaseMapper.updateByPrimaryKeySelective(sellerUserUp);
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }*/

    @Override
    public ResultInfo update(UpdateUserParam updateUserParam) throws Exception {
        Long id = updateUserParam.getId();
        SellerUser sellerUser = sellerUserBaseMapper.selectByPrimaryKey(id);
        if (sellerUser == null) {
            LogUtils.getLogger().info("用户的id不存在");
            return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "用户的id不存在");
        }
        SellerUser sellerUserUp = new SellerUser();
        sellerUserUp.setId(id);
        sellerUserUp.setNickName(updateUserParam.getNickName());
        sellerUserUp.setPassword(PasswordUtil.enCode(sellerUser.getUserName(), updateUserParam.getPassword()));
        sellerUserUp.setUpdateTime(DateUtil.getCurrentDate());
        sellerUserBaseMapper.updateByPrimaryKeySelective(sellerUserUp);
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "修改成功");
    }

    @Transactional
    @Override
    public ResultInfo deleteUser(DeleteUserParam deleteUserParam) throws Exception {
        Long id = deleteUserParam.getId();
        if (selectByPrimaryKey(id) == null) {
            LogUtils.getLogger().info("用户的id不存在");
            return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.ERROR, "用户不存在");
        }
        //备份数据
        sellerUserBakService.save(id);
        //删除数据
        deleteByPrimaryKey(id);
        return new ResultInfo(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, "注销成功");
    }
}
