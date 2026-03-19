package com.company.Server.model;

/**
 * @Title:
 * @Package
 * @Description: 服务器和客户端通信线程(对应UserThreads)
 * @author: Yeeasy
 **/

import com.company.Server.tools.ConnectThreadManage;
import com.company.Shared.Message;

import java.net.*;
import java.io.*;
import java.util.Iterator;

public class ConnectThread extends Thread {

    Socket socket;

    public ConnectThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        while (true) {
            try {
                //接收客户端信息
                ObjectInputStream in;
                ObjectOutputStream out;
                in = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) in.readObject();

                //根据MessageType进行处理
                if (msg.getMsgType().equals("talk")){
                    //获取聊天好友线程，转发信息到聊天好友talkingView
                    ConnectThread connectThread= ConnectThreadManage.getConnectThread(msg.getFriend());
                    out = new ObjectOutputStream(connectThread.socket.getOutputStream());
                    out.writeObject(msg);
                    System.out.println("聊天信息成功送达");

                }else if(msg.getMsgType().equals("onlineGet")){
                    // 获取在线好友ID字符串->接收ClientLogin
                    Message m=new Message();

                    String onlineFriends= ConnectThreadManage.getOnlineFriends();
                    m.setMsgType("onlineSet");
                    m.setUser(msg.getUser());
                    m.setContent(onlineFriends);

                    //发送到UserThreads
                    ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(m);
                    System.out.println("成功发送在线好友列表");
                }
               //错误！此处不需要！
//                TalkingView talkingView= TalkingViewManage.getTalkingView(msg.getUser()+" "+msg.getFriend());
//                talkingView.showMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //一个用户上线之后将上线信息通过UserThread发送到其他用户，更新在线好友列表
    public void onlineNotice(String userID){
        //得到在线用户线程
        Iterator iterator=ConnectThreadManage.map.keySet().iterator();
        //遍历，更新每个在线用户的好友列表，把这次登录用户改为在线
        while (iterator.hasNext()){
            Message msg=new Message();
            msg.setMsgType("onlineSet");
            msg.setContent(userID);   //需要点亮的图标
            String onlineUsers=iterator.next().toString(); //获取其他用户ID
            System.out.print("在线好友为"+onlineUsers+" ");
            msg.setUser(onlineUsers);
            Socket socket = ConnectThreadManage.getConnectThread(onlineUsers).socket; //
            try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
