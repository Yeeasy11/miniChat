package com.company.Server.view;

import com.company.Server.model.ActListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Title:
 * @Package
 * @Description: server主要控制界面
 * @author: Yeeasy
 **/
public class ServerCenter extends JFrame implements ActionListener {

    JPanel ServerP;
    JButton ServerB1,ServerB2;

    public static void main(String[] args) {

        ServerCenter serverCenter=new ServerCenter();
    }

    public ServerCenter(){

        setSize(300,100);
        setLocationRelativeTo(null);  //居中

        ServerP=new JPanel();
        ServerB1=new JButton("打开服务器");
        ServerB1.addActionListener(this);   //监听Button B1
        ServerB2=new JButton("关闭服务器");
        ServerP.add(ServerB1);
        ServerP.add(ServerB2);

        this.add(ServerP, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //JFrame执行关闭操作时，退出程序
        this.setResizable(false);
        this.setVisible(true);

        System.out.println("服务器启动");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //打开服务器
        //一个端口可以有多个socket ，但其中只能有一个处于listen状态
        if(e.getSource()==ServerB1){
            new ActListener();
            System.out.println("服务器打开");
        }
    }
}
