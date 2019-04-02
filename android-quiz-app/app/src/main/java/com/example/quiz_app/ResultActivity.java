package com.example.quiz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        t1=(TextView) findViewById(R.id.textView1);
        t2=(TextView) findViewById(R.id.textView2);
        t3=(TextView) findViewById(R.id.textView3);

        Intent i = getIntent();

            String questions= i.getStringExtra("Total");
            String correct =i.getStringExtra("Correct");
            String wrong = i.getStringExtra("Incorrect");


            t1.setText(questions);
            t2.setText(correct);
            t3.setText(wrong);
        }
    }

