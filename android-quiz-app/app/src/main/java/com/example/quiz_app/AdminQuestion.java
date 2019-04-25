package com.example.quiz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AdminQuestion extends AppCompatActivity {

    Button btnUpdate, btnDelete;
    TextView quest, answ, opt1, opt2, opt3, opt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_question);

        //initialize btns & textviews
        quest = (TextView) findViewById(R.id.questionn);
        opt1 = (TextView) findViewById(R.id.onee);
        opt2 = (TextView) findViewById(R.id.twoo);
        opt3 = (TextView) findViewById(R.id.threee);
        opt4 = (TextView) findViewById(R.id.fourr);
        answ = (TextView) findViewById(R.id.answerr);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        //get details from list row using intent
        Intent intent = getIntent();
        String question = intent.getStringExtra("q");
        String one = intent.getStringExtra("one");
        String two = intent.getStringExtra("two");
        String three = intent.getStringExtra("three");
        String four = intent.getStringExtra("four");
        String answer = intent.getStringExtra("ans");

        //load question details into the ui when row is clicked
        quest.setText(question);
        opt1.setText(one);
        opt2.setText(two);
        opt3.setText(three);
        opt4.setText(four);
        answ.setText(answer);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to update activity
                Intent i = new Intent(getApplicationContext(),UpdateActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                //code to delete selected record
            }
        });
    }
}
