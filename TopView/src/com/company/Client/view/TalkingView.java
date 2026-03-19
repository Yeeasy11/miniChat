package com.company.Client.view;
import com.company.Client.model.UserThreads;
import com.company.Client.tools.UserThreadsManage;
import com.company.Shared.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @Title:
 * @Package
 * @Description: 显示好友聊天窗口
 * @author: Yeeasy
 **/
public class TalkingView extends JFrame implements ActionListener{

    JPanel TalkPanel;
    JTextField TalkField;
    JTextArea TalkArea;
    JButton TalkButton;
    String user;
    String friend;
    String nowTime;

    ObjectInputStream in;
    ObjectOutputStream out;

    public static void main(String[] args) {
        TalkingView View=new TalkingView("0","0");

    }

    public TalkingView(String userID,String friendID){

        setSize(450,400);
        setLocationRelativeTo(null);  //居中

        TalkPanel=new JPanel();
        TalkArea=new JTextArea();
        TalkField=new JTextField(15); //最多放下15个字
        TalkButton=new JButton("发送");

        TalkPanel.add(TalkField);
        TalkPanel.add(TalkButton);

        //监听TalkButton
        TalkButton.addActionListener(this);

        this.user=userID;
        this.friend=friendID;

        this.add(TalkArea, BorderLayout.CENTER);
        this.add(TalkPanel,BorderLayout.SOUTH);
        this.setTitle(userID+"正在和"+friendID+"号朋友聊天");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //JFrame执行关闭操作时，退出程序
        this.setResizable(false);
        this.setVisible(true);

    }

    //在聊天界面展示信息
    public void showMessage(Message msg) {
        String info = msg.getUser() +"对你"+  "说:" + msg.getContent() + "\n";
        this.TalkArea.append(msg.getNowTime()+"\n");
        this.TalkArea.append(info);
    }

    @Override
    public void actionPerformed(ActionEvent E) {

        if(E.getSource()==TalkButton){
            //点击发送，通信->userThread
            Message msg=new Message();
            msg.setUser(this.user);
            msg.setFriend(this.friend);
            msg.setMsgType("talk");
            msg.setContent(TalkField.getText());
            this.nowTime=new Date().toString();
            msg.setNowTime(this.nowTime);
            try {
                UserThreads userThread= UserThreadsManage.getUserThreads(msg.getUser());
                Socket socket=userThread.getSocket();
                out=new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(msg);
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }
    }


}
