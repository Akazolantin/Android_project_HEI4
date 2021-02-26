package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rules extends AppCompatActivity {

    private TextView mTest_rules;
    private TextView mTest_rules_title;
    private Button mRules_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        mTest_rules=(TextView)findViewById(R.id.activity_Rules_textView);
        mTest_rules_title=(TextView)findViewById(R.id.activity_Rules_textView_Title);
        mRules_Button=(Button)findViewById(R.id.activity_rules_button);




        mRules_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main_activity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(Main_activity);
                finish();

            }
        });
    }


}