package com.example.quiz_app;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Shannon (IT16036758)
 * @author Udana    (IT16069282)
 * CTSE final project SE4010
 *
 * QuestionActivity.java
 * This class handles the main app logic for the quiz game
 */

public class QuestionActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;
    TextView t1_question,timerTxt;
    int total=0;
    int correct =0;
    int wrong=0;
    DatabaseReference ref1,ref;
    int a;
    ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_question);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);

        t1_question = (TextView) findViewById(R.id.questionTxt);
        timerTxt = (TextView) findViewById(R.id.txtTimer);

        ref1 = FirebaseDatabase.getInstance().getReference().child("questions");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                a = (int)dataSnapshot.getChildrenCount();
                for(int i=1;i<a+1;i++){
                    arrayList.add(i);
                }
                Collections.shuffle(arrayList);
                updateQuestion();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error",databaseError.toString());
            }
        });

        reverseTimer(60,timerTxt);
    }

    //plays 10 random questions from the database one after another
    //all ten questions are loaded into the same ui
    //total increases by one once a question is answered
    //incorrect and correct totals increase respectively
    private void updateQuestion(){
        total++;
        if (total >10){
            Intent i =new Intent(QuestionActivity.this,ResultActivity.class);
            i.putExtra("Total",String.valueOf(total-1));
            i.putExtra("Correct",String.valueOf(correct));
            i.putExtra("Incorrect",String.valueOf(wrong));
            startActivity(i);
            finish();
        }
        else {
            ref= FirebaseDatabase.getInstance().getReference().child("questions").child(String.valueOf(arrayList.get(total-1)));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question =dataSnapshot.getValue(Question.class);

                    t1_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b1.getText().toString().equals(question.getAnswer())){
                                Toast.makeText(getApplicationContext(),"Correct !",Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler =new Handler();

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //b1.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b1.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                //answer is wrong.... we will find the correct answer and make it green
                                Toast.makeText(getApplicationContext(), "Incorrect !", Toast.LENGTH_SHORT).show();
                                wrong = wrong + 1;

                                b1.setBackgroundColor(Color.RED);

                                if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // b1.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b1.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b2.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b2.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b3.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b3.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b4.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b4.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b2.getText().toString().equals(question.getAnswer())){
                                Toast.makeText(getApplicationContext(),"Correct !",Toast.LENGTH_SHORT).show();
                                b2.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler =new Handler();

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // b2.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                //answer is wrong.... we will find the correct answer and make it green
                                Toast.makeText(getApplicationContext(), "Incorrect !", Toast.LENGTH_SHORT).show();
                                wrong = wrong + 1;

                                b2.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //b1.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        b1.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b2.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        b2.setBackgroundResource(R.drawable.rounded_button_home);
                                        b3.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b3.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        b4.setBackgroundResource(R.drawable.rounded_button_home);
                                        //b4.setBackgroundColor(Color.parseColor("#03A9f4"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b3.getText().toString().equals(question.getAnswer())){
                                Toast.makeText(getApplicationContext(),"Correct !",Toast.LENGTH_SHORT).show();
                                b3.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler =new Handler();

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // b3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b3.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                //answer is wrong.... we will find the correct answer and make it green
                                Toast.makeText(getApplicationContext(), "Incorrect !", Toast.LENGTH_SHORT).show();
                                wrong = wrong + 1;

                                b3.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // b1.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b1.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b2.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b2.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b3.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b3.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b4.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b4.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b4.getText().toString().equals(question.getAnswer())){
                                Toast.makeText(getApplicationContext(),"Correct !",Toast.LENGTH_SHORT).show();
                                b4.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler =new Handler();

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // b4.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b4.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else {
                                //answer is wrong.... we will find the correct answer and make it green
                                Toast.makeText(getApplicationContext(), "Incorrect !", Toast.LENGTH_SHORT).show();
                                wrong = wrong + 1;

                                b4.setBackgroundColor(Color.RED);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // b1.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b1.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b2.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b2.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b3.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b3.setBackgroundResource(R.drawable.rounded_button_home);
                                        // b4.setBackgroundColor(Color.parseColor("#FF9800"));
                                        b4.setBackgroundResource(R.drawable.rounded_button_home);
                                        updateQuestion();
                                    }
                                },1500);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    //reverse timer set for 60 seconds
    public void reverseTimer(int seconds,final TextView tv){

        new CountDownTimer(seconds* 1000+1000,1000){

            public void onTick(long millisUnitFinished) {

                int seconds = (int) (millisUnitFinished / 1000);
                int minutes =  seconds/60;
                seconds= seconds % 60;
                tv.setText(String.format("%02d",minutes)+":" +String.format("%02d",seconds));
            }

            public void onFinish(){
                tv.setText("Completed");
                Intent myIntent=new Intent (QuestionActivity.this,ResultActivity.class);
                myIntent.putExtra("Total",String.valueOf(total));
                myIntent.putExtra("Correct",String.valueOf(correct));
                myIntent.putExtra("Incorrect",String.valueOf(wrong));
                startActivity(myIntent);
                finish();
            }
        }.start();
    }
}