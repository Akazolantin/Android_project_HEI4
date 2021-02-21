package com.example.draw_and_pass;

import android.media.Image;

public class User {
    private int id;
    private String name;
    private Image icon;

    User(int mid,String mname,Image micon){
        this.id=mid;
        this.name=mname;
        this.icon=micon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Image getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}

