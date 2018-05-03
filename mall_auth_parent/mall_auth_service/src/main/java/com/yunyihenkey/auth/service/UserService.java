package com.yunyihenkey.auth.service;

/**
 * @author LiarYang
 * @date 2018/5/3 10:43
 */
public interface UserService {

    String signUp(String phoneNumber,Integer type,Integer code ,String extensionCoding);

    String verificationCode(String phoneNumber,Integer type);

    void saveUser(String password,String key ,String cityCode);

    int verificationPhone(String phoneNumber);

}
