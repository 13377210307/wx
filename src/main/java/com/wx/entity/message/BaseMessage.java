package com.wx.entity.message;

import lombok.Data;

import java.util.Date;

@Data
public class BaseMessage {

	protected String ToUserName;

	protected String FromUserName;

	protected Date CreateTime;

	protected String MsgType;

}
