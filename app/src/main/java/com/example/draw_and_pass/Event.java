package com.example.draw_and_pass;

import android.graphics.Bitmap;
import android.media.Image;

public class Event {
    private User user;
    private Bitmap image;
    private String phrase;
    private String phraseTofind;

    public Event(User user) {
        this.user = user;

    }

    public Event(User user, String phrase) {
        this.user = user;
        this.phrase = phrase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }


    public String getPhraseTofind() {
        return phraseTofind;
    }

    public void setPhraseTofind(String phraseTofind) {
        this.phraseTofind = phraseTofind;
    }
}
