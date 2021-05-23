package com.example.studyroad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView timeRemaining;
    private ProgressBar timerProgress;

    private CountDownTimer countDownTimer1;
    private long timeLeft = 600000; //10mins
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureMapButton();

        timeRemaining = findViewById(R.id.timerText);
        startButton = findViewById(R.id.timerButton);
        timerProgress = findViewById(R.id.progressBar);

        startButton.setOnClickListener(v -> timerButtonAction());
    }

    public void timerButtonAction(){
        if(timerRunning == false){
            startTimer();
        }
        else{
            countDownTimer1.cancel();
            timerRunning = false;
            startButton.setText("Start");
        }
    }

    public void startTimer(){
        countDownTimer1 = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateUI();
            }

            @Override
            public void onFinish(){}
        }.start();

        startButton.setText("Stop");
        timerRunning = true;
    }

    public void updateUI(){
        long progressPercent = (timeLeft*100)/600000;
        int minutes = (int) timeLeft / 60000;
        int seconds = (int) timeLeft % 60000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += 0;
        timeLeftText += seconds;

        timeRemaining.setText(timeLeftText);
        timerProgress.setProgress((int) progressPercent);
    }

    public void configureMapButton(){
        Button mapButton = (Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, mapActivity.class)));
    }

}