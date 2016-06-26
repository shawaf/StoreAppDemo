package com.oncoti.Models;

import java.util.Date;

/**
 * Created by Shawaf on 9/3/2015.
 */
public class HeadlineModel {

    private String userName;
    private String userImageUrl;
    private String postImageUrl;
    private String postText;
    private Date postTime;
    private String comments;
    private String likes;

    public HeadlineModel(String userName, String userImageUrl, String postImageUrl, String postText, Date postTime, String comments, String likes) {
        this.userName = userName;
        this.userImageUrl = userImageUrl;
        this.postImageUrl = postImageUrl;
        this.postText = postText;
        this.postTime = postTime;
        this.comments = comments;
        this.likes = likes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
