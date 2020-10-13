package com.wx.utils.token;

import com.wx.entity.AccessToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeiXinTokenUtil {

    @Autowired
    private HttpRequestUtil httpRequestUtil;

    // 获取access_token的接口地址（GET） 限2000（次/天）
    private static String url= "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static String appid = "wx52fa1771b64de26a";

    private static String appsecret = "49c8d36e2f6363bade100788690a39cd";

    public AccessToken getToken(){

        AccessToken token=null;
        //访问微信服务器的地址
        String requestUrl=url.replace("APPID", appid).replace("APPSECRET", appsecret);
        //HttpRequestUtil httpRequestUtil=new HttpRequestUtil();
        //创建一个json对象
        JSONObject json =httpRequestUtil.httpsRequest(requestUrl,"GET",null);
        System.out.println("获取到的json格式的Token为:"+json);
        //判断json是否为空
        if (json!=null){

            try{
                token=new AccessToken();
                //将获取的access_token放入accessToken对象中
                token.setToken(json.getString("access_token"));
                //将获取的expires_in时间放入accessToken对象中
                token.setExpiresIn(json.getInt("expires_in"));
            }
            catch (Exception e){
                token=null;
                e.printStackTrace();
                System.out.println("系统异常！");
            }
        }else {
            token=null;
            // 获取token失败
            System.out.println("获取token失败");
        }
        return token;

    }
}
