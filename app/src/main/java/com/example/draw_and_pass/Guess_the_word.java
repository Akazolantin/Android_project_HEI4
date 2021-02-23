package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Guess_the_word extends AppCompatActivity {

    String Response_value;

    private EditText mReponse;
    private Button mButtonToTransition;

    private ImageView mImageview;

    private static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_word);
        mButtonToTransition=(Button) findViewById(R.id.activity_guess_Button);
        mReponse= (EditText) findViewById(R.id.activity_guess_edit_text);
        mImageview=(ImageView)findViewById(R.id.activity_image_view);

        mButtonToTransition.setEnabled(false);

        mImageview.setImageBitmap(game.getEvents().get(-1).getImage());

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
                Response_value = mReponse.getText().toString();
                Intent transitionActivity = new Intent(Guess_the_word.this, Transition.class);
                startActivity(transitionActivity);
            }
        });
    }
}