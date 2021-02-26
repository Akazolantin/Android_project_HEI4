package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Drawing extends Activity {
    private final String TAG="DrawingActivity";
    private static Game game;
    private static User user;
    private DrawLineCanvas canvas;

    public static void setGame(Game game) {

        Drawing.game = game;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////


    public void gotoTransitionActivity(){
        Intent intent;
        game.getEvents().get(game.getEvents().size()-1).setImage(canvas.getB());
        if (game.getCounter() < game.getNbrevent()) {
            intent= new Intent(this,Transition.class);
            Transition.setGame(game);
        }else{
            intent = new Intent(this,End_game.class);
            End_game.setGame(game);
        }
        countDownTimer.cancel();
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
        //Log.d(TAG,"la list est "+game.getEvents().size());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        canvas  = (DrawLineCanvas) findViewById(R.id.canvas);
        TextView phrase = (TextView) findViewById(R.id.phrase);
        phrase.setText(game.getEvents().get(game.getEvents().size()-2).getPhrase());
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
        int[] Colors = new int[]{
                0xffF44336,
                0xffE91E63,
                0xff9C27B0,
                0xff673AB7,
                0xff3F51B5,
                0xff2196F3,
                0xff03A9F4,
                0xff00BCD4,
                0xff009688,
                0xff4CAF50,
                0xff8BC34A,
                0xffCDDC39,
                0xffFFEB3B,
                0xffFFC107,
                0xffFF9800,
                0xffFF5722,
                0xff795548,
                0xff9E9E9E,
                0xff607D8B,
                0xffFFFFFF
        };
        LinearLayout linearUp= (LinearLayout) findViewById(R.id.color_up);
        LinearLayout linearDown= (LinearLayout) findViewById(R.id.color_down);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 100);
        lp.weight = linearDown.getWeightSum()/(Colors.length/2);
        View.OnClickListener btnClicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvas.setColor(Colors[(int)view.getTag()]);
            }
        };

            for (int i = 0; i < Colors.length/2; i++) {
                Button btnWord = new Button(this);
                btnWord.setTag(i);
                btnWord.setHeight(100);
                btnWord.setWidth(10);
                btnWord.setLayoutParams(lp);
                btnWord.setBackgroundColor(Colors[i]);
                btnWord.setOnClickListener(btnClicked);
                linearUp.addView(btnWord);
            }

        for (int i = Colors.length/2; i < Colors.length; i++) {
            Button btnWord = new Button(this);
            btnWord.setTag(i);
            btnWord.setWidth(10);
            btnWord.setHeight(100);
            btnWord.setLayoutParams(lp);
            btnWord.setBackgroundColor(Colors[i]);
            btnWord.setOnClickListener(btnClicked);
            linearDown.addView(btnWord);
        }

    }

}