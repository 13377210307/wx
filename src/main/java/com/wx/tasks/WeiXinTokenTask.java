package com.wx.tasks;

import com.wx.utils.menu.MenuUtil;
import com.wx.utils.token.WeiXinTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeiXinTokenTask {

    @Autowired
    private WeiXinTokenUtil weixinCommenUtil;

    @Autowired
    private MenuUtil menuUtil;

    private String token = "38_j2PqGxZUCH9YnHRUW87jH10JSYyadO0sk0qRDjNkABbAy9aYSenqLscRvCg4ruqHUQHQoswGF-E39wPkxuBeGZj0AyoU4GDTYJnfpbjMxQpiwJCwvldIk8z8tN28arV6OdeGw1OdvCHgIxi4VRXhADAMIO";


    // 第一次延迟1秒执行，当执行完后7100秒再执行
    @Scheduled(initialDelay = 1000, fixedDelay = 5*1000*3600 )
    public void getWeixinAccessToken(){
        try {
            /*String token = weixinCommenUtil.getToken().getToken();
            System.out.println(token);*/
            // 删除菜单
            //this.menuUtil.deleteMenu(token);
            /*String menu = this.menuUtil.initMenu();
            this.menuUtil.createMenu(token,menu);*/
        } catch (Exception e) {
            e.printStackTrace();
            this.getWeixinAccessToken();
        }
    }

}
