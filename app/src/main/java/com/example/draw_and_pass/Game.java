package com.example.draw_and_pass;

import java.util.ArrayList;

public class Game {
    private int id;
    private int nbrevent;
    private ArrayList<Event> events;
    private User gagnant;
    private Game instance;

    public Game getInstance() {
        return instance;
    }

    public void setInstance(Game instance) {
        this.instance = instance;
    }

    public Game(int id, int nbrevent, ArrayList<Event> events, User gagnant) {
        this.id = id;
        this.nbrevent = nbrevent;
        this.events = events;
        this.gagnant = gagnant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrevent() {
        return nbrevent;
    }

    public void setNbrevent(int nbrevent) {
        this.nbrevent = nbrevent;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public User getGagnant() {
        return gagnant;
    }

    public void setGagnant(User gagnant) {
        this.gagnant = gagnant;
    }
}
