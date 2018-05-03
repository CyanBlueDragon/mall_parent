package com.yunyihenkey.common.constant;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yunyihenkey.common.utils.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/*
 * @author LiarYang
 * @date 2018/4/28 10:43
*/

@Component
public class MessageServer {
    @Resource
    private RedisUtil redisUtil;

    public String sendMessage(String phoneNumber ,Integer type){
        System.setProperty("sun.net.client.defaultConnectTimeout", "20000");
        System.setProperty("sun.net.client.defaultReadTimeout", "20000");
        // 初始化ascClient需要的几个参数
        final String product = "Dysmsapi";// 短信API产品名称
        final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名
        // 替换成你的AK
        final String accessKeyId = "LTAIPALNbKV3HrXh";// 你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "39Slnvmb3SgbeP0cPZQ8LSOyiGt2j8";// 你的accessKeySecret，参考本文档步骤2
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
                    domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumber);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("阿里云短信测试专用");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(SMSTemplateEnum.getByValue(type).getText());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        int x = new Random().nextInt(1000000);
        String  template = null;//根据不用类型选择不同的短信模板
        switch (type){
            case 1:
                template =  "{\"code\":\"" + x + "\"}";
                break;
        }
        request.setTemplateParam(template);
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        // 请求失败这里会抛ClientException异常

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse != null) {
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                // 请求成功
                String key = type + phoneNumber;
                if (redisUtil.hasKey(key)) {
                    redisUtil.expire(key,0);
                }
                    redisUtil.set(key,60,x);
                    return sendSmsResponse.getCode();
                }
                return sendSmsResponse.getCode();
            }
            return null;
        }




    public static void main(String[] args) {
       // new MessageServer().sendMessage("13267077260",1);
    }
}
