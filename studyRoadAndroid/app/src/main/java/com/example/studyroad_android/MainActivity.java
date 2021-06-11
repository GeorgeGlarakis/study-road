package com.example.studyroad_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView timeRemaining;
    private ProgressBar timerProgress;
    private SeekBar timeBar;
    protected long setTime = 600000;

    private CountDownTimer countDownTimer1;
    private long timeLeft = setTime;
    private boolean timerRunning =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Map button
        configureMapButton();

        timeRemaining = findViewById(R.id.timerText);
        startButton = findViewById(R.id.timerButton);
        timerProgress = findViewById(R.id.progressBar);
        timeBar = findViewById(R.id.seekBar);

        startButton.setOnClickListener(v -> timerButtonAction());

        //Seek Bar changes, time changes
        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (timerRunning ==false) userSetTime();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (timerRunning ==false) userSetTime();
            }
        });
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
                updateUI(setTime,timeLeft);
            }

            @Override
            public void onFinish(){}
        }.start();

        startButton.setText("Stop");
        timerRunning = true;
    }

    //Update time text and progress bar.
    public void updateUI(long setTimeInit,long setTimeLeft){
        long progressPercent = (setTimeLeft*100)/setTimeInit;
        int minutes = (int) setTimeLeft / 60000;
        int seconds = (int) setTimeLeft % 60000 / 1000;
        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += 0;
        timeLeftText += seconds;

        timeRemaining.setText(timeLeftText);
        timerProgress.setProgress((int) progressPercent);

        //when timer ends, add a pin
        //Adds a new pin to the map.
        if(setTimeLeft == 0) mapActivity.addMapPin();
    }

    public void configureMapButton(){
        Button mapButton = (Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, mapActivity.class)));
    }

    public void userSetTime(){
        if (timeBar.getProgress() >= 0 && timeBar.getProgress() <=8 ) {
            setTime = 600000 * 1;
        }
        else if(timeBar.getProgress() >= 9 && timeBar.getProgress() <=16 ) {
            setTime = 600000 * 2;
        }
        else if(timeBar.getProgress() >= 17 && timeBar.getProgress() <=24 ) {
            setTime = 600000 * 3;
        }
        else if(timeBar.getProgress() >= 25 && timeBar.getProgress() <=32 ) {
            setTime = 600000 * 4;
        }
        else if(timeBar.getProgress() >= 33 && timeBar.getProgress() <=40 ) {
            setTime = 600000 * 5;
        }
        else if(timeBar.getProgress() >= 41 && timeBar.getProgress() <=48 ) {
            setTime = 600000 * 6;
        }
        else if(timeBar.getProgress() >= 49 && timeBar.getProgress() <=56 ) {
            setTime = 600000 * 7;
        }
        else if(timeBar.getProgress() >= 57 && timeBar.getProgress() <=64 ) {
            setTime = 600000 * 8;
        }
        else if(timeBar.getProgress() >= 65 && timeBar.getProgress() <=71 ) {
            setTime = 600000 * 9;
        }
        else if(timeBar.getProgress() >= 73 && timeBar.getProgress() <=80 ) {
            setTime = 600000 * 10;
        }
        else if(timeBar.getProgress() >= 81 && timeBar.getProgress() <=90 ) {
            setTime = 600000 * 11;
        }
        else setTime = 600000*12;

        timeLeft = setTime;
        System.out.println(timeBar.getProgress());
        updateUI(setTime,setTime);
    }

}