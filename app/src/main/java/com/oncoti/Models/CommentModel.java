package com.oncoti.Models;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class CommentModel {

    private String userImage;
    private String userName;
    private String commentTime;
    private String commentText;

    public CommentModel(String userImage, String userName, String commentTime, String commentText) {
        this.userImage = userImage;
        this.userName = userName;
        this.commentTime = commentTime;
        this.commentText = commentText;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
