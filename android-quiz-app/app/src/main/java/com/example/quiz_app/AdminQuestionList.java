package com.example.quiz_app;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quiz_app.Model.Question;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminQuestionList extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    DatabaseReference ref;
    //list to store questions from db
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_question_list);

        fab = findViewById(R.id.fab);
        listView=(ListView) findViewById(R.id.listview);

        ref= FirebaseDatabase.getInstance().getReference("questions");
        questions = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //Toast.makeText(AdminQuestionList.this,"Clicked Item:"+1+""+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();

                Question question = questions.get(i);
                Intent i2 =new Intent(AdminQuestionList.this,AdminQuestion.class);

                i2.putExtra("q",question.getQuestion());
                i2.putExtra("one",question.getOption1());
                i2.putExtra("two",question.getOption2());
                i2.putExtra("three",question.getOption3());
                i2.putExtra("four",question.getOption4());
                i2.putExtra("ans",question.getAnswer());

                startActivity(i2);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AdminQuestionList.this,InsertQuestionActivity.class);
                startActivity(i);
            }
        });
    }
}
