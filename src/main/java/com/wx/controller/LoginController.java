package com.wx.controller;

import com.wx.entity.AccessToken;
import com.wx.utils.CheckUtil;
import com.wx.utils.message.MessageUtil;
import com.wx.utils.message.TextMessageUtil;
import com.wx.utils.token.WeiXinTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private WeiXinTokenUtil weiXinTokenUtil;



    /**
     * 获取签名、时间戳、随机数、随机字符串
     * 将随机字符串返回进行验证
     */
    @GetMapping
    public String getWechar(@RequestParam String signature,
                          @RequestParam String timestamp,
                          @RequestParam String nonce,
                          @RequestParam String echostr) {
        // 验证接入
        boolean result = CheckUtil.checkSignature(signature, timestamp, nonce);
        if (result) {
            return echostr;
        }else {
            return null;
        }
    }

    /**
     * 测试
     */
    @PostMapping
    public void test1(HttpServletRequest request,HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");

        String message = null;
        //处理文本类型，实现输入1，回复相应的封装的内容
        if("text".equals(MsgType)){
            TextMessageUtil textMessage = new TextMessageUtil();
            System.out.println(Content);
            message = textMessage.initMessage(FromUserName, ToUserName, Content);
        }
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.close();

    }

    /**
     * 获取accessToken
     */
    @GetMapping("/getToken")
    public void getToken() {
        AccessToken accessToken = weiXinTokenUtil.getToken();
        System.out.println(accessToken.getToken());
    }

}
