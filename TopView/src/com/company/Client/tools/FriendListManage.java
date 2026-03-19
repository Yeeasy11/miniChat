package com.company.Client.tools;

import com.company.Client.view.FriendList;

import java.util.HashMap;

/**
 * @Title:
 * @Package
 * @Description:管理好友列表
 * @author: Yeeasy
 **/
public class FriendListManage {
    private static HashMap map=new HashMap<String,FriendList>();

    public FriendListManage(){ }
    public static void addFriendList(String userID, FriendList FriendList) {
        map.put(userID,FriendList);
    }

    public static FriendList getFriendList(String userID) {
        return (FriendList) map.get(userID);
    }
}
