package com.company.Server.tools;

import com.company.Server.model.ConnectThread;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Title:
 * @Package
 * @Description: 管理不同客户端与服务器socket,并且开线程，查看用户在线否
 * @author: Yeeasy
 **/
public class ConnectThreadManage {

    public static HashMap map=new HashMap<String, ConnectThread>();    //静态！

    //把线程添加到HashMap
    static public void addConnectThread(String userID, ConnectThread connectThread){
        map.put(userID,connectThread);
    }

    //拿到线程
    static public ConnectThread getConnectThread(String userID){
        return (ConnectThread) map.get(userID);
    }

    //返回在线好友
    public static String getOnlineFriends(){
        Iterator iterator=map.keySet().iterator();
        String onlineFriends="";
        while (iterator.hasNext()){
            onlineFriends+=iterator.next()+" ";    //将在线好友ID拼接为字符串进行通信
        }
        return onlineFriends;
    }
}
