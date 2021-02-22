package com.example.draw_and_pass;

import java.util.ArrayList;

public class Singleton {
    private static final Singleton ourInstance = new Singleton();

    private Game currentGame;
    private ArrayList<Game> GameArrayList;

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        this.GameArrayList = new ArrayList<>();
    }

    public void setGameArrayList(ArrayList<Game> restaurantArrayList) {
        this.GameArrayList = restaurantArrayList;
    }

    public void setCurrentGame(Game game){
        currentGame = game;
    }

    public Game getCurrentRestaurant(){
        return  currentGame;
    }


    public Game getGameAtPosition(int position){
        return GameArrayList.get(position);
    }

    public int getNumberOfRestaurant(){
        return GameArrayList.size();
    }
}
