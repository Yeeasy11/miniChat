package com.company.Client.model;

import com.company.Client.tools.FriendListManage;
import com.company.Client.tools.TalkingViewManage;
import com.company.Client.view.FriendList;
import com.company.Client.view.TalkingView;
import com.company.Shared.Message;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Title:
 * @Package
 * @Description:客户端连接到服务器线程(对应ConnectThread)
 * @author: Yeeasy
 **/
public class UserThreads extends Thread {

    private Socket socket;
    ObjectInputStream in;

    public UserThreads(Socket socket){
        this.socket=socket;
    }

    public void run() {
        while (true) {

            try {
                //接收ConnectThread来的信息
                in = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) in.readObject();


                if(msg.getMsgType().equals("onlineSet")){
                    //修改用户列表好友在线状态
                    System.out.println("在线好友为"+msg.getContent());
                    FriendList friendList= FriendListManage.getFriendList(msg.getUser());
                    System.out.println(friendList);
                    if(friendList!=null){
                        friendList.UpdateFriendList(msg);
                    }

                }else if(msg.getMsgType().equals("talk")){

                    System.out.println( msg.getUser()+"说：" + msg.getContent());
                    TalkingView talkingView= TalkingViewManage.getTalkingView(msg.getFriend()+" "+msg.getUser());
                    talkingView.showMessage(msg);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Socket getSocket() {
        return socket;
    }

}
