package com.oncoti.Models;

/**
 * Created by Shawaf on 8/28/2015.
 */
public class NavDrawerItem_Model {

    private String name;
    private int icon;
    private int count;

    public NavDrawerItem_Model(String name, int icon, int count) {
        this.name = name;
        this.icon = icon;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
