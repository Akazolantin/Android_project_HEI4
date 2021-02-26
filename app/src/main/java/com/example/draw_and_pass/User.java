package com.example.draw_and_pass;

import android.media.Image;
import android.widget.ImageView;

public class User {
    private int id;
    private String name;
    private Image icon;

    User(int mid, String mname, Image micon){
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

    public void setIcon() {this.icon = icon; }

    public Image getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }




}

