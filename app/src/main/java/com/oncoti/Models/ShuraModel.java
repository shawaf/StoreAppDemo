package com.oncoti.Models;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class ShuraModel  {

    private String prodImageUrl;
    private String prodOwnerName;
    private String prodDesc;
    private String prodPrice;
    private String productRate;
    private String prodRatePersonNo;

    public ShuraModel(String prodImageUrl, String prodOwnerName, String prodDesc, String prodPrice, String productRate, String prodRatePersonNo) {
        this.prodImageUrl = prodImageUrl;
        this.prodOwnerName = prodOwnerName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.productRate = productRate;
        this.prodRatePersonNo = prodRatePersonNo;
    }

    public String getProdImageUrl() {
        return prodImageUrl;
    }

    public void setProdImageUrl(String prodImageUrl) {
        this.prodImageUrl = prodImageUrl;
    }

    public String getProdOwnerName() {
        return prodOwnerName;
    }

    public void setProdOwnerName(String prodOwnerName) {
        this.prodOwnerName = prodOwnerName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProductRate() {
        return productRate;
    }

    public void setProductRate(String productRate) {
        this.productRate = productRate;
    }

    public String getProdRatePersonNo() {
        return prodRatePersonNo;
    }

    public void setProdRatePersonNo(String prodRatePersonNo) {
        this.prodRatePersonNo = prodRatePersonNo;
    }
}
