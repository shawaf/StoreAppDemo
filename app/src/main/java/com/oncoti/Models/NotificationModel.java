package com.oncoti.Models;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class NotificationModel {

    private String notifImageUrl;
    private String notifUser;
    private String notifText;
    private String notifTime;

    public NotificationModel(String notifImageUrl, String notifUser, String notifText, String notifTime) {
        this.notifImageUrl = notifImageUrl;
        this.notifUser = notifUser;
        this.notifText = notifText;
        this.notifTime = notifTime;
    }

    public String getNotifImageUrl() {
        return notifImageUrl;
    }

    public void setNotifImageUrl(String notifImageUrl) {
        this.notifImageUrl = notifImageUrl;
    }

    public String getNotifUser() {
        return notifUser;
    }

    public void setNotifUser(String notifUser) {
        this.notifUser = notifUser;
    }

    public String getNotifText() {
        return notifText;
    }

    public void setNotifText(String notifText) {
        this.notifText = notifText;
    }

    public String getNotifTime() {
        return notifTime;
    }

    public void setNotifTime(String notifTime) {
        this.notifTime = notifTime;
    }
}
