package com.yunyihenkey.seller.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunyihenkey.auth.service.util.JwtUtils;
import com.yunyihenkey.auth.service.vo.authjwt.seller.AuthSellerUser;
import com.yunyihenkey.basedao.malldb.basevo.SellerUser;
import com.yunyihenkey.common.constant.MallConstants;
import com.yunyihenkey.common.constant.MessageServer;
import com.yunyihenkey.common.constant.RedisConstant;
import com.yunyihenkey.common.constant.SMSTemplateEnum;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.common.utils.ValidatorUtils;
import com.yunyihenkey.common.vo.base.BaseController;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.common.vo.resultinfo.CodeEnum;
import com.yunyihenkey.common.vo.resultinfo.ResultInfo;
import com.yunyihenkey.common.vo.resultinfo.SystemCodeEnum;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.GetCode;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.QueryListParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.SaveUserParam;
import com.yunyihenkey.seller.dao.malldb.vo.param.userController.UpdateStatus;
import com.yunyihenkey.seller.service.SellerUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @author HeXing
 * @desc 用户控制器
 * @date 2018/5/15 10:57
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private SellerUserService sellerUserService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ValidatorUtils validatorUtils;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MessageServer messageServer;

    /**
     * 查询员工用户列表 管理账户
     *
     * @return
     */
    @PostMapping("queryList")
    public Object queryList(@RequestBody QueryListParam queryListParam, HttpServletRequest request) {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(queryListParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        PageHelper.startPage(queryListParam.getPageNum(), queryListParam.getPageSize());
        AuthSellerUser jwtSellerUser = jwtUtils.getSellerUser(request);
        queryListParam.setShopId(Long.parseLong(jwtSellerUser.getShopId()));
        List<SellerUser> list = sellerUserService.queryUserRoleList(queryListParam);
        PageInfo<SellerUser> pageInfo = new PageInfo<SellerUser>(list);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 保存员工
     *
     * @param saveParam
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("save")
    public Object save(@RequestBody SaveUserParam saveParam, HttpServletRequest request) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(saveParam, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        AuthSellerUser jwtSellerUser = jwtUtils.getSellerUser(request);
        String reqSource = request.getHeader(MallConstants.HEADER_REQ_SOURCE);
        return sellerUserService.save(saveParam.getRoleId(), Long.parseLong(jwtSellerUser.getShopId()), saveParam.getPhone(), saveParam.getPassword(), saveParam.getNickName(), saveParam.getCode(), reqSource);

    }

    /**
     * 获取验证码
     *
     * @param getCode
     * @return
     * @throws Exception
     */
    @PostMapping("getCode")
    public Object getCode(@RequestBody GetCode getCode) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(getCode, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        String phone = getCode.getPhone();
        SMSResult smsResult = messageServer.sendMessage(phone, SMSTemplateEnum.UserRegistration);
        if (smsResult.isResult()) { //发送成功
            String code = smsResult.getCode();
            redisUtil.set(RedisConstant.REDIS_REGISTER_STAFF_CODE + phone, code);
            return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
        }
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.ERROR);
    }

    @PostMapping("updateStatus")
    public Object updateStatus(@RequestBody UpdateStatus updateStatus) throws Exception {
        String validateAndGetErrorInfo = validatorUtils.validateAndGetErrorInfo(updateStatus, Default.class);
        if (StringUtils.isNotBlank(validateAndGetErrorInfo)) {
            return new ResultInfo<Object>(SystemCodeEnum.SELLER, CodeEnum.ERROR, validateAndGetErrorInfo);
        }
        sellerUserService.updateStatus(updateStatus);
        return new ResultInfo<>(SystemCodeEnum.SELLER, CodeEnum.SUCCESS);
    }


}
