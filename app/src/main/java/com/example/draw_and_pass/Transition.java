package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class Transition extends AppCompatActivity {
    private EditText mPseudo;
    private Button mNextButton;
    private ImageButton mButtonProfil;
    private static Game game;
   private final String TAG = "toto";
   private static ArrayList<User> users;

    public static void setUsers(ArrayList<User> userArrayList) {
        Transition.users = userArrayList;
        Log.d("toto", "salut: "+userArrayList);
    }

    public static void setGame(Game game) {
        Transition.game = game;
        Log.d("toto", "salut: "+game);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(users ==null) {
            setContentView(R.layout.activity_transition);
            mPseudo = (EditText) findViewById(R.id.activity_transition_pseudo);
            mButtonProfil = (ImageButton) findViewById(R.id.activity_transition_profil);
            mNextButton = (Button) findViewById(R.id.activity_transition_next_activity);

            mNextButton.setEnabled(false);

            mPseudo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mNextButton.setEnabled(s.toString().length() > 1);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        else{
            setContentView(R.layout.activity_transition_alt);
            mNextButton = (Button) findViewById(R.id.activity_transition_next_activity);
            mButtonProfil = (ImageButton) findViewById(R.id.activity_transition_profil);
            //mButtonProfil.setImage(users.get(game.getCounter()).getIcon());
            TextView pseudo = (TextView) findViewById(R.id.activity_transition_pseudo);
            pseudo.setText(users.get(game.getCounter()+1).getName());
        }

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User user;
                if(users==null){
                user = new User(0, mPseudo.getText().toString(), null);}
                else{
                    user = users.get(game.getCounter()+1);
                }

                Event event = new Event(user);
                game.addEvent(event);
                // retourner le nombre de tours de start game

                    if (game.getCounter() % 2 == 0) {
                        Log.d(TAG, "1");
                        Intent drawingActivity = new Intent(Transition.this, Drawing.class);
                        game.setCounter();
                        Drawing.setGame(game);
                        startActivity(drawingActivity);

                        Log.d(TAG, "2");
                    } else {
                        Intent guessActivity = new Intent(Transition.this, Guess_the_word.class);
                        Guess_the_word.setGame(game);
                        startActivity(guessActivity);
                        Log.d(TAG, "3");
                        game.setCounter();
                    }

            }
        });
    }

}