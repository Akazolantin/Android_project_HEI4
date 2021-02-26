package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    int nbr_player;
    private TextView mApplication_Name;
    private TextView mNumber_of_player;
    private Button mButton_to_rules;

    private Button mButton_to_play;
    private Button mButton_to_increment;
    private Button mButton_to_decrement;
    private static Game game;


    public void gotoOtherActivity(){
        Intent intent = new Intent(this,Transition.class);
        Game game = new Game(0,nbr_player,null,null);// prendre les arguments sauvegardés dans le start game
        Transition.setGame(game);
        startActivity(intent);
    }

    public void gotoEnd_gameActivity(){
        Intent intent = new Intent(this,End_game.class);
        startActivity(intent);
    }
    public void goToRulesActivity(){
        Intent intent = new Intent(this,Rules.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mApplication_Name = (TextView)findViewById(R.id.activity_Application_Name);
        mNumber_of_player=(TextView) findViewById(R.id.activity_start_nombre_participants);
        mButton_to_rules=(Button)findViewById(R.id.activity_rules_button);

        mButton_to_play=(Button)findViewById(R.id.activity_play_button);
        mButton_to_increment=(Button)findViewById(R.id.activity_increment_button);
        mButton_to_decrement=(Button)findViewById(R.id.activity_decrement_button);

        mButton_to_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbr_player++;
                String j = Integer.toString(nbr_player);
                mNumber_of_player.setText(j);

            }
        });
        mButton_to_decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbr_player--;
                String j = Integer.toString(nbr_player);
                mNumber_of_player.setText(j);
            }
        });

        mButton_to_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOtherActivity();
            }
        });
        mButton_to_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRulesActivity();
            }
        });



    }
}

