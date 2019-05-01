package com.example.quiz_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 *
 * @author Shannon (IT16036758)
 * @author Udana    (IT16069282)
 * CTSE final project SE4010
 *
 * AboutUsActivity.java
 * This class handles the logic for About Us screen
 */

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about_us);
    }
}
