package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Drawing extends Activity {
    private final String TAG="DrawingActivity";
    private static Game game;
    private static User user;
    private DrawLineCanvas canvas;

    public static void setGame(Game game) {
        Drawing.game = game;
    }

    public void gotoTransitionActivity(){
        Intent intent = new Intent(this,Transition.class);
        game.getEvents().get(-1).setImage(canvas.getB());
        Transition.setGame(game);
        startActivity(intent);
    }
    private TextView timer;

    CountDownTimer countDownTimer= new CountDownTimer(90000,1000) {
        @Override
        public void onTick(long l) {
            int seconds = (int) (l / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timer.setText(String.format("%d:%02d", minutes, seconds));
        }

        @Override
        public void onFinish() {
            gotoTransitionActivity();
        }
    }.start();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvas  = findViewById(R.id.canvas);
        setContentView(R.layout.activity_drawing);
        TextView phrase = (TextView) findViewById(R.id.phrase);
        phrase.setText(game.getEvents().get(-2).getPhrase());
        Button button = (Button) findViewById(R.id.bouton);
        ImageButton pinceau = (ImageButton) findViewById(R.id.pinceau);
        ImageButton gomme = (ImageButton) findViewById(R.id.gomme);
        timer = (TextView) findViewById(R.id.timer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"click");
                gotoTransitionActivity();
            }
        });
        pinceau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvas.setColor(0xff000000);
                canvas.setSize(12);
            }
        });
        gomme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvas.setColor(0xffffffff);
                canvas.setSize(75);
            }
        });
    }

}