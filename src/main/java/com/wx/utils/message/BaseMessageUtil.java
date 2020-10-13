package com.wx.utils.message;

/**
 *
 * 类名称: BaseMessageUtil
 * 类描述: 消息封装工具类的基类,这里采用泛型,方便后期功能扩展
 * @author yuanjun
 * 创建时间:2017年12月6日下午2:06:55
 */
public interface BaseMessageUtil <T>{

	public abstract  String messageToxml(T t);

	public abstract  String initMessage(String FromUserName,String ToUserName);
}
