package com.oncoti.Models;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class OrderModel {
    private String orderImageUrl;
    private String orderDealer;
    private String orderDesc;
    private String orderLastUpdate;
    private String orderPrice;
    private String orderCurState;

    public OrderModel(String orderImageUrl, String orderDealer, String orderDesc, String orderLastUpdate, String orderPrice, String orderCurState) {
        this.orderImageUrl = orderImageUrl;
        this.orderDealer = orderDealer;
        this.orderDesc = orderDesc;
        this.orderLastUpdate = orderLastUpdate;
        this.orderPrice = orderPrice;
        this.orderCurState = orderCurState;
    }

    public String getOrderImageUrl() {
        return orderImageUrl;
    }

    public void setOrderImageUrl(String orderImageUrl) {
        this.orderImageUrl = orderImageUrl;
    }

    public String getOrderDealer() {
        return orderDealer;
    }

    public void setOrderDealer(String orderDealer) {
        this.orderDealer = orderDealer;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderLastUpdate() {
        return orderLastUpdate;
    }

    public void setOrderLastUpdate(String orderLastUpdate) {
        this.orderLastUpdate = orderLastUpdate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderCurState() {
        return orderCurState;
    }

    public void setOrderCurState(String orderCurState) {
        this.orderCurState = orderCurState;
    }
}
