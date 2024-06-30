package com.example.math_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Levelspage extends AppCompatActivity implements View.OnClickListener{
    Button zero;
    Button one;
    Button two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelspage);
        zero=findViewById(R.id.zero);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.zero) {
            Intent level0=new Intent(Levelspage.this, question_answer.class);
            startActivity(level0);

            // handle zero button click
        } else if (clickedButton.getId() == R.id.one) {
            Intent level1=new Intent(Levelspage.this, question_answer1.class);
            startActivity(level1);
            // handle one button click
        } else if (clickedButton.getId() == R.id.two) {
            Intent level2=new Intent(Levelspage.this, question_answer2.class);
            startActivity(level2);

            // handle two button click
        } else {
            throw new IllegalStateException("Unexpected value: " + v.getId());
        }



    }
}