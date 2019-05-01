package com.example.quiz_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *
 * @author Shannon
 * @author Udana
 */

public class AdminQuestion extends AppCompatActivity {

    DatabaseReference ref;
    Button btnUpdate, btnDelete;
    TextView quest, answ, opt1, opt2, opt3, opt4;
    String question, one, two, three, four, answer, key;
    ImageView imgbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_question);

        //initialize btns & textviews, image view
        quest = (TextView) findViewById(R.id.questionn);
        opt1 = (TextView) findViewById(R.id.onee);
        opt2 = (TextView) findViewById(R.id.twoo);
        opt3 = (TextView) findViewById(R.id.threee);
        opt4 = (TextView) findViewById(R.id.fourr);
        answ = (TextView) findViewById(R.id.answerr);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        imgbackbutton = (ImageView) findViewById(R.id.imgbackbutton);

        //get details from list row using intent
        Intent intent = getIntent();
        question = intent.getStringExtra("q");
        one = intent.getStringExtra("one");
        two = intent.getStringExtra("two");
        three = intent.getStringExtra("three");
        four = intent.getStringExtra("four");
        answer = intent.getStringExtra("ans");
        key = intent.getStringExtra("id");

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

                Intent intent1 = new Intent(getApplicationContext(),UpdateActivity.class);

                intent1.putExtra("qe",question);
                intent1.putExtra("one",one);
                intent1.putExtra("two",two);
                intent1.putExtra("three",three);
                intent1.putExtra("four",four);
                intent1.putExtra("ans",answer);
                intent1.putExtra("id",key);
                startActivity(intent1);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        AdminQuestion.this);

                alertDialogBuilder.setTitle("Do you want to delete question?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                //code to delete selected record
                                ref = FirebaseDatabase.getInstance().getReference("questions").child(key);

                                //removing the question
                                ref.removeValue();

                                //return to question list
                                Intent iback = new Intent(getApplicationContext(),AdminQuestionList.class);
                                startActivity(iback);
                                finish();
                                Log.e("delete","success");
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        imgbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),AdminQuestionList.class);
        startActivity(intent);
        finish();
    }
}
