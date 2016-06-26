package com.oncoti.Models;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class CategoryModel {

    private int catImage;
    private String catName;

    public CategoryModel(int catImage, String catName) {
        this.catImage = catImage;
        this.catName = catName;
    }

    public int getCatImage() {
        return catImage;
    }

    public void setCatImage(int catImage) {
        this.catImage = catImage;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
