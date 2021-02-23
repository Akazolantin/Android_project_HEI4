package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Game game = new Game(0,4,new ArrayList<Event>(),null);// prendre les arguments sauvegardés dans le start game


    public void gotoOtherActivity(){
        Intent intent = new Intent(this,Transition.class);
        Transition.setGame(game);
        startActivity(intent);

    }

    public void gotoEnd_gameActivity(){
        Intent intent = new Intent(this,End_game.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user0 = new User(0,"toto",null);
        Event event0 = new Event(user0, "phrase dedans");
        game.addEvent(event0);
        Button button = (Button) findViewById(R.id.bouton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoOtherActivity();
            }
        });
    }
}