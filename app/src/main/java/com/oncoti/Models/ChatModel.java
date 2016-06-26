package com.oncoti.Models;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class ChatModel {

    private boolean isMe;
    private String chatUser;
    private String chatText;
    private String chatTime;

    public ChatModel(boolean isMe, String chatUser, String chatText, String chatTime) {
        this.isMe = isMe;
        this.chatUser = chatUser;
        this.chatText = chatText;
        this.chatTime = chatTime;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setIsMe(boolean isMe) {
        this.isMe = isMe;
    }

    public String getChatUser() {
        return chatUser;
    }

    public void setChatUser(String chatUser) {
        this.chatUser = chatUser;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }
}
