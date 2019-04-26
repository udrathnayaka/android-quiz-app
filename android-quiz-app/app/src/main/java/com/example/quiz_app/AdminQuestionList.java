package com.example.quiz_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quiz_app.Model.Question;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.quiz_app.R.layout.question_row;

public class AdminQuestionList extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    DatabaseReference ref, ref1;
    //list to store questions from db
    List<Question> questions;
    ArrayAdapter<String>arrayAdapter;
    List<String> q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_question_list);

        fab = findViewById(R.id.fab);
        listView=(ListView) findViewById(R.id.listview);

        ref= FirebaseDatabase.getInstance().getReference("questions");
        questions = new ArrayList<>();
        q = new ArrayList<>();
        final String[] idd = new String[1];

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Question value = dataSnapshot.getValue(Question.class);
                //idd[0] = dataSnapshot.getKey();
                //Log.e("iddddd",idd[0]);
                questions.add(value);
                q.add(value.getQuestion());
                arrayAdapter = new ArrayAdapter<String>(AdminQuestionList.this, android.R.layout.simple_list_item_1, q);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                final Question question = questions.get(i);
                ref1 = FirebaseDatabase.getInstance().getReference().child("questions");
                Query query = ref1.orderByChild("question").equalTo(question.getQuestion());

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child: dataSnapshot.getChildren()){
                            String key = child.getKey();
                            //int key = ref.getRef(i).getKey();

                            Intent i2 =new Intent(AdminQuestionList.this,AdminQuestion.class);

                            i2.putExtra("id",key);
                            i2.putExtra("q",question.getQuestion());
                            i2.putExtra("one",question.getOption1());
                            i2.putExtra("two",question.getOption2());
                            i2.putExtra("three",question.getOption3());
                            i2.putExtra("four",question.getOption4());
                            i2.putExtra("ans",question.getAnswer());

                            startActivity(i2);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();;
    }
}
