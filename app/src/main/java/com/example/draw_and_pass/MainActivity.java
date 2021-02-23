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
        Game game = new Game(0,4,null,null);// prendre les arguments sauvegardés dans le start game
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

        Button button = (Button) findViewById(R.id.bouton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoOtherActivity();
            }
        });
    }
}