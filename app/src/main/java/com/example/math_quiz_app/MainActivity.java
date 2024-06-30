package com.example.math_quiz_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
    Button startnow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startnow=findViewById(R.id.startnow);


        startnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent levelpage=new Intent(MainActivity.this, Levelspage.class);
                startActivity(levelpage);
            }
        });



        ImageButton abcButton = findViewById(R.id.info);
        abcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Create a dialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

// Create a linear layout to hold the dialog contents
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                layout.setPadding(32, 32, 32, 32); // Add padding to create space between elements

// Create a text view for the heading
                TextView heading = new TextView(MainActivity.this);
                heading.setText("We Aim");
                heading.setTextSize(20);
                heading.setGravity(Gravity.CENTER);
                heading.setTypeface(null, Typeface.BOLD);
                heading.setTextColor(Color.BLACK); // Set black text color
                layout.addView(heading);

// Create text views for each level description
                TextView WeAim = new TextView(MainActivity.this);
                WeAim.setGravity(Gravity.CENTER);
                WeAim.setText("The math quiz aims to make learning math exciting and accessible to kids, " +
                        "encouraging their active participation and involvement. " +
                        "By presenting math problems and questions in a game-like format, " +
                        "the quiz aims to capture children's attention and maintain their interest throughout the learning process.");
                WeAim.setTextSize(16);
                WeAim.setTextColor(Color.BLACK); // Set black text color
                layout.addView(WeAim);


// Create a text view for the heading
                heading = new TextView(MainActivity.this);
                heading.setText("About Levels");
                heading.setTextSize(20);
                heading.setTypeface(null, Typeface.BOLD);
                heading.setGravity(Gravity.CENTER);
                heading.setTextColor(Color.BLACK); // Set black text color
                layout.addView(heading);

// Create text views for each level description
                TextView level0 = new TextView(MainActivity.this);

                level0.setGravity(Gravity.CENTER);
                level0.setText("Level 0: 10 Questions, No Timer.");
                level0.setTextSize(16);
                level0.setTextColor(Color.BLACK); // Set black text color
                layout.addView(level0);

                TextView level1 = new TextView(MainActivity.this);

                level1.setGravity(Gravity.CENTER);
                level1.setText("Level 1: 10 Questions, Timer: 20 seconds.");
                level1.setTextSize(16);
                level1.setTextColor(Color.BLACK); // Set black text color
                layout.addView(level1);

                TextView level2 = new TextView(MainActivity.this);

                level2.setGravity(Gravity.CENTER);
                level2.setText("Level 2: 10 Questions, Timer: 10 seconds.");
                level2.setTextSize(16);
                level2.setTextColor(Color.BLACK); // Set black text color
                layout.addView(level2);

// Set the layout as the dialog view
                builder.setView(layout);

// Set a positive button to dismiss the dialog
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

// Create and show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}