//package com.company.Client.view;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
///**
// * @Title:
// * @Package
// * @Description:
// * @author: Yeeasy
// **/
//public class ClientInfo extends JFrame implements ActionListener, MouseListener {
//
//    String userID;
//    JPanel Pup;
//    JPanel Pmid;
//    JLabel Lup;
//    JLabel Lmid;
//    JButton JBup;
//    JButton JBmid;
//    JTextField Tup;
//    JTextField Tmid;
//
//    public ClientInfo(String userID){
//        setSize(200,200);
//        setLocationRelativeTo(null);  //居中
//
//        this.userID=userID;
//        Lup=new JLabel("用户名");
//        Tup=new JTextField(this.userID);
//        JBup=new JButton("修改密码");
//        JBmid=new JButton("找回密码设置");
//
//        Pup.add(Lup);
//        Pup.add(Tup);
//        Pmid.add(JBup);
//        Pmid.add(JBmid);
//
//        this.setTitle(userID);
//        this.setVisible(true);
//
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//}
