package com.example.math_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;
import android.os.VibrationEffect;
import java.util.ArrayList;
import java.util.Random;

public class question_answer1 extends AppCompatActivity implements View.OnClickListener {

    TextView level, questionNumber, timer, question;
    Button opt1, opt2, opt3, opt4, submit;
    int questionIndex = 0;
    private int score = 0;
    int totalQuestion = questions.questions.length;
    String selectedAns = "";
    static MediaPlayer successSound, failSound;
    ArrayList<Integer> randomQuestionIndexes = getRandomNonRepeatingIntegers(questions.questions.length, 0, (questions.questions.length-1));
    boolean isAnswerShown = false;
    CountDownTimer countDownTimer;
    private boolean playAnimation = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        successSound = MediaPlayer.create(this, R.raw.yay);
        failSound = MediaPlayer.create(this, R.raw.fail);
        successSound.setLooping(false);
        failSound.setLooping(false);

        //link with XML with ID
        level = findViewById(R.id.leveltextview);
        questionNumber = findViewById(R.id.questionindextrack);
        timer = findViewById(R.id.timelimit);
        timer.setText("Time Limit: No Limit");
        question = findViewById(R.id.Question);
        opt1 = findViewById(R.id.one);
        opt2 = findViewById(R.id.two);
        opt3 = findViewById(R.id.three);
        opt4 = findViewById(R.id.four);
        submit = findViewById(R.id.submit);

        opt1.setOnClickListener(this);
        opt2.setOnClickListener(this);
        opt3.setOnClickListener(this);
        opt4.setOnClickListener(this);
        submit.setOnClickListener(this);

        submit.setEnabled(false);//initially disable the submit button

        //set level text
        level.setText("Level 1");

        //load question
        loadNewQuestion();
    }

    //getRandomNonRepeatingIntegers function
    public static ArrayList<Integer> getRandomNonRepeatingIntegers(int size, int min, int max) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random random = new Random();
        while (numbers.size() < size) {
            int randomNumber = random.nextInt((max - min) + 1) + min;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    //on click function
    @Override
    public void onClick(View v) {

        Button clickButton = (Button) v;



        if (clickButton.equals(submit)) {
            if (!isAnswerShown) {
                showAns((Button) clickButton);
                isAnswerShown = true;
            } else {
                opt1.setEnabled(true);
                opt2.setEnabled(true);
                opt3.setEnabled(true);
                opt4.setEnabled(true);
                questionIndex++;
                loadNewQuestion();
                isAnswerShown = false;
                submit.setText("Submit");
            }
        } else {
            // Change the background color of the clicked button to green
            clickButton.setBackgroundColor(Color.parseColor("#FF9800"));

            // Change the background color of all other buttons back to the default color



            if (!opt1.equals(clickButton)) {
                opt1.setBackgroundColor(Color.parseColor("#00B8D4"));
            }
            if (!opt2.equals(clickButton)) {
                opt2.setBackgroundColor(Color.parseColor("#00B8D4"));
            }
            if (!opt2.equals(clickButton)) {
                opt2.setBackgroundColor(Color.parseColor("#00B8D4"));
            }
            if (!opt3.equals(clickButton)) {
                opt3.setBackgroundColor(Color.parseColor("#00B8D4"));
            }

            if (!opt4.equals(clickButton)) {
                opt4.setBackgroundColor(Color.parseColor("#00B8D4"));
            }

            selectedAns = clickButton.getText().toString();

            if (selectedAns == null) {
                submit.setEnabled(false); // Disable the submit button if selectedAnswer is null
            } else {
                submit.setEnabled(true); // Enable the submit button if selectedAnswer is not null
            }
        }
    }

    ArrayList List = getRandomNonRepeatingIntegers(questions.questions.length, 0, (questions.questions.length-1));
    void loadNewQuestion(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (questionIndex == totalQuestion) {
            finishQuiz();
            return;
        }
        submit.setEnabled(false);

        opt1.setBackgroundColor(Color.parseColor("#00B8D4"));
        opt2.setBackgroundColor(Color.parseColor("#00B8D4"));
        opt3.setBackgroundColor(Color.parseColor("#00B8D4"));
        opt4.setBackgroundColor(Color.parseColor("#00B8D4"));

        timer.clearAnimation();
        timer.setTextSize(16);
        timer.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        questionNumber.setText("Questions: "+(questionIndex+1)+"/"+totalQuestion );
        question.setText(questions.questions[(int) List.get(questionIndex)]);
        opt1.setText(questions.choices[(int) List.get(questionIndex)][0]);
        opt2.setText(questions.choices[(int) List.get(questionIndex)][1]);
        opt3.setText(questions.choices[(int) List.get(questionIndex)][2]);
        opt4.setText(questions.choices[(int) List.get(questionIndex)][3]);

        // Initialize a CountDownTimer object that counts down from 20 seconds to 0 seconds
        countDownTimer = new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update the timer TextView with the remaining time
                timer.setText("Time Limit: " + millisUntilFinished / 1000 + " seconds");

                if (millisUntilFinished < 6000) {
                    if(playAnimation){
                        timer.setText(millisUntilFinished / 1000+" Seconds");
                        timer.setTextSize(24);
                        timer.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        timer.setTextColor(Color.RED);


                        Animation animation = new AlphaAnimation(0.2f, 1.0f);
                        animation.setDuration(1000);
                        animation.setInterpolator(new LinearInterpolator());
                        animation.setRepeatCount(Animation.INFINITE);
                        animation.setRepeatMode(Animation.REVERSE);
                        timer.startAnimation(animation);


                    }


                    // TODO: Add warning graphic animation
                }

            }

            public void onFinish() {
                // When the timer finishes, load the next question
                questionIndex++;
                timer.clearAnimation();
                timer.setTextSize(16);
                timer.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                loadNewQuestion();

            }
        };

        // Start the timer
        countDownTimer.start();
        playAnimation=true;
        timer.setTextColor(Color.BLACK);

    }
    void startAnimation() {
        if (playAnimation) {
            Animation animation = new AlphaAnimation(1, 0.5f);
            animation.setDuration(300);
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.REVERSE);
            question.startAnimation(animation);
        }
    }


    //finish quiz function
    void finishQuiz(){

        Intent intent = new Intent(question_answer1.this,result.class);
        intent.putExtra("score",score);
        startActivity(intent);
        finish();
    }

    void showAns(Button clickButton){
        String b1=opt1.getText().toString();
        String b2=opt2.getText().toString();
        String b3=opt3.getText().toString();
        String b4=opt4.getText().toString();
        submit.setText("Next");
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);


        // Get the correct answer
        String correctAns = questions.answer[(int) List.get(questionIndex)];

        // Check if the selected answer is correct
        if (selectedAns.equals(correctAns)){
            if (!submit.equals(clickButton)){
                clickButton.setBackgroundColor(Color.parseColor("#66ff33"));
            }


            successSound.start(); //play success sound
            score++; //increment score
        } else {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 50 milliseconds to indicate incorrect answer
            vibrator.vibrate(500);

            failSound.start(); //play fail sound
        }

        // Change the background color of the correct answer button to green
        if (b1.equals(correctAns)){
            opt1.setBackgroundColor(Color.parseColor("#66ff33"));
        } else if (b2.equals(correctAns)){
            opt2.setBackgroundColor(Color.parseColor("#66ff33"));
        } else if (b3.equals(correctAns)){
            opt3.setBackgroundColor(Color.parseColor("#66ff33"));
        } else if (b4.equals(correctAns)){
            opt4.setBackgroundColor(Color.parseColor("#66ff33"));
        }
    }




}