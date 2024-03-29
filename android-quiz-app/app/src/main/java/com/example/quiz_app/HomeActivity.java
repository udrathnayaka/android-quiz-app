package com.example.quiz_app;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 *
 * @author Shannon (IT16036758)
 * @author Udana    (IT16069282)
 * CTSE final project SE4010
 *
 * HomeActivity.java
 * This class handles the logic for the Home screen
 */

public class HomeActivity extends AppCompatActivity {

    Button buttonStartQuiz;
    Button buttonInstructions;
    Button buttonDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        buttonStartQuiz = findViewById(R.id.btnStartQuiz);
        buttonInstructions = findViewById(R.id.btnStartInstructions);
        buttonDashboard=findViewById(R.id.btnDashboard);


        //start quiz button code
        //onClicklisteners for buttons
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getApplicationContext(),QuestionActivity.class);
              startActivity(i);
            }
        });

        //instructions intent
        buttonInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),InstructionsActivity.class);
                startActivity(i);
            }
        });

        //main menu intent
        buttonDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(i);
            }
        });
    }

    //onBackPressed method
    @Override
    public void onBackPressed() {
        finish();
    }

    //onDestroy method
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
