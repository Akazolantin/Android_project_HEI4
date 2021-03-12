package com.example.draw_and_pass;

import android.media.Image;
import android.widget.ImageView;

public class User {
    private int id;
    private String name;
    private ImageView icon;

    User(int mid, String mname, ImageView micon){
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



    public ImageView getIcon() {
        return icon;
    }

    public  void setIcon(ImageView icon) {
        this.icon=icon;
    }

    public void setName(String name) {
        this.name = name;
    }




}

