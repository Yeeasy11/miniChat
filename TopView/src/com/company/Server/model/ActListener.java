package com.company.Server.model;

import com.company.Server.tools.ConnectThreadManage;
import com.company.Shared.Users;

import java.net.*;
import java.io.*;
import com.company.Shared.*;

/**
 * @Title:
 * @Package
 * @Description: 服务器核心，监听客户端
 * @author: Yeeasy
 **/
public class ActListener {


    public ActListener() {
        //使用对象流
        try {
            ServerSocket server = new ServerSocket(1111);

            while (true) {
                Socket socket = server.accept();

                //接收ConnectToServer信息，判断是否登录成功
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Users u = (Users) in.readObject();
                Message msg = new Message();
                System.out.println("服务器接收ID与密码：" + u.getUserID() + "和" + u.getUserPW());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                if (u.getUserType().equals("LoginUser")) {
                    String password = sqlCenter.getPassword(u.getUserID());
                    //判断登录是否合法
                    if (u.getUserPW().equals(password)) {

                        msg.setMsgType("success");
                        System.out.println("用户登录密码正确，登录成功");
                        out.writeObject(msg);

                        //登录成功->创建线程与客户端通信
                        ConnectThread connectThread = new ConnectThread(socket);
                        ConnectThreadManage.addConnectThread(u.getUserID(), connectThread);
                        connectThread.start();
                        System.out.println("服务器与客户端通信线程创建成功");

                        //登录成功->发送上线信息，其他用户更新好友列表
                        connectThread.onlineNotice(u.getUserID());
                        System.out.println("好友列表更新成功");

                    } else {
                        msg.setMsgType("fail");
                        out.writeObject(msg);
                        socket.close();
                    }
                }
                if(u.getUserType().equals("NewUser")){
                    sqlCenter.insertDB(u.getUserID(),u.getUserPW());
                    msg.setMsgType("NewUser");
                }
            }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
            }
        }
}
