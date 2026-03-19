package com.company.Client.tools;

import com.company.Client.view.TalkingView;

import java.util.HashMap;

/**
 * @Title:
 * @Package
 * @Description: 管理不同User的聊天界面
 * @author: Yeeasy
 **/
public class TalkingViewManage {

    public static HashMap map=new HashMap<String,TalkingView>();

    public static void AddTalkingView(String ID,TalkingView talkingView){
        map.put(ID,talkingView);
    }

    public static TalkingView getTalkingView(String ID){
        return (TalkingView) map.get(ID);
    }

}
