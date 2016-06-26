package com.oncoti.Models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shawaf on 8/30/2015.
 */
public class ProductModel {

    private ArrayList<String> prodImageUrls;
    private String ownerImage;
    private String ownerName;
    private Date uploadTime;
    private String category;
    private String prodName;
    private String description;
    private String price;
    private ArrayList<String> tags;
    private int wowCounter;
    private int commentsCounter;

    public ProductModel(ArrayList<String> prodImageUrls, String ownerImage, String ownerName, Date uploadTime, String category, String prodName, String description, String price, ArrayList<String> tags, int wowCounter, int commentsCounter) {
        this.prodImageUrls = prodImageUrls;
        this.ownerImage = ownerImage;
        this.ownerName = ownerName;
        this.uploadTime = uploadTime;
        this.category = category;
        this.prodName = prodName;
        this.description = description;
        this.price = price;
        this.tags = tags;
        this.wowCounter = wowCounter;
        this.commentsCounter = commentsCounter;
    }

    public ArrayList<String> getProdImageUrls() {
        return prodImageUrls;
    }

    public void setProdImageUrls(ArrayList<String> prodImageUrls) {
        this.prodImageUrls = prodImageUrls;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(String ownerImage) {
        this.ownerImage = ownerImage;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getWowCounter() {
        return wowCounter;
    }

    public void setWowCounter(int wowCounter) {
        this.wowCounter = wowCounter;
    }

    public int getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(int commentsCounter) {
        this.commentsCounter = commentsCounter;
    }
}
