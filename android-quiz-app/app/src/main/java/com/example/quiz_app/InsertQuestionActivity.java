package com.example.quiz_app;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.quiz_app.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertQuestionActivity extends AppCompatActivity {

    Button btnInsert;
    EditText qtext, btn1, btn2, btn3, btn4, ans;
    DatabaseReference ref, ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_insert_question);

        ref = FirebaseDatabase.getInstance().getReference("questions");

        //initialize buttons, edittext
        qtext = (EditText) findViewById(R.id.qtxt);
        btn1 = (EditText) findViewById(R.id.btn1);
        btn2 = (EditText) findViewById(R.id.btn2);
        btn3 = (EditText) findViewById(R.id.btn3);
        btn4 = (EditText) findViewById(R.id.btn4);
        ans = (EditText) findViewById(R.id.ans);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        InsertQuestionActivity.this);

                alertDialogBuilder.setTitle("Do you want to add this Question?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                addToDatabase();
                                finish();
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
    }

    private void addToDatabase() {

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String q = qtext.getText().toString().trim();
                String op1 = btn1.getText().toString().trim();
                String op2 = btn2.getText().toString().trim();
                String op3 = btn3.getText().toString().trim();
                String op4 = btn4.getText().toString().trim();
                String answer = ans.getText().toString().trim();

                //check if empty
                if(!TextUtils.isEmpty(q) && !TextUtils.isEmpty(op1) && !TextUtils.isEmpty(op2)
                        && !TextUtils.isEmpty(op3) && !TextUtils.isEmpty(op4) && !TextUtils.isEmpty(answer)){
                    //check if same question exist

                    //create primary key
                    int a = (int) dataSnapshot.getChildrenCount()+1;

                    //create question object
                    Question question = new Question(answer,op1,op2,op3,op4,q);

                    //save question
                    ref.child(String.valueOf(a)).setValue(question);

                    //reset editText
                    qtext.setText("");
                    btn1.setText("");
                    btn2.setText("");
                    btn3.setText("");
                    btn4.setText("");
                    ans.setText("");

                    Log.e("insert","success");
                } else{
                    Log.e("insert","fail");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
