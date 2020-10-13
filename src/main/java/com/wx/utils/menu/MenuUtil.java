package com.wx.utils.menu;

import com.wx.entity.button.Button;
import com.wx.entity.button.ClickButton;
import com.wx.entity.button.ViewButton;
import com.wx.entity.menu.MyMenu;
import com.wx.utils.WeixinUtil;
import com.wx.utils.token.WeiXinTokenUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * 类名称: MemuUtil
 * 类描述: 菜单工具
 * @author yuanjun
 * 创建时间:2017年12月8日下午8:42:15
 */
@Component
public class MenuUtil {

	@Autowired
	private WeixinUtil weixinUtil;

	@Autowired
	private WeiXinTokenUtil weiXinTokenUtil;

	// 闯将菜单接口
	private static final String CTRATE_MENU_URL  = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	//删除菜单接口
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";


	/**
	 * 创建菜单
	 * @param accessToken
	 * @param Menu 菜单json格式字符串
	 * @return
	 */
	public int createMenu(String accessToken,String Menu){
		int result = Integer.MIN_VALUE;
		String url = CTRATE_MENU_URL.replaceAll("ACCESS_TOKEN", accessToken);
		JSONObject json = weixinUtil.doPoststr(url, Menu);
		if(json!=null){
			//从返回的数据包中取数据{"errcode":0,"errmsg":"ok"}
			result = json.getInt("errcode");
			System.out.println(json);
		}
		return result;
	}

	public String initMenu(){
		String result = "";

		// 政策法规按钮
		ViewButton policyButton = new ViewButton();
		policyButton.setName("政策法规");
		policyButton.setType("view");
		policyButton.setUrl("https://www.baidu.com");
		// 通知公告按钮
		ViewButton noticeButton = new ViewButton();
		noticeButton.setName("政策法规");
		noticeButton.setType("view");
		noticeButton.setUrl("https://www.baidu.com");


		//创建点击一级菜单
		// 一键举报
		ViewButton reportButton = new ViewButton();
		reportButton.setName("一键举报");
		reportButton.setType("view");
		reportButton.setUrl("https://www.baidu.com");
		// 在线咨询
		ViewButton questButton = new ViewButton();
		questButton.setName("在线咨询");
		questButton.setType("view");
		questButton.setUrl("https://www.baidu.com");


		//创建其他类型的菜单与click型用法一致
		// 一键举报
		ViewButton declareButton = new ViewButton();
		declareButton.setName("申报点信息查询");
		declareButton.setType("view");
		declareButton.setUrl("https://www.baidu.com");
		// 疫病预警信息查询
		ViewButton diseaseButton = new ViewButton();
		diseaseButton.setName("疫病预警信息查询");
		diseaseButton.setType("view");
		diseaseButton.setUrl("https://www.baidu.com");
		// 一键举报
		ViewButton checkButton = new ViewButton();
		checkButton.setName("检疫合格证信息查询");
		checkButton.setType("view");
		checkButton.setUrl("https://47.101.223.16:8031/noticewx");

		//封装到一级菜单
		Button button1 = new Button();
		button1.setName("政策法规");
		//button1.setType("click");
		button1.setSub_button(new Button[]{policyButton,noticeButton});

		//封装到一级菜单
		Button button2 = new Button();
		button2.setName("在线举报");
		//button2.setType("click");
		button2.setSub_button(new Button[]{reportButton,questButton});

		//封装到一级菜单
		Button button3 = new Button();
		button3.setName("信息公开");
		//button3.setType("click");
		button3.setSub_button(new Button[]{declareButton,diseaseButton,checkButton});

		//封装菜单
		MyMenu menu = new MyMenu();
		menu.setButton(new Button[]{button1,button2,button3});
		return JSONObject.fromObject(menu).toString();
	}


	/**
	 * 删除菜单接口
	 */
	public boolean deleteMenu(String token) {
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject result = weixinUtil.doGetstr(url);
		if ("ok".equals(result.getString("errmsg"))) {
			System.out.println("删除菜单失败");
			return true;
		}
		System.out.println("删除菜单成功");
		return false;
	}

}
