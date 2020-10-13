package com.wx.entity.message;

import com.wx.entity.message.BaseMessage;
import lombok.Data;

import java.util.Date;

@Data
public class MessageText extends BaseMessage {

	private String Content;//文本消息内容

	private String MsgId;//消息id，64位整型

	public MessageText(){

	}


	public MessageText(String toUserName, String fromUserName, Date createTime, String msgType, String content, String msgId) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Content = content;
		MsgId = msgId;
	}



}
