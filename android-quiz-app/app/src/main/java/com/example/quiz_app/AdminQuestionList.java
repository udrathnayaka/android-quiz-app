package com.example.quiz_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminQuestionList extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_question_list);

        listView=(ListView) findViewById(R.id.listview);
        final ArrayList<String> arrayList =new ArrayList<>();

        arrayList.add("Question 01");
        arrayList.add("Question 02");
        arrayList.add("Question 03");
        arrayList.add("Question 04");
        arrayList.add("Question 05");
        arrayList.add("Question 06");
        arrayList.add("Question 07");
        arrayList.add("Question 08");
        arrayList.add("Question 09");
        arrayList.add("Question 10");
        arrayList.add("Question 11");
        arrayList.add("Question 12");
        arrayList.add("Question 13");
        arrayList.add("Question 14");
        arrayList.add("Question 15");
        arrayList.add("Question 16");
        arrayList.add("Question 17");
        arrayList.add("Question 18");
        arrayList.add("Question 19");
        arrayList.add("Question 20");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(AdminQuestionList.this,"Clicked Item:"+1+""+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
        }
    }

