package com.example.quiz_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 *
 * @author Shannon (IT16036758)
 * @author Udana    (IT16069282)
 * CTSE final project SE4010
 *
 * DashboardActivity.java
 * This class handles the logic for the dashboard Screen
 */

public class DashboardActivity extends AppCompatActivity {

    ImageView imageAdmin;
    ImageView imageAboutUs;
    ImageView imageInstructions;
    ImageView imageExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        imageAdmin = findViewById(R.id.imgAdmin);
        imageAboutUs = findViewById(R.id.imgAboutUs);
        imageInstructions = findViewById(R.id.imgInstructions);
        imageExit = findViewById(R.id.imgExit);

        //administrator icon logic
        imageAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        //about us icon logic
        imageAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(i);
            }
        });

        //instructions icon logic
        imageInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InstructionsActivity.class);
                startActivity(i);
            }
        });

        //exit icon logic
        imageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        DashboardActivity.this);

                alertDialogBuilder.setTitle("Do you want to exit?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                moveTaskToBack(true);
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
}
