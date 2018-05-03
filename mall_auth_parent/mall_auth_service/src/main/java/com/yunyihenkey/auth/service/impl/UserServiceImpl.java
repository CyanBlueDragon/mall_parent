package com.yunyihenkey.auth.service.impl;

import com.yunyihenkey.auth.service.UserService;
import com.yunyihenkey.basedao.malldb.basemapper.MallSysSellerUserBaseMapper;
import com.yunyihenkey.basedao.malldb.basevo.MallSysSellerUser;
import com.yunyihenkey.common.constant.MessageServerFactory;
import com.yunyihenkey.common.utils.RedisUtil;
import com.yunyihenkey.seller.dao.malldb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author LiarYang
 * @date 2018/5/3 10:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MallSysSellerUserBaseMapper mallSysSellerUserBaseMapper;


    @Override
    public String signUp(String phoneNumber, Integer type, Integer code, String extensionCoding) {
        String key = type + phoneNumber;
        if (extensionCoding == null) {
            Integer oldCode = (Integer) redisUtil.get(key);
            if (oldCode.equals(code)) {
                String mark = UUID.randomUUID().toString().replaceAll("-","");
                redisUtil.sSet(mark,phoneNumber);
                return mark;
            }
            return null;
        }



        return null;
    }



    @Override
    public String verificationCode(String phoneNumber,Integer type) {
        return MessageServerFactory.buildMessageServer().sendMessage(phoneNumber,type);
    }

    @Override
    public void saveUser(String password, String key, String cityCode) {
        MallSysSellerUser user = new MallSysSellerUser();
        String phoneNumber = (String) redisUtil.get(key);
        user.setPassword(password);
        user.setCityCode(cityCode);
        user.setMobile(phoneNumber);
        mallSysSellerUserBaseMapper.insertSelective(user);
        redisUtil.expire(key,0);
    }


    @Override
    public int verificationPhone(String phoneNumber) {

        return userMapper.verificationPhone(phoneNumber);
    }
}
