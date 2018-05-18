package com.yunyihenkey.seller.service;

import com.yunyihenkey.common.constant.SMSTemplateEnum;
import com.yunyihenkey.common.vo.base.SMSResult;
import com.yunyihenkey.seller.dao.malldb.vo.result.user.DistributorTypeResult;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiarYang
 * @date 2018/5/3 10:43
 */
public interface UserService {

    /**
     * 注册
     * @param phoneNumber
     * @param type
     * @param code
     * @param extensionCoding 推广编码
     * @return
     */
    //有推广编码调用
    DistributorTypeResult signUp(String phoneNumber, Integer type, String code , String extensionCoding);
    //有注册编码调用
    DistributorTypeResult signUpV2(String phoneNumber, Integer type, String code , String extensionCoding);
    //推广编码与注册码为空调用
    DistributorTypeResult signUpV1(String phoneNumber, Integer type, String code );

    /**
     * 获取验证码
     * @param phoneNumber
     * @return
     */
    SMSResult verificationCode(String phoneNumber,SMSTemplateEnum smsTemplateEnum);

    /**
     * 保存用户
     * @param password 密码
     * @param key 匹配手机号码标识
     * @param cityCode 城市区号
     */
    boolean saveUser(String password,String phoneNumber ,String cityCode,String provinceCode,HttpServletRequest request);

    /**
     * 验证手机号码是否存在
     * @param phoneNumber
     * @return
     */
    int verificationPhone(String phoneNumber);

    /**
     * 忘记密码
     * @param phoneNumber
     * @param newPassword 新密码
     * @return
     */
    int forgetPassword(String phoneNumber,String newPassword);

    void addRegistrationCode(List set);

    /**
     * 验证短信验证码
     * @param phoneNumber
     * @param type
     * @param code
     * @return
     */
    int checkoutCode(String phoneNumber,Integer type, String code);
}
