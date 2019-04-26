package com.example.quiz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app.Model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    String question, one, two, three, four, answer, key;
    TextView questionText;
    EditText opt1, opt2, opt3, opt4, ans;
    Button save, cancel;
    DatabaseReference ref;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update);

        //initialize button, textview, edittext, imageview
        questionText = (TextView) findViewById(R.id.questionView);
        opt1 = (EditText) findViewById(R.id.edit1);
        opt2 = (EditText) findViewById(R.id.edit2);
        opt3 = (EditText) findViewById(R.id.edit3);
        opt4 = (EditText) findViewById(R.id.edit4);
        ans = (EditText) findViewById(R.id.editAnswer);
        save = (Button) findViewById(R.id.btnSave);
        cancel = (Button) findViewById(R.id.btnCancel);
        backbtn = (ImageView) findViewById(R.id.backbtn);

        //get intent from Admin Question view
        Intent intent = getIntent();
        question = intent.getStringExtra("qe");
        one = intent.getStringExtra("one");
        two = intent.getStringExtra("two");
        three = intent.getStringExtra("three");
        four = intent.getStringExtra("four");
        answer = intent.getStringExtra("ans");
        key = intent.getStringExtra("id");

        //set with data
        questionText.setText(question);
        opt1.setText(one);
        opt2.setText(two);
        opt3.setText(three);
        opt4.setText(four);
        ans.setText(answer);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update database
                String answer = ans.getText().toString().trim();
                String option1 = opt1.getText().toString().trim();
                String option2 = opt2.getText().toString().trim();
                String option3 = opt3.getText().toString().trim();
                String option4 = opt4.getText().toString().trim();

                if(!TextUtils.isEmpty(answer) && !TextUtils.isEmpty(option1) && !TextUtils.isEmpty(option2)
                    && !TextUtils.isEmpty(option3) && !TextUtils.isEmpty(option4)){

                    ref = FirebaseDatabase.getInstance().getReference("questions").child(key);

                    //update
                    Question question1 = new Question(answer, option1, option2, option3, option4, question);
                    ref.setValue(question1);
                    Log.e("update","success");
                    Toast.makeText(UpdateActivity.this, "Successfully Updated!",
                            Toast.LENGTH_SHORT).show();

                    //set intent to start list view
                    Intent intent1 = new Intent(getApplicationContext(),AdminQuestionList.class);
                    startActivity(intent1);
                    finish();
                } else{
                    Log.e("update","fail");
                    Toast.makeText(UpdateActivity.this, "Fill all fields!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set intent to start list view
                Toast.makeText(UpdateActivity.this, "Question Update cancelled",
                        Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(),AdminQuestionList.class);
                startActivity(intent2);
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AdminQuestionList.class);
        startActivity(intent);
        finish();
    }
}
