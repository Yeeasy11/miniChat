package com.company.Client.tools;

import com.company.Client.model.UserThreads;

import java.util.HashMap;

/**
 * @Title:
 * @Package
 * @Description:管理客户端与服务器通信线程
 * @author: Yeeasy
 **/
public class UserThreadsManage {

    public static HashMap<String, UserThreads> map=new HashMap<>();

    public static void addUserThreads(String userID,UserThreads userThread){
        map.put(userID,userThread);
    }

    public static UserThreads getUserThreads(String userID){
        return  map.get(userID);
    }


}