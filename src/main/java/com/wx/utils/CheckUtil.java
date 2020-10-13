package com.wx.utils;

import com.wx.security.SHA1;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 接入认证工具类
 */
public class CheckUtil {

    private static final String token = "xxyctoken4403";
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] str = new String[]{token,timestamp,nonce};
        //排序
        Arrays.sort(str);
        //拼接字符串
        StringBuffer buffer = new StringBuffer();
        for(int i =0 ;i<str.length;i++){
            buffer.append(str[i]);
        }
        //进行sha1加密
        String temp = SHA1.encode(buffer.toString());
        //与微信提供的signature进行匹对
        return signature.equals(temp);
    }

}
