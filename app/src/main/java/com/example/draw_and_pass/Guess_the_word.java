package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Guess_the_word extends AppCompatActivity {

    String Response_value;

    private EditText mReponse;
    private Button mButtonToTransition;

    private ImageView mImageview;

    private static Game game;

    public static void setGame(Game game) {

        Guess_the_word.game = game;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean back_answer = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (game.getCounter()>0) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                boolean debugState = false;
                if (debugState) {
                    Toast.makeText(this, "BACK key press", Toast.LENGTH_SHORT).show();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Vous ne pouvez pas tricher !")
                        .setCancelable(false)
                        .setPositiveButton("Oh MINCE !!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                back_answer = true;
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }

        }
        return back_answer;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_word);
        mButtonToTransition=(Button) findViewById(R.id.activity_guess_Button);
        mReponse= (EditText) findViewById(R.id.activity_guess_edit_text);
        mImageview=(ImageView)findViewById(R.id.activity_image_view);

        mButtonToTransition.setEnabled(false);

        mImageview.setImageBitmap(game.getEvents().get(game.getEvents().size()-2).getImage());

        mReponse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButtonToTransition.setEnabled(s.toString().length() != 0);
                Response_value = mReponse.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButtonToTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getEvents().get(game.getEvents().size()-1).setPhrase(Response_value);
                Response_value = mReponse.getText().toString();
                Intent intent;
                if (game.getCounter() < game.getNbrevent()) {
                    intent= new Intent(Guess_the_word.this,Transition.class);
                    Transition.setGame(game);
                }else{
                    intent = new Intent(Guess_the_word.this,End_game.class);
                    End_game.setGame(game);
                }
                startActivity(intent);
            }

        });
    }
}