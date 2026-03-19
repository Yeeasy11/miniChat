package com.company.Client.model;

/**
 * @Title:
 * @Package
 * @Description: 客户端连接到服务器
 * @author: Yeeasy
 **/
import com.company.Client.tools.*;
import com.company.Shared.Message;

import java.io.*;
import java.net.*;
import com.company.Shared.*;

public class ConnectToServer {

    public Socket socket;
    public ConnectToServer(){

    }

    //发送登录请求->ActListener
    public boolean UserConnectTO(Object o){
        Boolean judge=false; //判断登录/注册是否成功
        try{
            //本机自连接
            this.socket=new Socket("127.0.0.1",1111);
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(o);
            Users user=(Users)o;   //传入object为user，类型转换！！
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            Message msg=(Message) in.readObject();

            if(msg.getMsgType().equals("NewUser")){
                judge=true;
                return judge;
            }

            if(msg.getMsgType().equals("success")){
                //创建客户与服务器通信线程
                UserThreads userThread=new UserThreads(socket);
                userThread.start();
                UserThreadsManage.addUserThreads(user.getUserID(),userThread);
                System.out.println("登录成功");
                judge=true;
            }else{
                socket.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return judge;
    }
}
