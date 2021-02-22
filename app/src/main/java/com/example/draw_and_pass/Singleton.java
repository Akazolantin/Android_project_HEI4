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

    public void setGameArrayList(ArrayList<Game> gameArrayList) {
        this.GameArrayList = gameArrayList;
    }

    public void setCurrentGame(Game game){
        currentGame = game;
    }

    public Game getCurrentGame(){
        return  currentGame;
    }


    public Game getGameAtPosition(int position){
        return GameArrayList.get(position);
    }

    public int getNumberOfGame(){
        return GameArrayList.size();
    }
}
