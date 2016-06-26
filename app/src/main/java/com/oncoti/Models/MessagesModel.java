package com.oncoti.Models;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class MessagesModel {

    private String msgUserImageUrl;
    private String msgUserName;
    private String msgLastText;
    private String msgTime;

    public MessagesModel(String msgUserImageUrl, String msgUserName, String msgLastText, String msgTime) {
        this.msgUserImageUrl = msgUserImageUrl;
        this.msgUserName = msgUserName;
        this.msgLastText = msgLastText;
        this.msgTime = msgTime;
    }

    public String getMsgUserImageUrl() {
        return msgUserImageUrl;
    }

    public void setMsgUserImageUrl(String msgUserImageUrl) {
        this.msgUserImageUrl = msgUserImageUrl;
    }

    public String getMsgUserName() {
        return msgUserName;
    }

    public void setMsgUserName(String msgUserName) {
        this.msgUserName = msgUserName;
    }

    public String getMsgLastText() {
        return msgLastText;
    }

    public void setMsgLastText(String msgLastText) {
        this.msgLastText = msgLastText;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
