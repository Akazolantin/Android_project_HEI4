package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Rules extends AppCompatActivity {

    private TextView mTest_rules;
    private TextView mTest_rules_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        mTest_rules=(TextView)findViewById(R.id.activity_Rules_textView);
        mTest_rules_title=(TextView)findViewById(R.id.activity_Rules_textView_Title);
    }
}