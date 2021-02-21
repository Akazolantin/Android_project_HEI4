package com.example.draw_and_pass;

import android.media.Image;

public class Event {
    private User user;
    private Image image;
    private String phrase;

    public Event(User user, Image image) {
        this.user = user;
        this.image = image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
