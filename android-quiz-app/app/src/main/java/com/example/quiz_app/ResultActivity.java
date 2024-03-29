package com.example.quiz_app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 * @author Shannon (IT16036758)
 * @author Udana    (IT16069282)
 * CTSE final project SE4010
 *
 * ResultActivity.java
 * This class handles the logic for teh result screen upon finishing of the quiz game
 */

public class ResultActivity extends AppCompatActivity {

    TextView t1,t2,t3;
    ImageView imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        //return to home
        imgHome = findViewById(R.id.imgHome);
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
