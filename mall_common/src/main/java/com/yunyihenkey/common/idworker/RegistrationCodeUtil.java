package com.yunyihenkey.common.idworker;

import java.util.HashSet;
import java.util.Random;

/**
 * @author LiarYang
 * @date 2018/5/9 10:48
 */
public class RegistrationCodeUtil {

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        while (set.size() >= 1000000) {
            String a = getRandomString(8);
            set.add(a);
        }

    }
}
