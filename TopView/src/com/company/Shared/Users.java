package com.company.Shared;

/**
 * @Title:
 * @Package
 * @Description: 记录用户信息
 * @author: Yeeasy
 **/
public class Users implements java.io.Serializable {
    private String userID;
    private String userPW;
    private String userType; //NewUser LoginUser

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }
}
