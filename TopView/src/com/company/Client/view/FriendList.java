package com.company.Client.view;

import com.company.Client.tools.TalkingViewManage;
import com.company.Shared.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @Title: FriendList
 * @Package
 * @Description: 查看好友列表
 * @author: Yeeasy
 **/
public class FriendList extends JFrame implements ActionListener, MouseListener {

    JPanel Main;
    //UP
    JPanel PUp;
    JLabel LUp;

    //MIDDLE
    JPanel PMid;
    JLabel []Friends;

    //DOWN
    JPanel PDown;
    JButton PDownJb1;

    String userID;

    public static void main(String[] args) {
        FriendList List=new FriendList("0");
    }

    public FriendList(String userID){

        setSize(200,400);
        setLocationRelativeTo(null);  //居中

        //UP
        PUp=new JPanel();
        LUp=new JLabel("我的好友列表",0);
        PUp.add(LUp);

        //DOWN
        PDown=new JPanel();
        PDownJb1=new JButton("进入群聊");
        PDown.add(PDownJb1);

        //MIDDLE
        Main=new JPanel();
        PMid=new JPanel(new GridLayout(5,1,8,8));
        Friends=new JLabel[5];
        for(int i=0;i<Friends.length;i++){
            Friends[i]=new JLabel(i+1+"",new ImageIcon("src/Images/Friend.jpg"),JLabel.LEFT);
            Friends[i].setEnabled(false);  //默认不在线，登录则在线
            if(Friends[i].getText().equals(userID)){
                Friends[i].setEnabled(true);
            }
            Friends[i].addMouseListener(this); //监听鼠标点击
            PMid.add(Friends[i]);
        }
        Main.add(PMid,BorderLayout.CENTER);
        this.userID=userID;
        this.add(PUp,BorderLayout.NORTH);
        this.add(Main,BorderLayout.CENTER);
        this.add(PDown,BorderLayout.SOUTH);
        this.setTitle(userID);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //JFrame执行关闭操作时，退出程序
        this.setResizable(false);
        this.setVisible(true);

    }

    public void  UpdateFriendList(Message msg){
        //获取在线好友ID数组，将String拆分为数组

        String onlineFriends=msg.getContent();
        String OnlineFriends[]=onlineFriends.split(" ");

        //在线好友ID转换为int，遍历
        int ID=0 ;
            for (int i = 0; i < OnlineFriends.length; i++) {
                //使用Integer.parseInt需要对OnlineFriends进行非空处理
                // 否则java.lang.NumberFormatException: For input string: ""
                if (OnlineFriends[i] != null && !OnlineFriends[i].equals("")) {
                    ID = Integer.parseInt(OnlineFriends[i]) - 1;
                    Friends[ID].setEnabled(true);

                }
            }
        System.out.println("好友列表更新完毕");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent E) {
        String FriendID=((JLabel)E.getSource()).getText();
        //双击获取好友编号并开启聊天窗口
        if(E.getClickCount()==2){
            TalkingView talkingView=new TalkingView(this.userID,FriendID);
            TalkingViewManage.AddTalkingView(this.userID+" "+FriendID,talkingView);
        }
//        if(E.getClickCount()==1){
//            ClientInfo clientInfo=new ClientInfo(this.userID);
//        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //选择时高亮提醒
        JLabel  j1=(JLabel)e.getSource();
        j1.setForeground(Color.RED);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //不选择时取消颜色
        JLabel  j1=(JLabel)e.getSource();
        j1.setForeground(Color.black);
    }
}
