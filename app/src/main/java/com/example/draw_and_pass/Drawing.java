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
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class OtherActivity extends Activity {
    private final String TAG="OtherActivity";

    public void gotoTransitionActivity(){
        Intent intent = new Intent(this,Transition.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        Button button = (Button) findViewById(R.id.bouton);
        DrawLineCanvas canvas = findViewById(R.id.canvas);
        ImageButton pinceau = (ImageButton) findViewById(R.id.pinceau);
        ImageButton gomme = (ImageButton) findViewById(R.id.gomme);
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