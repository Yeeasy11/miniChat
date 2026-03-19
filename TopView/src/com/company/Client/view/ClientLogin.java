package com.company.Client.view;

import com.company.Client.model.LogginUser;
import com.company.Client.tools.FriendListManage;
import com.company.Client.model.UserThreads;
import com.company.Client.tools.UserThreadsManage;
import com.company.Shared.Message;
import com.company.Shared.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Title:
 * @Package
 * @Description: 登录主界面
 * @author: Yeeasy
 **/

public class ClientLogin extends JFrame implements ActionListener {
    //UP
    JPanel Pup;
    JLabel Lup;

    //MIDDLE
    JPanel Pmid;
    JLabel Lmid1,Lmid2;
    JTextField Tmid;
    JPasswordField PWmid;

    //DOWN
    JPanel Pdown;
    JButton PdownJb1,PdownJb2,PdownJb3;

    public static void main(String[] args){
        ClientLogin login=new ClientLogin();
    }

    public ClientLogin(){

        setSize(300,300);
        setLocationRelativeTo(null);  //居中

        //UP
        Pup=new JPanel();
        Lup=new JLabel("WELCOME TO TVtalk",0);
        Pup.add(Lup);

        //MIDDLE，登录面板
        Pmid=new JPanel(new FlowLayout(FlowLayout.LEADING,40,30));
        Lmid1=new JLabel("账号:",0);
        Lmid2=new JLabel("密码:",0);
        Tmid=new JTextField(15); //最多放下15个字
        Tmid.setPreferredSize(new Dimension(80,25));
        PWmid=new JPasswordField(15);
        Tmid.setPreferredSize(new Dimension(80,25));

        Pmid.add(Lmid1);
        Pmid.add(Tmid);
        Pmid.add(Lmid2);
        Pmid.add(PWmid);

        //DOWN，按钮
        Pdown=new JPanel();
        PdownJb1=new JButton("找回密码");
        PdownJb2=new JButton("登    录");
        PdownJb3=new JButton("注    册");
        Pdown.add(PdownJb1);
        Pdown.add(PdownJb2);
        Pdown.add(PdownJb3);

        PdownJb2.addActionListener(this);   //按钮响应
        PdownJb3.addActionListener(this);

        this.add(Pup,BorderLayout.NORTH);
        this.add(Pmid,BorderLayout.CENTER);
        this.add(Pdown,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //JFrame执行关闭操作时，退出程序
        this.setResizable(false); //窗口不可自由改变大小
        this.setVisible(true);  //默认可见

        System.out.println("登录界面启动");

    }

    @Override
    public void actionPerformed(ActionEvent E) {

        //点击PdownJb2登陆
        if(E.getSource()==PdownJb2){

            //发送登录请求->LogginUser->ConnectToServer->ActListener
            LogginUser logginUser=new LogginUser();
            Users u=new Users();
            u.setUserID(Tmid.getText().trim());
            u.setUserPW(new String(PWmid.getPassword()));      //密码是一个字符串
            u.setUserType("LoginUser");

            //成功则进入FriendList界面,失败跳出对话框提示
            if(logginUser.UserCheck(u)){
                try {
                    FriendList friendList=new FriendList(u.getUserID());
                    FriendListManage.addFriendList(u.getUserID(),friendList);
                    System.out.println("好友列表启动");
                    
                    //更新在线好友列表->connectThread
                    Message msg=new Message();
                    msg.setUser(u.getUserID());
                    msg.setMsgType("onlineGet");
                    UserThreads userThread= UserThreadsManage.getUserThreads(u.getUserID());
                    Socket socket=userThread.getSocket();
                    ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(msg);
                    System.out.println("成功申请获取在线好友列表");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //关闭登录界面
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"登录失败，请检查个人信息");
            }
        }
        if(E.getSource()==PdownJb3){
            LogginUser logginUser=new LogginUser();
            Users u=new Users();
            u.setUserID(Tmid.getText().trim());
            u.setUserPW(new String(PWmid.getPassword()));//密码是一个字符串
            JOptionPane.showMessageDialog(this, "注册成功");
            this.dispose();
            u.setUserType("NewUser");
            logginUser.NewUserCheck(u);
        }
    }
}


