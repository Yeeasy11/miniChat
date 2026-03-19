package com.company.Client.model;

import com.company.Shared.Users;

/**
 * @Title:
 * @Package
 * @Description: 登录，用户连接服务器
 * @author: Yeeasy
 **/
public class LogginUser {
    public boolean UserCheck(Users user){
        boolean check=new ConnectToServer().UserConnectTO(user);
        return check;
    }

    public  boolean NewUserCheck(Users user){
        boolean check=new ConnectToServer().UserConnectTO(user);
        return check;
    }
}
