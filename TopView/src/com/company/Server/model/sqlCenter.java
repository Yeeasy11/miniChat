package com.company.Server.model;

import java.sql.*;

/**
 * @Title:
 * @Package
 * @Description:连接到数据库
 * @author: Yeeasy
 **/
public class sqlCenter {
    //数据库连接
    private static Connection con=null;
    private static String url="jdbc:mysql://localhost:3306/tvtalk";  //协议+ip地址+端口号+访问数据库
    private static String userName="root";  //账号
    private static String passWord="Yeeasy";  //密码
    private static String driverName="com.mysql.cj.jdbc.Driver";  //驱动

    public static void insertDB(String ID,String Password){

        //实例化Driver
        try {
            Class.forName(driverName);
            con=DriverManager.getConnection(url,userName,passWord);

            if(!con.isClosed()) {
                System.out.println("成功连接数据库");
            }
            Statement statement=con.createStatement();
            String sql="insert into users(ID,Password) values ("+ID+","+Password+")";
            statement.executeUpdate(sql);
            con.close();
            System.out.println("成功插入数据");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getPassword(String ID) {
        String password=null;
        int id=Integer.parseInt(ID);
        System.out.println(id);
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, passWord);

            if (!con.isClosed()) {
                System.out.println("成功连接数据库");
            }

            String sql = "select Password from users where ID="+id;
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()){
                password = resultSet.getString("Password");
            }
            con.close();
            System.out.println("成功获取数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }


}
