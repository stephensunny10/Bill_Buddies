package com.itax.billbuddies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.activities.Auth.LoginA;
import com.itax.billbuddies.MainActivity;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.SessionManager;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class SplashA extends AppCompatActivity {
    ShimmerTextView shimmerTextView;
    boolean isLoggedIn = false;
    String TAG = "SplashActivity";
    SessionManager  sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(this);
        shimmerTextView = (ShimmerTextView)findViewById(R.id.shimmer_tv);
        Shimmer shimmer = new Shimmer();
        shimmer.start(shimmerTextView);

        startTimerAndMove();
    }

    private void startTimerAndMove(){
        Thread myThread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(Constants.splash_time);
                    if(sessionManager.isLoggedIn()){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), LoginA.class));
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }


}