package com.company.Shared;

/**
 * @Title:
 * @Package
 * @Description: 接送信息内容
 * @author: Yeeasy
 **/
public class Message implements java.io.Serializable {
    private String msgType;//success-登录成功,fail-登录失败,talk-聊天,onlineGet-获取在线好友,onlineSet-修改列表在线情况,newUser-注册
    private String user;
    private String friend;
    private String nowTime;
    private String content;

    public String getMsgType(){
        return msgType;
    }

    public void setMsgType(String msgType){
        this.msgType=msgType;
    }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public String getFriend() { return friend; }

    public void setFriend(String friend) { this.friend = friend; }

    public String getNowTime() { return nowTime; }

    public void setNowTime(String nowTime) { this.nowTime = nowTime; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}


