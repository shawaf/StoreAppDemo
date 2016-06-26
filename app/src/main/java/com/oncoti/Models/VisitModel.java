package com.oncoti.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class VisitModel {

    private String userImageUrl;
    private String userName;
    private String placeName;
    private String placeLocation;
    private Date visitTime;
    private String visitDesc;
    private ArrayList<String> placeImages;
    private String commentsNo;
    private String likesNo;

    public VisitModel(String userImageUrl, String userName, String placeName, String placeLocation, Date visitTime, String visitDesc, ArrayList<String> placeImages, String commentsNo, String likesNo) {
        this.userImageUrl = userImageUrl;
        this.userName = userName;
        this.placeName = placeName;
        this.placeLocation = placeLocation;
        this.visitTime = visitTime;
        this.visitDesc = visitDesc;
        this.placeImages = placeImages;
        this.commentsNo = commentsNo;
        this.likesNo = likesNo;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitDesc() {
        return visitDesc;
    }

    public void setVisitDesc(String visitDesc) {
        this.visitDesc = visitDesc;
    }

    public ArrayList<String> getPlaceImages() {
        return placeImages;
    }

    public void setPlaceImages(ArrayList<String> placeImages) {
        this.placeImages = placeImages;
    }

    public String getCommentsNo() {
        return commentsNo;
    }

    public void setCommentsNo(String commentsNo) {
        this.commentsNo = commentsNo;
    }

    public String getLikesNo() {
        return likesNo;
    }

    public void setLikesNo(String likesNo) {
        this.likesNo = likesNo;
    }
}
