package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public void gotoOtherActivity(){
        Intent intent = new Intent(this,Transition.class);
        startActivity(intent);
        Game game = new Game(0,4,null,null);// prendre les arguments sauvegardés dans le start game
        Transition.setGame(game);
    }

    public void gotoEnd_gameActivity(){
        Intent intent = new Intent(this,End_game.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);


    }
}