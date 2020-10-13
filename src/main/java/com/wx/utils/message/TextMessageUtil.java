package com.wx.utils.message;

import com.thoughtworks.xstream.XStream;
import com.wx.entity.message.MessageText;

import java.util.Date;

public class TextMessageUtil{
	/**
	 * 将发送消息封装成对应的xml格式
	 */
	public  String messageToxml(MessageText message) {
		XStream xstream  = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	/**
	 * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
	 * @param FromUserName
	 * @param ToUserName
	 */
	public String initMessage(String FromUserName, String ToUserName, String type) {
		MessageText text = new MessageText();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		if ("1".equals(type)) {
			text.setContent("如果我是dj你会爱我吗？");
		}else if ("2".equals(type)) {
			text.setContent("您真是个大帅逼");
		}else if("您好".equals(type)) {
			text.setContent("您看我像是一个好人吗？");
		}
		text.setCreateTime(new Date());
		text.setMsgType("text");
		System.out.println("222222222"+text);
	    return messageToxml(text);
	}

}
